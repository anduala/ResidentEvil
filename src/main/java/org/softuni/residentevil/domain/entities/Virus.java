package org.softuni.residentevil.domain.entities;

import org.softuni.residentevil.domain.entities.enums.Creator;
import org.softuni.residentevil.domain.entities.enums.Magnitude;
import org.softuni.residentevil.domain.entities.enums.Mutation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "viruses")
public class Virus extends BaseEntity{

    private String name;
    private String description;
    private String sideEffect;
    private Creator creator;
    private Boolean isDeadly;
    private Boolean isCurable;
    private Mutation mutation;
    private Integer turnoverRate;
    private Integer hoursUntilTurn;
    private Magnitude magnitude;
    private LocalDate releasedOn;
    private List<Capital> capitals;

    public Virus() {
    }

    @Column(name = "name")
    @NotNull
    @Size(min = 3,max = 10,message = "Invalid name!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "side_effects")
    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "creator")
    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    @Column(name = "is_deadly")
    public Boolean getDeadly() {
        return isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    @Column(name = "is_curable")
    public Boolean getCurable() {
        return isCurable;
    }

    public void setCurable(Boolean curable) {
        isCurable = curable;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "mutation")
    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @Column(name = "turnover_rate")
    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @Column(name = "hours_until_turn")
    public Integer getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "magnitude")
    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    @Column(name = "released_on")
    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    @ManyToMany(targetEntity = Capital.class)
    @JoinTable(
            name = "viruses_capitals",
            joinColumns = @JoinColumn(name = "virus_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "capital_id",referencedColumnName = "id"))
    public List<Capital> getCapitals() {
        return capitals;
    }

    public void setCapitals(List<Capital> capitals) {
        this.capitals = capitals;
    }
}
