package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        validateTitle(title);
        validateDescription(description);
        validateDate(date);

        this.title = title;
        this.name = title; // 'name' da superclasse Registry
        this.description = description;
        this.author = author;
        this.date = date;
    }

    // ==== Validações ====
    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
    }

    private void validateDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
    }

    // ==== Getters ====
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    // ==== Métodos públicos compatíveis com testes ====
    public void setTitle(String title) {
        rename(title);
    }

    public void setDescription(String description) {
        changeDescription(description);
    }

    public void setDate(LocalDateTime date) {
        reschedule(date);
    }

    // ==== Métodos de domínio ====
    public void rename(String newTitle) {
        validateTitle(newTitle);
        this.title = newTitle;
        this.name = newTitle;
    }

    public void changeDescription(String newDescription) {
        validateDescription(newDescription);
        this.description = newDescription;
    }

    public void reschedule(LocalDateTime newDate) {
        validateDate(newDate);
        if (newDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("New date cannot be in the past");
        }
        this.date = newDate;
    }

    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(date);
    }

    public String displayDetails() {
        return String.format("Task '%s' by %s\nDue: %s\nDescription: %s",
                title, author, date.toString(), description);
    }
}

