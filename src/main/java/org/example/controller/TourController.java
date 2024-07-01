package org.example.controller;

import org.example.model.Tour;
import org.example.model.Type;
import org.example.service.imp.TourService;
import org.example.service.imp.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/tour")
public class TourController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private TourService tourService;

    @ModelAttribute("type")
    public Iterable<Type> listType() {
        return typeService.findAll();
    }

    @GetMapping("")
    public ModelAndView list(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/tour/list");
        Page<Tour> tour = tourService.findAll(pageable);
        modelAndView.addObject("tour", tour);
        return modelAndView;
    }

    @GetMapping("/sort")
    public ModelAndView sort(@RequestParam String sort) {
        ModelAndView modelAndView = new ModelAndView("/tour/sort");
        Page<Tour> tour = tourService.findAllAndSort(sort);
        modelAndView.addObject("tour", tour);
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("/tour/create");
        modelAndView.addObject("tour", new Tour());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(
            @Validated @ModelAttribute("tour") Tour tour,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "/tour/create";
        }
        tourService.save(tour);
        redirectAttributes.addFlashAttribute("message", "Thêm thành công");
        return "redirect:/tour ";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(value = "search", defaultValue = "") String qbc,
            Model model,
            @PageableDefault(value = 5) Pageable pageable
    ) {
        if (!qbc.isEmpty()) {
            Page<Tour> search = tourService.findByCodeEndingWith(pageable, qbc);
            model.addAttribute("tour", search);
            model.addAttribute("qbc", qbc);
            return "/tour/search";
        }
        return "redirect:/tour";
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") Long id) {
        Optional<Tour> tour = tourService.findById(id);
        if (tour.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/tour/update");
            modelAndView.addObject("tour", tour.get());
            return modelAndView;
        } else {
            return new ModelAndView("/erro_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("tour") Tour tour, RedirectAttributes redirectAttributes) {
        tourService.save(tour);
        redirectAttributes.addFlashAttribute("message", "Update thanh cong");
        return "redirect:/tour";
    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        tourService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Update thanh cong");
        return "redirect:/tour";
    }
}
