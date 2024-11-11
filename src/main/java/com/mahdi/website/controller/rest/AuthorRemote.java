package com.mahdi.website.controller.rest;

import com.mahdi.website.dto.AuthorDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AuthorRemote {
    @PostMapping("/search")
    List<AuthorDTO> searchAuthors(@RequestBody AuthorDTO authorDTO);

    @PostMapping("/add")
    AuthorDTO saveAuthor(@RequestBody AuthorDTO authorDTO);

    @GetMapping("/change-activity")
    AuthorDTO deactivateAuthor(@RequestBody AuthorDTO authorDTO);

    @PostMapping("/update")
    AuthorDTO updateAuthor(@RequestBody AuthorDTO authorDTO);
}
