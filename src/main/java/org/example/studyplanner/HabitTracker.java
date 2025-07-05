package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HabitTracker {
    private List<Habit> habits;
    private Map<Integer, List<LocalDateTime>> tracker;
    private Integer nextId;

    private static HabitTracker instance;

    public static HabitTracker getHabitTracker() {
        if (instance == null) {
            instance = new HabitTracker();
        }
        return instance;
    }

    private HabitTracker() {
        this.habits = new ArrayList<>();
        this.tracker = new HashMap<>();
        this.nextId = 1;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        for (Habit habit : habits) {
            response.append(habit.toString()).append(", ");
        }
        return "Habits: " + response.toString();
    }

    public Habit getHabitById(Integer id) {
        return this.habits.stream()
                .filter(habit -> Objects.equals(habit.getId(), id))
                .findFirst().orElse(null);
    }

    public List<Habit> getHabits() {
        return this.habits;
    }

    public String formatHabitDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(formatter);
    }

    public List<Integer> getTrackerKeys() {
        return new ArrayList<>(this.tracker.keySet());
    }

    // ✅ Nova versão simplificada — Replace Parameter With Method (extrai do contexto)
    public int handleAddHabitAdapter(List<String> stringProps, List<Integer> intProps, boolean isConcluded) {
        String name = stringProps.get(0);
        String motivation = stringProps.get(1);
        LocalTime dedication = getTimeFromProps(intProps);
        LocalDateTime start = getDateTimeFromProps(intProps);

        return addHabit(name, motivation, dedication, start, isConcluded);
    }

    // ✅ Método principal refatorado (menos parâmetros)
    public int addHabit(String name, String motivation, LocalTime dedication, LocalDateTime startDate, boolean isConcluded) {
        Habit habit = new Habit(name, motivation, dedication, this.nextId, startDate, isConcluded);
        this.habits.add(habit);
        this.tracker.put(nextId, new ArrayList<>());
        return nextId++;
    }

    // ✅ Métodos auxiliares privados extraindo dados
    private LocalTime getTimeFromProps(List<Integer> props) {
        int minutes = props.get(0);
        int hours = props.get(1);
        return LocalTime.of(hours, minutes);
    }

    private LocalDateTime getDateTimeFromProps(List<Integer> props) {
        return LocalDateTime.of(
                props.get(2),  // year
                props.get(3),  // month
                props.get(4),  // day
                props.get(5),  // hour
                props.get(6),  // minute
                props.get(7)   // second
        );
    }

    public int addHabit(String name, String motivation) {
        Habit habit = new Habit(name, motivation, this.nextId);
        this.habits.add(habit);
        this.tracker.put(nextId, new ArrayList<>());
        return nextId++;
    }

    public void addHabitRecord(Integer id) {
        tracker.get(id).add(LocalDateTime.now());
    }

    public void toggleConcludeHabit(Integer id) {
        for (Habit habit : this.habits) {
            if (habit.getId().equals(id)) {
                habit.setIsConcluded(!habit.getIsConcluded());
            }
        }
    }

    public void removeHabit(Integer id) {
        this.habits.removeIf(habit -> habit.getId().equals(id));
        this.tracker.remove(id);
    }

    public List<LocalDateTime> getHabitRecords(Integer id) {
        return this.tracker.get(id);
    }

    public List<String> searchInHabits(String search) {
        List<String> result = new ArrayList<>();
        for (Habit habit : this.habits) {
            if (habit.getName().toLowerCase().contains(search.toLowerCase()) ||
                    habit.getMotivation().toLowerCase().contains(search.toLowerCase())) {
                result.add(habit.toString());
            }
        }
        return result;
    }
}

