package org.softuni.residentevil.service;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.entities.Virus;
import org.softuni.residentevil.domain.models.service.VirusServiceModel;
import org.softuni.residentevil.repository.VirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService{
    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VirusServiceModel spreadVirus(VirusServiceModel virusServiceModel) {
        Virus virus = this.modelMapper.map(virusServiceModel, Virus.class);
        try {
            this.virusRepository.saveAndFlush(virus);

            return this.modelMapper.map(virus, VirusServiceModel.class);
        } catch (Exception e){
            e.printStackTrace();

            return null;
        }

    }

    @Override
    public List<VirusServiceModel> findAllViruses() {
        return this.virusRepository.findAll()
                .stream()
                .map(v -> this.modelMapper.map(v , VirusServiceModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public VirusServiceModel findVirusById(String id) {
        VirusServiceModel virusServiceModel = this.modelMapper.map(this.virusRepository.findById(id),VirusServiceModel.class);

        return virusServiceModel;
    }

    @Override
    public void deleteVirusById(String id) {
        try {
            this.virusRepository.deleteById(id);

        } catch (Exception e){
            e.printStackTrace();

        }
    }

}





