package com.mahdi.website.web.resource;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.service.interfaces.AuthorService;
import com.mahdi.website.web.remote.AuthorRemote;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/author-management")
@Tag(name = "Author", description = "Author management operations")
public class AuthorResource implements AuthorRemote {

    private final AuthorService authorService;

    @Override
    @Operation(summary = "Search authors", description = "Search for authors based on provided criteria")
    public ResponseEntity<List<AuthorDTO>> searchAuthors(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorService.searchAuthorDTO(authorDTO), HttpStatus.FOUND);
    }

    @Override
    @Operation(summary = "Save new author", description = "Add a new author to the system")
    public ResponseEntity<AuthorDTO> saveAuthor(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorService.saveAuthorDTO(authorDTO), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Update author", description = "Update an existing author's information")
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorService.updateAuthorDTO(authorDTO), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Find author by ID", description = "Retrieve an author by their ID")
    public ResponseEntity<AuthorDTO> findAuthorById(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorService.findAuthorDTOById(authorDTO), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "deactivate author by ID", description = "deactivate an author by their ID")
    public ResponseEntity<AuthorDTO> deactivateAuthorById(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorService.deactivateAuthorDTO(authorDTO), HttpStatus.OK);
    }
}
