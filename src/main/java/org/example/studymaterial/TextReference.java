package org.example.studymaterial;

public class TextReference extends Reference {
    private int wordCount;
    private String format;

    public TextReference(String title, String language, int wordCount, String format, String accessRights) {
        this.wordCount = wordCount;
        this.format = format;
        updateMetadata(title, null, null);
        setLanguage(language);
        updateAccessInfo(accessRights, null, false);
    }

    public void editAccess(String accessRights, String format, int wordCount) {
        updateAccessInfo(accessRights, null, isDownloadable());
        this.format = format;
        this.wordCount = wordCount;
    }

    public boolean handleTextAccess() {
        if (!"Public".equals(getAccessRights())) {
            return false;
        } else if (!"pdf".equalsIgnoreCase(this.format)) {
            return false;
        } else if (this.wordCount == 0) {
            return false;
        }
        return true;
    }
}

