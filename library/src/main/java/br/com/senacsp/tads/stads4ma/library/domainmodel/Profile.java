package br.com.senacsp.tads.stads4ma.library.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "PROFILES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user; // FK para User

    @Column(name = "FULL_NAME", length = 100)
    private String fullName;

    @Column(name = "LANGUAGE", length = 10)
    private String language; // Ex: "pt-BR"

    @Column(name = "AVATAR_URL", length = 255)
    private String avatarUrl;

    @Column(name = "COUNTRY", length = 50)
    private String country;

    @Column(name = "THEME_PREFERENCE", length = 20)
    private String themePreference; // Ex: "dark", "light"

    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @Column(name = "PHONE", length = 20)
    private String phone;
}
