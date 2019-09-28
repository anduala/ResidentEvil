package org.softuni.residentevil.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.models.binding.VirusAddBindingModel;
import org.softuni.residentevil.domain.models.service.VirusServiceModel;
import org.softuni.residentevil.domain.models.view.CapitalListViewModel;
import org.softuni.residentevil.domain.models.view.CapitalShowViewModel;
import org.softuni.residentevil.domain.models.view.VirusShowViewModel;
import org.softuni.residentevil.service.CapitalService;
import org.softuni.residentevil.service.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController{

    private final CapitalService capitalService;
    private final VirusService virusService;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusController(CapitalService capitalService, VirusService virusService, ModelMapper modelMapper) {
        this.capitalService = capitalService;
        this.virusService = virusService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView, @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel){
        modelAndView.addObject("bindingModel",bindingModel);
        modelAndView.addObject("capitals",this.capitalService.findAllCapitals().stream()
                .map(c -> this.modelMapper.map(c, CapitalListViewModel.class)).collect(Collectors.toList()));

        return super.view("add-virus",modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(@Valid @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel,
                                   BindingResult bindingResult, ModelAndView modelAndView){
        VirusServiceModel virusServiceModel = this.virusService
                .spreadVirus(this.modelMapper.map(bindingModel,VirusServiceModel.class));

        if (bindingResult.hasErrors()){
            modelAndView.addObject("bindingModel",bindingModel);
            return super.view("add-virus",modelAndView);
        }

        return super.redirect("/");
    }

    @GetMapping("/show")
    public ModelAndView show(ModelAndView modelAndView){

        List<VirusShowViewModel> viruses = this.virusService.findAllViruses()
                .stream()
                .map(v -> this.modelMapper.map(v, VirusShowViewModel.class))
                .collect(Collectors.toList());

        List<CapitalShowViewModel> capitals = this.capitalService.findAllCapitals()
                .stream()
                .map(c -> this.modelMapper.map(c, CapitalShowViewModel.class))
                .collect(Collectors.toList());

        modelAndView.setViewName("show-virus");
        modelAndView.addObject("viruses",viruses);
        modelAndView.addObject("capitals",capitals);

        return super.view("show-virus",modelAndView);
    }



    @GetMapping("/show/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel, ModelAndView modelAndView){
            VirusServiceModel virusServiceModel = this.virusService.findVirusById(id);

            if (virusServiceModel == null){
                throw new IllegalArgumentException("Virus not found!");
            }

            bindingModel.setReleasedOn(virusServiceModel.getReleasedOn());

            modelAndView.addObject("bindingModel",bindingModel);
            modelAndView.addObject("capitals",this.capitalService.findAllCapitals().stream()
                .map(c -> this.modelMapper.map(c, CapitalListViewModel.class)).collect(Collectors.toList()));

            this.virusService.deleteVirusById(id);



        return super.view("edit",modelAndView);
    }


    @PostMapping("/show/edit/{name}")
    public ModelAndView editConfirm(@PathVariable("name") String name, ModelAndView modelAndView){

        modelAndView.setViewName("redirect:/show");
        return modelAndView;
    }


    @GetMapping(value = "/show/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id, ModelAndView modelAndView){
        this.virusService.deleteVirusById(id);

        return super.view("/index",modelAndView);
    }





}





