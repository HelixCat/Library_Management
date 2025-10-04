package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.PublisherRemote;
import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.mapper.PublisherMapper;
import com.mahdi.website.service.interfaces.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/publisher-management")
@Tag(name = "Publisher", description = "Publisher management operations")
public class PublisherResource implements PublisherRemote {

    private final PublisherMapper publisherMapper;
    private final PublisherService publisherService;

    @Override
    @Operation(summary = "Search publishers", description = "Search for publishers based on provided criteria")
    public ResponseEntity<List<PublisherDTO>> searchPublishers(@RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherMapper.toDTOList(publisherService.searchPublisher(publisherDTO)), HttpStatus.FOUND);
    }

    @Override
    @Operation(summary = "Save new publisher", description = "Add a new publisher to the system")
    public ResponseEntity<PublisherDTO> savePublisher(@RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherMapper.toDTO(publisherService.savePublisher(publisherDTO)), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Update publisher", description = "Update an existing publisher's information")
    public ResponseEntity<PublisherDTO> updatePublisher(@RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherMapper.toDTO(publisherService.updatePublisher(publisherDTO)), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Find publisher by ID", description = "Retrieve a publisher by their ID")
    public ResponseEntity<PublisherDTO> findPublisherById(@PathVariable Long id) {
        return new ResponseEntity<>(publisherMapper.toDTO(publisherService.findPublisherById(id)), HttpStatus.OK);
    }
}
