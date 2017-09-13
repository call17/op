package ru.sbrf.metrica.vaadin.model;

import java.time.LocalDate;
import java.util.Objects;

public class Idle {

    private Long id;
    private String projectNumber;
    private String projectName;
    private String blockName;
    private String releaseName;
    private String stageName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String estimate;
    private String problemTypeName;
    private String automationSystemName;
    private String functionalSubsystemName;
    private String description;
    private String responsible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public String getProblemTypeName() {
        return problemTypeName;
    }

    public void setProblemTypeName(String problemTypeName) {
        this.problemTypeName = problemTypeName;
    }

    public String getAutomationSystemName() {
        return automationSystemName;
    }

    public void setAutomationSystemName(String automationSystemName) {
        this.automationSystemName = automationSystemName;
    }

    public String getFunctionalSubsystemName() {
        return functionalSubsystemName;
    }

    public void setFunctionalSubsystemName(String functionalSubsystemName) {
        this.functionalSubsystemName = functionalSubsystemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Idle idle = (Idle) o;
        return Objects.equals(projectNumber, idle.projectNumber) &&
                Objects.equals(projectName, idle.projectName) &&
                Objects.equals(blockName, idle.blockName) &&
                Objects.equals(releaseName, idle.releaseName) &&
                Objects.equals(stageName, idle.stageName) &&
                Objects.equals(startDate, idle.startDate) &&
                Objects.equals(endDate, idle.endDate) &&
                Objects.equals(estimate, idle.estimate) &&
                Objects.equals(problemTypeName, idle.problemTypeName) &&
                Objects.equals(automationSystemName, idle.automationSystemName) &&
                Objects.equals(functionalSubsystemName, idle.functionalSubsystemName) &&
                Objects.equals(description, idle.description) &&
                Objects.equals(responsible, idle.responsible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectNumber, projectName, blockName, releaseName, stageName, startDate, endDate, estimate, problemTypeName, automationSystemName, functionalSubsystemName, description, responsible);
    }
}
