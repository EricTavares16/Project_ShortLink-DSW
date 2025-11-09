package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.application.dto.UserDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.User;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
                .roleName(user.getRole() != null ? user.getRole().toString() : null)
                .planName(user.getPlan() != null ? user.getPlan().toString() : null)
                .profileId(user.getProfile() != null ? user.getProfile().getId() : null)
                .build();
    }

    private User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); //

        return user;
    }

    @Override
    public List<UserDTO> findAllDTO() {
        return userRepository.findAll()
                .stream()
                .map(this :: toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findDTOById(UUID id) {
        User user = userRepository.findById(id).orElse(null);
        return toDTO(user);
    }

    @Override
    public UserDTO createDTO(UserDTO userDTO) {
        User user = toEntity(userDTO);
        User saved = (User) userRepository.save(user);
        return toDTO(saved);
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
