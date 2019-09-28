package org.softuni.residentevil.service;

import org.softuni.residentevil.domain.models.service.VirusServiceModel;

import java.util.List;

public interface VirusService {

    VirusServiceModel spreadVirus(VirusServiceModel virusServiceModel);

    List<VirusServiceModel> findAllViruses();

    VirusServiceModel findVirusById(String id);

    void deleteVirusById(String id);
}
