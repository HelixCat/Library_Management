package com.mahdi.website.controller.rest;

import com.mahdi.website.dto.PublisherDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PublisherRemote {
    @PostMapping("/search")
    ResponseEntity<List<PublisherDTO>> searchPublishers(@RequestBody PublisherDTO publisherDTO);

    @PostMapping("/save")
    ResponseEntity<PublisherDTO> savePublisher(@RequestBody PublisherDTO publisherDTO);

    @PutMapping("/deactivate")
    ResponseEntity<PublisherDTO> deactivatePublisher(@RequestBody Long publisherId);

    @PutMapping("/update")
    ResponseEntity<PublisherDTO> updatePublisher(@RequestBody PublisherDTO publisherDTO);
}
