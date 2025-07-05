package org.example.studyplanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KanbanView {
    public enum State {
        TODO, DOING, DONE;
    }

    HabitTracker habitTracker = null;
    TodoTracker todoTracker = null;
    Map<State, List<PlannerMaterial>> kanban = null;

    public KanbanView(HabitTracker habitTracker, TodoTracker todoTracker) {
        this.habitTracker = habitTracker;
        this.todoTracker = todoTracker;
        this.kanban = new HashMap<>();
        this.kanban.put(State.TODO, new ArrayList<>());
        this.kanban.put(State.DOING, new ArrayList<>());
        this.kanban.put(State.DONE, new ArrayList<>());
    }

    public List<PlannerMaterial> getKanbanByState(State state) {
        return kanban.get(state);
    }

    public void addHabitToKanban(State state, Integer id) throws Exception {
        Habit toAdd = this.habitTracker.getHabitById(id);
        if (toAdd == null) {
            throw new Exception("Habit not found with id: " + id);
        }
        kanban.get(state).add(toAdd);
    }

    public void addToDoToKanban(State state, Integer id) throws Exception {
        ToDo toAdd = this.todoTracker.getToDoById(id);
        if (toAdd == null) {
            throw new Exception("ToDo not found with id: " + id);
        }
        kanban.get(state).add(toAdd);
    }

    public void removeHabitFromKanban(State state, Integer id) throws Exception {
        Habit toRemove = this.habitTracker.getHabitById(id);
        if (toRemove == null) {
            throw new Exception("No habit found with id: " + id);
        }
        kanban.get(state).remove(toRemove);
    }

    public void removeToDoFromKanban(State state, Integer id) throws Exception {
        ToDo toRemove = this.todoTracker.getToDoById(id);
        if (toRemove == null) {
            throw new Exception("No todo found with id: " + id);
        }
        kanban.get(state).remove(toRemove);
    }

    public String kanbanView() throws Exception {
        if (kanban.isEmpty()) {
            throw new Exception("No material found");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        appendStateSection(sb, "Material ToDo:", State.TODO);
        appendStateSection(sb, "Material in progress:", State.DOING);
        appendStateSection(sb, "Material completed:", State.DONE);
        sb.append("]");
        return sb.toString();
    }

    // 🔹 Método extraído: formatação de uma seção da view
    private void appendStateSection(StringBuilder sb, String title, State state) {
        sb.append(System.lineSeparator()).append(title).append(System.lineSeparator());
        appendMaterialList(sb, state);
    }

    // 🔹 Método extraído: renderização da lista de materiais
    private void appendMaterialList(StringBuilder sb, State state) {
        List<PlannerMaterial> materials = kanban.get(state);
        if (materials.isEmpty()) {
            sb.append("No material found");
        } else {
            for (PlannerMaterial material : materials) {
                sb.append(", ").append(material.toString());
            }
        }
    }
}
