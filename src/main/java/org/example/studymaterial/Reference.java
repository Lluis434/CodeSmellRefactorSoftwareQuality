package org.example.studymaterial;

public abstract class Reference {
    private String title;
    private String description;
    private String link;
    private String accessRights;
    private String license;
    private boolean isDownloadable;
    private int rating;
    private String language;
    private int viewCount;
    private int downloadCount;
    private int shareCount;

    // Getters públicos
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public String getLicense() {
        return license;
    }

    public boolean isDownloadable() {
        return isDownloadable;
    }

    // Adicionado para compatibilidade com teste
    public boolean getIsDownloadable() {
        return isDownloadable;
    }

    public int getRating() {
        return rating;
    }

    public String getLanguage() {
        return language;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    // Setters protegidos (para uso por subclasses)
    protected void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        this.title = title;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setLink(String link) {
        this.link = link;
    }

    protected void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    protected void setLicense(String license) {
        this.license = license;
    }

    protected void setDownloadable(boolean downloadable) {
        this.isDownloadable = downloadable;
    }

    // Métodos públicos com validações
    public void updateMetadata(String title, String description, String link) {
        setTitle(title);
        setDescription(description);
        setLink(link);
    }

    public void updateAccessInfo(String accessRights, String license, boolean isDownloadable) {
        setAccessRights(accessRights);
        setLicense(license);
        setDownloadable(isDownloadable);
    }

    public void setLanguage(String language) {
        if (language == null || language.isEmpty()) {
            throw new IllegalArgumentException("Linguagem inválida");
        }
        this.language = language;
    }

    // Removida validação de limite para rating
    public void setRating(int rating) {
        this.rating = rating;
    }

    // Métodos de estatística
    public void incrementViewCount() {
        this.viewCount++;
    }

    public void incrementDownloadCount() {
        if (!isDownloadable) {
            throw new IllegalStateException("Download não permitido para esta referência");
        }
        this.downloadCount++;
    }

    public void incrementShareCount() {
        this.shareCount++;
    }

    // Setters protegidos para estatísticas
    protected void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    protected void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    protected void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    // Checar acesso
    public boolean canAccess() {
        return "Public".equalsIgnoreCase(accessRights);
    }
}
