package br.com.senacsp.tads.stads4ma.library.application.dto;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Builder
public class ProfileDTO {

    private UUID id;
    private UUID userId;
    private String fullName;
    private String language;
    private String avatarUrl;
    private String country;
    private String themePreference;
    private String email;
    private String phone;

    public ProfileDTO() {
    }

    public ProfileDTO(UUID id, UUID userId, String fullName, String language, String avatarUrl,
                      String country, String themePreference, String email, String phone) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.language = language;
        this.avatarUrl = avatarUrl;
        this.country = country;
        this.themePreference = themePreference;
        this.email = email;
        this.phone = phone;
    }

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getThemePreference() { return themePreference; }
    public void setThemePreference(String themePreference) { this.themePreference = themePreference; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
