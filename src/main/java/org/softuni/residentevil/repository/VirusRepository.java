package org.softuni.residentevil.repository;

import org.softuni.residentevil.domain.entities.Virus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VirusRepository extends JpaRepository<Virus,String> {

    List<Virus> findAll();

    Optional<Virus> findById(String id);

    void deleteById(String id);

}
