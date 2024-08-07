package com.mahdi.website.controller;

import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;



@Controller
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/publisher-management")
    public String showPublisherManagement(Model model) {
        model.addAttribute("publisherDTO", new PublisherDTO());
        model.addAttribute("searchPerformed", Boolean.FALSE);
        return "publisher_management";
    }

    @PostMapping("/publisher-management/search")
    public String searchPublishers(@ModelAttribute PublisherDTO publisherDTO, Model model) {
        List<PublisherDTO> publisherDTOList = publisherService.searchPublisher(publisherDTO);
        if (publisherDTOList.isEmpty()) {
            model.addAttribute("message", "No results found.");
            model.addAttribute("searchPerformed", Boolean.FALSE);
        } else {
            model.addAttribute("publisherDTOList", publisherDTOList);
            model.addAttribute("searchPerformed", Boolean.TRUE);
        }
        return "publisher_management";
    }

    @GetMapping("/add-publisher")
    public String showAddPublisherPage(Model model) {
        model.addAttribute("publisherDTO", new PublisherDTO());
        return "add_publisher";
    }

    @PostMapping("/save-publisher")
    public String savePublisher(@ModelAttribute PublisherDTO publisherDTO, Model model) {
        publisherService.savePublisher(publisherDTO);
        model.addAttribute("message", "the publisher has been successfully saved.");
        model.addAttribute("publisherDTO", new PublisherDTO());
        return "publisher_management";
    }

    @GetMapping("/publisher-management/deactivate-publisher/{publisherId}")
    public String deactivatePublisher(@PathVariable Long publisherId, Model model) {
        publisherService.deactivatePublisherById(publisherId);
        model.addAttribute("message", "the publisher has been successfully deactivated.");
        model.addAttribute("publisherDTO", new PublisherDTO());
        return "publisher_management";
    }

    @GetMapping("/publisher-management/update-publisher/{publisherId}")
    public String viewUpdatePublisherPage(@PathVariable Long publisherId, Model model) {
        PublisherDTO publisherDTO = publisherService.findPublisherDTOById(publisherId);
        model.addAttribute("publisherDTO", publisherDTO);
        return "update_publisher";
    }

    @PostMapping("/publisher-management/update-publisher/{publisherId}")
    public String updatePublisher(@PathVariable Long publisherId, @ModelAttribute PublisherDTO publisherDTO, Model model) {
        publisherService.updatePublisher(publisherId, publisherDTO);
        model.addAttribute("message", "the publisher has been successfully updated.");
        model.addAttribute("publisherDTO", new PublisherDTO());
        return "publisher_management";
    }
}
