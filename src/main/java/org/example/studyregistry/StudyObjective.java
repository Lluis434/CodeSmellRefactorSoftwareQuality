package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.List;

public class StudyObjective extends Registry {
    private String title;
    private String description;
    private String topic;
    private Integer practicedDays;
    private LocalDateTime startDate;
    private Double duration;
    private String objectiveInOneLine;
    private String objectiveFullDescription;
    private String motivation;

    // --- Construtor ---
    public StudyObjective(String title, String description) {
        this.title = title;
        this.description = description;
        this.name = title;
    }

    // --- Getters ---
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTopic() { return topic; }
    public Integer getPracticedDays() { return practicedDays; }
    public LocalDateTime getStartDate() { return startDate; }
    public Double getDuration() { return duration; }
    public String getObjectiveInOneLine() { return objectiveInOneLine; }
    public String getObjectiveFullDescription() { return objectiveFullDescription; }
    public String getMotivation() { return motivation; }

    public void setDescription(String description) {
        this.description = description;
    }

    // --- Métodos principais ---

    public void handleSetObjective() {
        handleSetRegistry(getId(), getName(), getPriority(), isActive());
        handleSetTextualInfo(getTitle(), getDescription(), getTopic(), getObjectiveInOneLine(), getObjectiveFullDescription(), getMotivation());
        if (getStartDate() != null && getDuration() != null && practicedDays != null) {
            handleSetTime(practicedDays, getStartDate().getDayOfMonth(), getStartDate().getMonthValue(), getStartDate().getYear(), getDuration());
        }
    }

    public void handleSetRegistry(Integer id, String name, Integer priority, boolean isActive) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.isActive = isActive;
    }

    public void handleSetTextualInfo(String title, String description, String topic,
                                     String objectiveInOneLine, String objectiveFullDescription, String motivation) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.objectiveInOneLine = objectiveInOneLine;
        this.objectiveFullDescription = objectiveFullDescription;
        this.motivation = motivation;
    }

    public void handleSetTime(Integer practicedDays, int day, int month, int year, Double duration) {
        this.practicedDays = practicedDays;
        this.duration = duration;
        this.startDate = LocalDateTime.of(year, month, day, 0, 0);
    }

    public int handleSetObjectiveAdapter(List<Integer> intProperties, List<String> stringProperties, Double duration, boolean isActive) {
        setFromAdapterLists(intProperties, stringProperties, duration, isActive);
        handleSetObjective();
        return this.id;
    }

    // --- Refatorado para evitar Long Method ---
    private void setFromAdapterLists(List<Integer> intProps, List<String> strProps, Double duration, boolean isActive) {
        extractRegistryProperties(intProps, strProps, isActive);
        extractTextualProperties(strProps);
        extractTimeProperties(intProps, duration);
    }

    private void extractRegistryProperties(List<Integer> intProps, List<String> strProps, boolean isActive) {
        this.id = intProps.get(0);
        this.priority = intProps.get(1);
        this.name = strProps.get(0);
        this.isActive = isActive;
    }

    private void extractTextualProperties(List<String> strProps) {
        this.title = strProps.get(1);
        this.description = strProps.get(2);
        this.topic = strProps.get(3);
        this.objectiveInOneLine = strProps.get(4);
        this.objectiveFullDescription = strProps.get(5);
        this.motivation = strProps.get(6);
    }

    private void extractTimeProperties(List<Integer> intProps, Double duration) {
        this.practicedDays = intProps.get(2);
        int day = intProps.get(3);
        int month = intProps.get(4);
        int year = intProps.get(5);
        this.duration = duration;
        this.startDate = LocalDateTime.of(year, month, day, 0, 0);
    }

    // --- toString ---
    @Override
    public String toString() {
        return "StudyObjective [title:" + title + ", description:" + description
                + (topic != null ? ", topic:" + topic : "")
                + (practicedDays != null ? ", practicedDays:" + practicedDays : "")
                + (duration != null ? ", duration:" + duration : "")
                + (objectiveInOneLine != null ? ", objective summary:" + objectiveInOneLine : "")
                + (objectiveFullDescription != null ? ", objective full description:" + objectiveFullDescription : "")
                + (motivation != null ? ", motivation:" + motivation : "") + "]";
    }
}
