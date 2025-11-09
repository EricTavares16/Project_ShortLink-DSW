package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.application.dto.ProfileDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileService {

    List<ProfileDTO> findAll();

    Optional<ProfileDTO> findById(UUID id);

    Profile create(Profile profile);

    Profile update(UUID id, Profile profile);

    boolean deleteById(UUID id);

    Optional<Profile> findByEmail(String email);

    List<Profile> findByCountry(String country);
    // NOVO: Recebe DTO e Retorna DTO
    ProfileDTO create(ProfileDTO profileDTO);

    // NOVO: Recebe DTO e Retorna DTO
    ProfileDTO update(UUID id, ProfileDTO profileDTO);


    List<Profile> findByThemePreference(String theme);
}
