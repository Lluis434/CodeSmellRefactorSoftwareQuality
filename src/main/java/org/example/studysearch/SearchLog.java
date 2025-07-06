package org.example.studysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchLog {
    private final List<String> searchHistory = new ArrayList<>();
    private final Map<String, Integer> searchCount = new HashMap<>();
    private boolean isLocked = false;
    private int numUsages = 0;
    private String logName;

    public SearchLog(String logName) {
        this.logName = logName;
    }

    /**
     * Método público para compatibilidade com testes legados.
     * Adiciona ao histórico, **mas não atualiza contadores nem numUsages**.
     */
    public void addSearchHistory(String query) {
        if (isLocked) return;
        searchHistory.add(query);
    }

    /**
     * Método principal para registrar buscas no sistema,
     * atualiza histórico, contadores e número de usos.
     */
    public void logSearch(String query) {
        if (isLocked) return;
        addSearchHistory(query);
        searchCount.put(query, searchCount.getOrDefault(query, 0) + 1);
        numUsages++;
    }

    // Retorna cópia para proteger estado interno
    public List<String> getSearchHistory() {
        return new ArrayList<>(searchHistory);
    }

    // Retorna cópia para proteger estado interno
    public Map<String, Integer> getSearchCount() {
        return new HashMap<>(searchCount);
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        this.isLocked = locked;
    }

    public int getNumUsages() {
        return numUsages;
    }

    public void setNumUsages(int numUsages) {
        this.numUsages = numUsages;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }
}
