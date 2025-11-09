package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.application.dto.ProfileDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Profile;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.ProfileRepository;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.ProfileRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileRepositoryImpl profileRepositoryImpl;

    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileRepositoryImpl profileRepositoryImpl) {
        this.profileRepository = profileRepository;
        this.profileRepositoryImpl = profileRepositoryImpl;
    }
/*
    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> findById(UUID id) {
        return profileRepositoryImpl.findByIdWithUser(id);
    }*/

    @Override
    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile update(UUID id, Profile profile) {
        if (!profileRepository.existsById(id)) {
            return null;
        }
        profile.setId(id);
        return profileRepository.save(profile);
    }

    @Override
    public boolean deleteById(UUID id) {
        if (!profileRepository.existsById(id)) {
            return false;
        }
        profileRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Profile> findByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    @Override
    public List<Profile> findByCountry(String country) {
        return profileRepositoryImpl.findByCountry(country);
    }

    @Override
    public ProfileDTO create(ProfileDTO profileDTO) {
        return null;
    }

    @Override
    public ProfileDTO update(UUID id, ProfileDTO profileDTO) {
        return null;
    }

    @Override
    public List<Profile> findByThemePreference(String theme) {
        return profileRepositoryImpl.findByThemePreference(theme);
    }

    private ProfileDTO toDTO(Profile profile) {
        return ProfileDTO.builder()
                .id(profile.getId())
                .fullName(profile.getFullName())
                .language(profile.getLanguage())
                .avatarUrl(profile.getAvatarUrl())
                .country(profile.getCountry())
                .themePreference(profile.getThemePreference())
                .email(profile.getEmail())
                .phone(profile.getPhone())
                .build();
    }

    private Profile toEntity(ProfileDTO dto) {
        return Profile.builder()
                .id(dto.getId())
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
    public List<ProfileDTO> findAll() {
        return profileRepository.findAll()
                .stream()
                .map(this::toDTO) // conversão manual
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProfileDTO> findById(UUID id) {
        return profileRepository.findById(id)
                .map(this::toDTO);
    }


    public ProfileDTO save(ProfileDTO profileDTO) {
        Profile profile = toEntity(profileDTO);
          Profile saved = profileRepository.save(profile);
        return toDTO(saved);
    }



}
