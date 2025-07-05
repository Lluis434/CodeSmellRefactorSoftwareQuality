package org.example.studyregistry;

import org.example.studymaterial.Reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyTaskManager {
    private static StudyTaskManager instance;
    private StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    List<Registry> registryList;
    List<String> weekResponsibilities = List.of();

    private StudyTaskManager() {
        this.registryList = new ArrayList<Registry>();
    }

    public static StudyTaskManager getStudyTaskManager() {
        if (instance == null) {
            instance = new StudyTaskManager();
        }
        return instance;
    }

    public List<String> getWeekResponsibilities() {
        return weekResponsibilities;
    }

    // Refatorado: não recebe mais long parameter list
    public void setUpWeek() {
        // Aqui você pode adicionar lógica adicional se necessário
        // Por ora, apenas preservamos os dados atribuídos
        this.weekResponsibilities = new ArrayList<>(this.weekResponsibilities);
    }

    // Refatorado: define os dados e chama o método que os processa
    public void handleSetUpWeek(List<String> stringProperties) {
        this.weekResponsibilities = new ArrayList<>(stringProperties);
        setUpWeek();
    }

    public void addRegistry(Registry registry) {
        registryList.add(registry);
    }

    public void removeRegistry(Registry registry) {
        registryList.remove(registry);
    }

    public List<Registry> getRegistryList() {
        return registryList;
    }

    public List<String> searchInRegistries(String text) {
        List<String> response = new ArrayList<>();
        for (Registry registry : registryList) {
            String mix = (registry.getName() != null ? registry.getName() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())) {
                response.add(registry.getName());
            }
        }
        return response;
    }
}

