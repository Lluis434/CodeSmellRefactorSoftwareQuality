package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyPlan extends Registry {
    private StudyObjective objective;
    private List<String> steps;

    // Armazenamento interno dos dados necessários para os passos
    private List<String> stringProperties;
    private Integer numberOfSteps;
    private boolean isImportant;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public StudyPlan(String planName, StudyObjective objective, List<StudyMaterial> materials) {
        this.name = planName;
        this.objective = objective;
        this.steps = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Plan: " + name + ",\nObjective: " + objective.getDescription() +
                ",\nSteps: " + String.join(", ", steps);
    }

    public List<String> getSteps() {
        return steps;
    }

    public StudyObjective getObjective() {
        return objective;
    }

    public void assignObjective(StudyObjective objective) {
        this.objective = objective;
    }

    public void addSingleStep(String toAdd) {
        steps.add(toAdd);
    }

    // Novo método: substitui a versão anterior com long parameter list
    public void handleAssignSteps(List<String> stringProperties, Integer numberOfSteps,
                                  boolean isImportant, LocalDateTime startDate, LocalDateTime endDate) {
        this.stringProperties = stringProperties;
        this.numberOfSteps = numberOfSteps;
        this.isImportant = isImportant;
        this.startDate = startDate;
        this.endDate = endDate;

        assignSteps(); // agora sem parâmetros
    }

    // Refatorado: usa os atributos da instância em vez de uma longa lista de parâmetros
    private void assignSteps() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        this.steps = new ArrayList<>(Arrays.asList(
                getFirstStep(),
                getResetStudyMechanism(),
                getConsistentStep(),
                getSeasonalSteps(),
                getBasicSteps(),
                "Number of steps: " + numberOfSteps,
                "Is it important to you? " + isImportant,
                startDate.format(formatter),
                endDate.format(formatter),
                getMainObjectiveTitle(),
                getMainGoalTitle(),
                getMainMaterialTopic(),
                getMainTask()
        ));
    }

    // Métodos auxiliares de acesso semântico
    private String getFirstStep() { return stringProperties.get(0); }
    private String getResetStudyMechanism() { return stringProperties.get(1); }
    private String getConsistentStep() { return stringProperties.get(2); }
    private String getSeasonalSteps() { return stringProperties.get(3); }
    private String getBasicSteps() { return stringProperties.get(4); }
    private String getMainObjectiveTitle() { return stringProperties.get(5); }
    private String getMainGoalTitle() { return stringProperties.get(6); }
    private String getMainMaterialTopic() { return stringProperties.get(7); }
    private String getMainTask() { return stringProperties.get(8); }
}

