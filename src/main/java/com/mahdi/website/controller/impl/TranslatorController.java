package com.mahdi.website.controller.impl;

import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.service.interfaces.TranslatorServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class TranslatorController {

    private final TranslatorServiceInterface translatorService;

    @GetMapping("/translator-management")
    public String showTranslatorManagement(Model model) {
        model.addAttribute("translatorDTO", new TranslatorDTO());
        model.addAttribute("searchPerformed", Boolean.FALSE);
        return "translator-management";
    }

    @PostMapping("/translator-management/search")
    public String searchTranslators(@ModelAttribute TranslatorDTO translatorDTO, Model model) {
        List<TranslatorDTO> translatorDTOList = translatorService.searchTranslator(translatorDTO);
        if (translatorDTOList.isEmpty()) {
            model.addAttribute("message", "No results found.");
            model.addAttribute("searchPerformed", Boolean.FALSE);
        } else {
            model.addAttribute("translatorDTOList", translatorDTOList);
            model.addAttribute("searchPerformed", Boolean.TRUE);
        }
        return "translator-management";
    }

    @GetMapping("/add-translator")
    public String showAddTranslatorPage(Model model) {
        model.addAttribute("translatorDTO", new TranslatorDTO());
        return "add-translator";
    }

    @PostMapping("/save-translator")
    public String saveTranslator(@ModelAttribute TranslatorDTO translatorDTO, Model model) {
        translatorService.saveTranslator(translatorDTO);
        model.addAttribute("message", "the translator has been successfully saved.");
        model.addAttribute("translatorDTO", new TranslatorDTO());
        return "translator-management";
    }

    @GetMapping("/translator-management/deactivate-translator/{translatorId}")
    public String deactivateTranslator(@PathVariable Long translatorId, Model model) {
        translatorService.deactivateTranslatorById(translatorId);
        model.addAttribute("message", "the translator has been successfully deactivated.");
        model.addAttribute("translatorDTO", new TranslatorDTO());
        return "redirect:/translator-management";
    }

    @GetMapping("/translator-management/update-translator/{translatorId}")
    public String viewUpdateTranslatorPage(@PathVariable Long translatorId, Model model) {
        TranslatorDTO translatorDTO = translatorService.findTranslatorDTOById(translatorId);
        model.addAttribute("translatorDTO", translatorDTO);
        return "update-translator";
    }

    @PostMapping("/translator-management/update-translator/{translatorId}")
    public String updateTranslator(@PathVariable Long translatorId, @ModelAttribute TranslatorDTO translatorDTO, Model model) {
        translatorService.updateTranslator(translatorId, translatorDTO);
        model.addAttribute("message", "the translator has been successfully updated.");
        model.addAttribute("translatorDTO", new TranslatorDTO());
        return "redirect:/translator-management";
    }
}
