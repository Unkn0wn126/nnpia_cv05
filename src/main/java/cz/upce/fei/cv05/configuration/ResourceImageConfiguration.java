package cz.upce.fei.cv05.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ResourceImageConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:D:\\stuff\\Projects\\NNPIA\\nnpia_cv05\\images\\");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:D:\\stuff\\Projects\\NNPIA\\nnpia_cv05\\src\\main\\resources\\static\\");
    }
}
