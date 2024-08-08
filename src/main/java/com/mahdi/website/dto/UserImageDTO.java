package com.mahdi.website.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserImageDTO {

    private MultipartFile file;

}
