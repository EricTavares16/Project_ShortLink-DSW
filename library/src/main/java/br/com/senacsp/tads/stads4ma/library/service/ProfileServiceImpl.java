package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.application.dto.ProfileDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Profile;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.ProfileRepository;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.UserRepository;
import org.springframework.stereotype.Service;
import br.com.senacsp.tads.stads4ma.library.domainmodel.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    // Convert Entity -> DTO
    private ProfileDTO toDTO(Profile profile) {
        return ProfileDTO.builder()
                .id(profile.getId())
                .userId(profile.getUser().getId())
                .fullName(profile.getFullName())
                .language(profile.getLanguage())
                .avatarUrl(profile.getAvatarUrl())
                .country(profile.getCountry())
                .themePreference(profile.getThemePreference())
                .email(profile.getEmail())
                .phone(profile.getPhone())
                .build();
    }

    // Convert DTO -> Entity
    private Profile toEntity(ProfileDTO dto, User user) {
        return Profile.builder()
                .id(dto.getId())
                .user(user)
                .fullName(dto.getFullName())
                .language(dto.getLanguage())
                .avatarUrl(dto.getAvatarUrl())
                .country(dto.getCountry())
                .themePreference(dto.getThemePreference())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }

    @Override
    public ProfileDTO create(ProfileDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Profile profile = toEntity(dto, user);

        Profile saved = profileRepository.save(profile);

        return toDTO(saved);
    }

    @Override
    public ProfileDTO update(UUID id, ProfileDTO dto) {

        Profile existing = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        existing.setUser(user);
        existing.setFullName(dto.getFullName());
        existing.setLanguage(dto.getLanguage());
        existing.setAvatarUrl(dto.getAvatarUrl());
        existing.setCountry(dto.getCountry());
        existing.setThemePreference(dto.getThemePreference());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());

        Profile updated = profileRepository.save(existing);

        return toDTO(updated);
    }

    @Override
    public List<Profile> findByThemePreference(String theme) {
        return List.of();
    }

    @Override
    public boolean deleteById(UUID id) {
        if (!profileRepository.existsById(id)) return false;
        profileRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ProfileDTO> findAll() {
        return profileRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProfileDTO> findById(UUID id) {
        return profileRepository.findById(id)
                .map(this::toDTO);
    }

    @Override
    public Optional<Profile> findByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    @Override
    public List<Profile> findByCountry(String country) {
        return List.of();
    }


    @Override
    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile update(UUID id, Profile profile) {
        profile.setId(id);
        return profileRepository.save(profile);
    }
}
