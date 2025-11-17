package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.application.dto.UserDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Profile;
import br.com.senacsp.tads.stads4ma.library.domainmodel.User;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.PlanRepository;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.ProfileRepository;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.RoleRepository;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PlanRepository planRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PlanRepository planRepository,
                           ProfileRepository profileRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.planRepository = planRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        List<User> resultset = new ArrayList<>();
        resultset.addAll(this.userRepository.findAll());
        return resultset;
    }

    @Override
    public User findById(UUID id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(UUID id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public User update(UUID id, User user) {
        if (!userRepository.existsById(id)) return null;
        user.setId(id);
        return (User) userRepository.save(user);
    }

/*
    @Override
    public boolean deleteById(UUID id) {
        return this.userRepository.deleteById(id);
    }*/

    @Override
    public User create(User user) {
        return (User) this.userRepository.save(user);
    }

    @Override
    public boolean existsById(UUID id) {
        return this.userRepository.existsById(id);
    }
/*
    @Override
    public User update(UUID id, User databaseUser) {
        return this.userRepository.update(databaseUser);
    }*/

    private UserDTO toDTO(User user) {
        if (user == null) return null;

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roleName(user.getRole())
                .planName(user.getPlan())
                .profileId(user.getProfile() != null ? user.getProfile().getId() : null)
                .password(user.getPassword())
                .build();
    }

    private User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        user.setRole(dto.getRoleName());
        user.setPlan(dto.getPlanName());

        return user;
    }

    @Override
    public List<UserDTO> findAllDTO() {
        return userRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findDTOById(UUID id) {
        User user = userRepository.findById(id).orElse(null);
        return toDTO(user);
    }

    @Override
    public UserDTO createDTO(UserDTO userDTO) {

        // 1) Converte DTO → Entidade
        User user = toEntity(userDTO);

        // 2)  CRIPTOGRAFA A SENHA
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 3) Salva o usuário sem profile
        User savedUser = userRepository.save(user);

        // 4) Cria um Profile automaticamente baseado no usuário criado
        Profile profile = Profile.builder()
                .user(savedUser)
                .fullName(savedUser.getName())
                .email(savedUser.getEmail())
                .language("pt-BR")
                .themePreference("light")
                .build();

        // 5) Salva o Profile
        Profile savedProfile = profileRepository.save(profile);

        // 6) Atualiza o usuário com o profile criado
        savedUser.setProfile(savedProfile);
        savedUser = userRepository.save(savedUser);

        // 7) Retorna DTO final
        return UserDTO.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .roleName(savedUser.getRole())
                .planName(savedUser.getPlan())
                .password(savedUser.getPassword()) // já criptografada
                .profileId(savedProfile.getId())
                .build();
    }


    @Override
    public UserDTO updateDTO(UUID id, UserDTO userDTO) {
        if (!userRepository.existsById(id)) return null;
        User user = toEntity(userDTO);
        user.setId(id);
        User updated = (User) userRepository.save(user);
        return toDTO(updated);
    }


}
