package com.mahdi.website.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class UserImageDTO implements Serializable {

    @NotNull(message = "Please select an image file to upload.")
    @Valid
    private MultipartFile file;
}
