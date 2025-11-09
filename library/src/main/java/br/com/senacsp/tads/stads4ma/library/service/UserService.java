package br.com.senacsp.tads.stads4ma.library.service;

import br.com.senacsp.tads.stads4ma.library.application.dto.UserDTO;
import br.com.senacsp.tads.stads4ma.library.domainmodel.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    public List<User> findAll();

    public User findById(UUID id);

    public boolean deleteById(UUID id);


    public User create(User user);

    boolean existsById(UUID id);

    User update(UUID id, User databaseUser);

    List<UserDTO> findAllDTO();

    UserDTO findDTOById(UUID id);

    UserDTO createDTO(UserDTO userDTO);

    UserDTO updateDTO(UUID id, UserDTO userDTO);

}
