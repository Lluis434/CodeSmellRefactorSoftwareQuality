package org.example.studymaterial;

public class VideoReference extends Reference {
    private boolean isAvailable;
    private String resolution;
    private String frameRate;
    private String videoFormat;

    // Construtor simplificado usando updateMetadata
    public VideoReference(String title, String description){
        updateMetadata(title, description, null);  // link = null por enquanto
    }

    // Construtor completo usando updateMetadata e updateAccessInfo
    public VideoReference(boolean isAvailable, String title, String description, String resolution, String frameRate, String videoFormat, String accessRights){
        this.isAvailable = isAvailable;
        this.resolution = resolution;
        this.frameRate = frameRate;
        this.videoFormat = videoFormat;
        updateMetadata(title, description, null);
        updateAccessInfo(accessRights, null, false);  // license = null e isDownloadable = false por padrão
    }

    // Método para editar disponibilidade e download
    public void editAvailability(boolean isAvailable, boolean isDownloadable){
        this.isAvailable = isAvailable;
        updateAccessInfo(getAccessRights(), getLicense(), isDownloadable); // mantem os direitos e licença atuais, só altera isDownloadable
    }

    public boolean handleStreamAvailability(){
        if(!isAvailable){
            return false;
        } else if(!isDownloadable()){
            return false;
        }
        return true;
    }

    // Getters e setters para atributos específicos de vídeo, se desejar
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(String frameRate) {
        this.frameRate = frameRate;
    }

    public String getVideoFormat() {
        return videoFormat;
    }

    public void setVideoFormat(String videoFormat) {
        this.videoFormat = videoFormat;
    }
}

