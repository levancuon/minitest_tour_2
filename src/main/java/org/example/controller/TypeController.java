package org.example.controller;

import org.example.model.DTO.TypeDTO;
import org.example.model.Tour;
import org.example.model.Type;
import org.example.repository.ITypeRepo;
import org.example.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private ITypeService typeService;

    @GetMapping("")
    public ModelAndView listType() {
        ModelAndView modelAndView = new ModelAndView("/type/list");
        Iterable<Type> types = typeService.findAll();
        modelAndView.addObject("type", types);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }

    @PostMapping("/create")
    public String save(@ModelAttribute("type") Type type, RedirectAttributes redirectAttributes) {
        typeService.save(type);
        redirectAttributes.addFlashAttribute("message", "Them thanh cong");
        return "redirect:/type";
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") Long id) {
        Optional<Type> type = typeService.findById(id);
        if (type.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/type/update");
            modelAndView.addObject("type", type.get());
            return modelAndView;
        } else {
            return new ModelAndView("/erro_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("type") Type type, RedirectAttributes redirectAttributes) {
        typeService.save(type);
        redirectAttributes.addFlashAttribute("message", "Update thanh cong");
        return "redirect:/type";
    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        typeService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Update thanh cong");
        return "redirect:/type";
    }

    @GetMapping("/dto")
    public String dto(Model model) {
        Iterable<TypeDTO> getTypeWithCountTour = typeService.getTypeWithCountTour();
        model.addAttribute("getTypeWithCountTour", getTypeWithCountTour);
        return "/dto";
    }


}
