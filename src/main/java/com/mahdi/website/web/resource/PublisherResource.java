package com.mahdi.website.web.resource;

import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.service.interfaces.PublisherService;
import com.mahdi.website.web.remote.PublisherRemote;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/publisher-management")
@Tag(name = "Publisher", description = "Publisher management operations")
public class PublisherResource implements PublisherRemote {

    private final PublisherService publisherService;

    @Override
    @Operation(summary = "Search publishers", description = "Search for publishers based on provided criteria")
    public ResponseEntity<List<PublisherDTO>> searchPublishers(@RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherService.searchPublisherDTO(publisherDTO), HttpStatus.FOUND);
    }

    @Override
    @Operation(summary = "Save new publisher", description = "Add a new publisher to the system")
    public ResponseEntity<PublisherDTO> savePublisher(@RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherService.savePublisherDTO(publisherDTO), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Update publisher", description = "Update an existing publisher's information")
    public ResponseEntity<PublisherDTO> updatePublisher(@RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherService.updatePublisherDTO(publisherDTO), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Find publisher by ID", description = "Retrieve a publisher by their ID")
    public ResponseEntity<PublisherDTO> findPublisherById(@PathVariable Long id) {
        return new ResponseEntity<>(publisherService.findPublisherDTOById(id), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "deactivate publisher by ID", description = "deactivate a publisher by their ID")
    public ResponseEntity<PublisherDTO> deactivatePublisher(@RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherService.deactivatePublisherDTOById(publisherDTO), HttpStatus.OK);
    }
}
