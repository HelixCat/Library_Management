package com.mahdi.website.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSiteConfiguration {

    @Bean
    public ModelMapper modelMapperBean() {
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }
}
