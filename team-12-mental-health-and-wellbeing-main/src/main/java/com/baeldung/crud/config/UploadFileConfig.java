package com.baeldung.crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// solve the problem that image or video can not be displayed immediately until re-run the server
@Configuration
public class UploadFileConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // find the absolute path
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static";
        String newPath = path.replace("\\","/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/img/resourceImg/**").addResourceLocations("file:" + newPath + "/img/resourceImg/");
        registry.addResourceHandler("/img/galleryImg/**").addResourceLocations("file:" + newPath + "/img/galleryImg/");
        registry.addResourceHandler("/mp4/resourceMp4/**").addResourceLocations("file:" + newPath + "/mp4/resourceMp4/");
        registry.addResourceHandler("/mp4/galleryMp4/**").addResourceLocations("file:" + newPath + "/mp4/galleryMp4/");
    // for others' folders
        // for event
        registry.addResourceHandler("/img/eventImg/**").addResourceLocations("file:" + newPath + "/img/eventImg/");
        // for news
        registry.addResourceHandler("/images/news/**").addResourceLocations("file:" + newPath + "/images/news/");
    }
}

