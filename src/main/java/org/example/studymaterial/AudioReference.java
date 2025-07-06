package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }

    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality) {
        this.audioQuality = quality;
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public void setAudioQuality(AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
    }

    // ✅ Adicionado para compatibilidade com códigos antigos
    public void editBasic(String title, String description, String link) {
        updateMetadata(title, description, link);
    }

    public static AudioQuality audioQualityAdapter(String quality) {
        return switch (quality.toLowerCase()) {
            case "low" -> AudioQuality.LOW;
            case "medium" -> AudioQuality.MEDIUM;
            case "high" -> AudioQuality.HIGH;
            case "very_high" -> AudioQuality.VERY_HIGH;
            default -> null;
        };
    }

    public void editAudio(
            AudioQuality audioQuality,
            boolean isDownloadable,
            String title,
            String description,
            String link,
            String accessRights,
            String license,
            String language,
            int rating,
            int viewCount,
            int shareCount
    ) {
        updateMetadata(title, description, link);
        updateAccessInfo(accessRights, license, isDownloadable);
        setAudioQuality(audioQuality);
        setLanguage(language);
        setRating(rating);  // PASSAR O RATING DIRETO, SEM LIMITE AQUI
        setViewCount(viewCount);
        setShareCount(shareCount);
    }

    public void editAudioAdapter(
            List<String> properties,
            List<Integer> intProperties,
            AudioQuality audioQuality,
            boolean isDownloadable
    ) {
        this.editAudio(
                audioQuality,
                isDownloadable,
                properties.get(0),  // title
                properties.get(1),  // description
                properties.get(2),  // link
                properties.get(3),  // accessRights
                properties.get(4),  // license
                properties.get(5),  // language
                intProperties.get(0), // rating
                intProperties.get(1), // viewCount
                intProperties.get(2)  // shareCount
        );
    }
}

