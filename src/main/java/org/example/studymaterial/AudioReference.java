package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }

    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality){
        this.audioQuality = quality;
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality){
        return switch (quality.toLowerCase()) {
            case "low" -> AudioQuality.LOW;
            case "medium" -> AudioQuality.MEDIUM;
            case "high" -> AudioQuality.HIGH;
            case "very_high" -> AudioQuality.VERY_HIGH;
            default -> null;
        };
    }

    public void setAudioQuality(AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
    }

    // Método original dividido em dois:
    public void editAudio(AudioQuality audioQuality, boolean isDownloadable){
        this.setAudioQuality(audioQuality);
        this.setDownloadable(isDownloadable);
        editAudio();
    }

    // Novo método que recupera os parâmetros diretamente do objeto
    private void editAudio() {
        editBasic(this.getTitle(), this.getDescription(), this.getLink());
        this.setAccessRights(this.getAccessRights());
        this.setLicense(this.getLicense());
        editVideoAttributes(this.getRating(), this.getLanguage(), this.getViewCount(), this.getShareCount());
    }

    // Adapta os parâmetros reduzidos para preencher o objeto antes da edição
    public void editAudioAdapter(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality, boolean isDownloadable){
        this.setTitle(properties.get(0));
        this.setDescription(properties.get(1));
        this.setLink(properties.get(2));
        this.setAccessRights(properties.get(3));
        this.setLicense(properties.get(4));
        this.setLanguage(properties.get(5));
        this.setRating(intProperties.get(0));
        this.setViewCount(intProperties.get(1));
        this.setShareCount(intProperties.get(2));
        editAudio(audioQuality, isDownloadable);
    }

    private void editVideoAttributes(int rating, String language, int viewCount, int shareCount){
        this.setRating(rating);
        this.setShareCount(shareCount);
        this.setViewCount(viewCount);
        this.setLanguage(language);
    }

    public void editBasic(String title, String description, String link){
        this.setTitle(title);
        this.setDescription(description);
        this.setLink(link);
    }
}
