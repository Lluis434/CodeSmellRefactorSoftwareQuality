package org.example.studysearch;

import org.example.studyregistry.StudyMaterial;

import java.util.List;

public class MaterialSearch implements Search<String> {

    private SearchLog searchLog = new SearchLog("Material Search");

    public MaterialSearch() {}

    @Override
    public List<String> search(String text) {
        List<String> results = performSearch(text);
        searchLog.logSearch(text);
        results.add("\n" + searchLog.getLogInfo());
        return results;
    }

    private List<String> performSearch(String text) {
        return StudyMaterial.getStudyMaterial().searchInMaterials(text);
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}

