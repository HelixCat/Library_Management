package com.mahdi.website.controller;

import com.mahdi.website.dto.PublisherDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TranslatorController {

//    private final Tra

    @GetMapping("/translator-management")
    public String showPublisherManagement(Model model) {
        model.addAttribute("translator", new PublisherDTO());
        model.addAttribute("searchPerformed", Boolean.FALSE);
        return "translator-management";
    }

    @PostMapping("/translator-management/search")
    public String searchPublishers(@ModelAttribute PublisherDTO publisherDTO, Model model) {
        List<PublisherDTO> publisherDTOList = translateService.searchPublisher(publisherDTO);
        if (publisherDTOList.isEmpty()) {
            model.addAttribute("message", "No results found.");
            model.addAttribute("searchPerformed", Boolean.FALSE);
        } else {
            model.addAttribute("publisherDTOList", publisherDTOList);
            model.addAttribute("searchPerformed", Boolean.TRUE);
        }
        return "publisher-management";
    }
}
