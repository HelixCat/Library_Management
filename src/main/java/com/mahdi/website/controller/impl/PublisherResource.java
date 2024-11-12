package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.PublisherRemote;
import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.mapper.PublisherMapper;
import com.mahdi.website.service.impl.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/publisher-management")
public class PublisherResource implements PublisherRemote {

    private final PublisherMapper publisherMapper;
    private final PublisherService publisherService;

    @PostMapping("/search")
    public ResponseEntity<List<PublisherDTO>> searchPublishers(@RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherMapper.toDTOList(publisherService.searchPublisher(publisherDTO)), HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<PublisherDTO> savePublisher(@RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherMapper.toDTO(publisherService.savePublisher(publisherDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/deactivate")
    public ResponseEntity<PublisherDTO> deactivatePublisher(@RequestBody Long publisherId) {
        return new ResponseEntity<>(publisherMapper.toDTO(publisherService.deactivatePublisherById(publisherId)), HttpStatus.OK);
    }

    @PostMapping("/publisher-management/update-publisher/{publisherId}")
    public String updatePublisher(@PathVariable Long publisherId, @ModelAttribute PublisherDTO publisherDTO, Model model) {
        publisherService.updatePublisher(publisherId, publisherDTO);
        model.addAttribute("message", "the publisher has been successfully updated.");
        model.addAttribute("publisherDTO", new PublisherDTO());
        return "redirect:/publisher-management";
    }
}

