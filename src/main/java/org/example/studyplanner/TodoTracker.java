package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TodoTracker {
    private List<ToDo> toDos = new ArrayList<>();
    private Map<Integer, List<LocalDateTime>> tracker;
    private Integer nextId;
    private static TodoTracker instance;

    private TodoTracker() {
        this.tracker = new HashMap<>();
        this.toDos = new ArrayList<>();
        this.nextId = 1;
    }

    public static TodoTracker getInstance() {
        if (instance == null) {
            instance = new TodoTracker();
        }
        return instance;
    }

    // ✅ REFATORADO: Extração de responsabilidades do método toString para métodos auxiliares
    @Override
    public String toString() {
        if (toDos.isEmpty()) {
            return "No ToDos found";
        }

        StringBuilder str = new StringBuilder();
        for (ToDo toDo : toDos) {
            appendToDoInfo(str, toDo); // Extraído para melhorar legibilidade
        }
        return str.toString();
    }

    // ✅ Método auxiliar: monta informações de um único ToDo
    private void appendToDoInfo(StringBuilder str, ToDo toDo) {
        str.append(toDo.toString()).append("\n");

        List<LocalDateTime> executionTimes = tracker.get(toDo.getId());
        if (executionTimes == null || executionTimes.isEmpty()) {
            str.append("No tracks found\n");
        } else {
            appendExecutionTimes(str, executionTimes); // Extraído formatação de datas
        }
    }

    // ✅ Método auxiliar: formata e adiciona datas de execução
    private void appendExecutionTimes(StringBuilder str, List<LocalDateTime> times) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (LocalDateTime time : times) {
            str.append(formatter.format(time)).append("\n");
        }
    }

    public void addToDoExecutionTime(Integer id) {
        List<LocalDateTime> et = tracker.computeIfAbsent(id, k -> new ArrayList<>());
        LocalDateTime now = LocalDateTime.now();
        et.add(now);
    }

    public List<ToDo> getToDos() {
        return toDos;
    }

    public ToDo getToDoById(Integer id) {
        for (ToDo toDo : toDos) {
            if (toDo.getId() == id) {
                return toDo;
            }
        }
        return null;
    }

    public Integer addToDo(String title, String description, Integer priority) {
        ToDo toAdd = new ToDo(nextId, title, description, priority);
        nextId++;
        this.toDos.add(toAdd);
        return toAdd.getId();
    }

    public void removeToDo(Integer id) {
        toDos.removeIf(toDo -> toDo.getId() == id);
    }

    public List<ToDo> sortTodosByPriority() {
        List<ToDo> sortedToDos = new ArrayList<>(toDos);
        sortedToDos.sort(Comparator.comparingInt(ToDo::getPriority));
        return sortedToDos;
    }

    public List<String> searchInTodos(String search) {
        List<String> todos = new ArrayList<>();
        for (ToDo toDo : toDos) {
            if (toDo.getTitle().toLowerCase().contains(search.toLowerCase()) ||
                    toDo.getDescription().toLowerCase().contains(search.toLowerCase())) {
                todos.add(toDo.toString());
            }
        }
        return todos;
    }
}
