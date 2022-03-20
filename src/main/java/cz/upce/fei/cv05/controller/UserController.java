package cz.upce.fei.cv05.controller;

import cz.upce.fei.cv05.dto.ProfileDto;
import cz.upce.fei.cv05.dto.UserDto;
import cz.upce.fei.cv05.entity.*;
import cz.upce.fei.cv05.service.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final SessionService sessionService;

    private final PostService postService;

    private final ProfileService profileService;

    private final UserService userService;

    private final FileService fileService;

    public UserController(SessionService sessionService, PostService postService, ProfileService profileService, UserService userService, FileService fileService) {
        this.sessionService = sessionService;
        this.postService = postService;
        this.profileService = profileService;
        this.userService = userService;
        this.fileService = fileService;
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException(){ return "error"; }

    @GetMapping("/login")
    public String showLogin(Model model){
        if (sessionService.getUser() != null){
            return "redirect:/";
        }
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "login";
    }

    @GetMapping("/logout")
    public String showLogout(Model model){
        if (sessionService.getUser() != null){
            userService.logout();
        }

        return "redirect:/login";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model){
        if (sessionService.getUser() == null){
            return "redirect:/login";
        }

        List<User> all = userService.getAllUsers();
        List<UserDto> users = all.stream().map(this::convertToDto).collect(Collectors.toList());

        model.addAttribute("users", users);

        return "user-list";
    }

    @GetMapping(value = {"/", "/user/{id}"})
    public String showUser(@PathVariable(required = false) Long id, Model model){
        if (sessionService.getUser() == null){
            return "redirect:/login";
        }
        UserDto userDto;
        if (id != null){
            userDto = convertToDto(userService.getUserById(id));
        }else {
            userDto = convertToDto(sessionService.getUser());
        }

        User user = userService.getUserById(userDto.getId());
        boolean canEdit = canUserEditProfile(user);

        model.addAttribute("user", userDto);
        model.addAttribute("canEdit", canEdit);

        return "user-detail";
    }

    private UserDto convertToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        userDto.setProfile(convertToDto(user.getProfile()));

        return userDto;
    }

    private ProfileDto convertToDto(Profile profile){
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(profile.getId());
        profileDto.setQuote(profile.getQuote());
        profileDto.setName(profile.getName());
        profileDto.setSurname(profile.getSurname());
        profileDto.setDateOfBirth(profile.getDateOfBirth().toString());
        profileDto.setPathToImage(profile.getPathToImage());

        return profileDto;
    }

    @PostMapping("/login-form-process")
    public String loginFormProcess(UserDto userDto){
        if (sessionService.getUser() != null){
            return "redirect:/";
        }

        userService.login(userDto.getName(), userDto.getPassword());
        return "redirect:/";
    }

    @GetMapping(value = {"/user-form", "/user-form/{id}"})
    public String showUserForm(@PathVariable(required = false) Long id, Model model){
        if ((id == null && sessionService.getUser() != null) || (id != null && sessionService.getUser() == null)){
            return "redirect:/";
        }
        if (id != null){
            User user = userService.getUserById(id);
            if (!canUserEditProfile(user)){
                return "redirect:/";
            }

            UserDto userDto = convertToDto(user);
            model.addAttribute("user", userDto);

        }else{
            UserDto userDto = new UserDto();
            userDto.setProfile(new ProfileDto());

            model.addAttribute("user", userDto);
        }


        return "create-user";
    }

    private boolean canUserEditProfile(User user){
        if(user.getId() == sessionService.getUser().getId()){
            return true;
        }
        Set<UserHasRole> userRoles = sessionService.getUser().getUserRoles();
        for (UserHasRole userRole:
                userRoles) {
            if (userRole.getRole().getUserRoleType() == UserRoleType.ADMIN){
                return true;
            }
        }

        return false;
    }

    @PostMapping("/user-form-process")
    public String userFormProcess(UserDto userDto){
        User user;
        if (userDto.getId() == null){
            user = userService.add(userDto.getName(), userDto.getEmail(), userDto.getPassword());
        }else {
            user = userService.getUserById(userDto.getId());
        }
        ProfileDto profileDto = userDto.getProfile();
        try {
            Date date = new SimpleDateFormat("yyy-MM-dd").parse(profileDto.getDateOfBirth());
            String fileName = fileService.upload(profileDto.getImage());
            if (userDto.getId() == null){
                profileService.add(user.getId(), profileDto.getName(), profileDto.getSurname(), profileDto.getQuote(), date, fileName);
            }else {
                profileService.update(user.getProfile().getId(), profileDto.getName(), profileDto.getSurname(), profileDto.getQuote(), date, fileName);
            }
        } catch (ParseException e) {
            System.err.println(e);
        }

        return "redirect:/";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //convert the date Note that the conversion here should always be in the same format as the string passed in, e.g. 2015-9-9 should be yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor is a custom date editor
    }
}
