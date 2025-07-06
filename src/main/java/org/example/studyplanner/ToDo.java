package org.example.studyplanner;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ToDo implements PlannerMaterial {
    private Integer id;           // Removido final para permitir setId
    private String title;
    private String description;
    private int priority;

    public ToDo(Integer id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public boolean hasId(Integer id) {
        return this.id.equals(id);
    }

    // **Adicionado método setId para o teste passar**
    public void setId(Integer id) {
        this.id = id;
    }

    public void updateTitle(String newTitle) {
        if (newTitle != null && !newTitle.isEmpty()) {
            this.title = newTitle;
        }
    }

    public void updateDescription(String newDescription) {
        if (newDescription != null && !newDescription.isEmpty()) {
            this.description = newDescription;
        }
    }

    public void changePriority(int newPriority) {
        if (newPriority >= 0) {
            this.priority = newPriority;
        }
    }

    public boolean containsText(String search) {
        String lowerSearch = search.toLowerCase();
        return title.toLowerCase().contains(lowerSearch) || description.toLowerCase().contains(lowerSearch);
    }

    public String toDetailedString(List<LocalDateTime> executionTimes) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.toString()).append("\n");
        sb.append(formatExecutionTimes(executionTimes));
        return sb.toString();
    }

    private String formatExecutionTimes(List<LocalDateTime> executionTimes) {
        if (executionTimes == null || executionTimes.isEmpty()) {
            return "No tracks found\n";
        }

        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (LocalDateTime ldt : executionTimes) {
            sb.append(formatter.format(ldt)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}

