package com.thiagosol.saas.service;

import com.thiagosol.saas.dto.UserDTO;
import com.thiagosol.saas.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> listAll() {
        return userRepository.listAll()
                .stream()
                .map(user -> new UserDTO(user.name, user.email))
                .collect(Collectors.toList());
    }

    /*@Transactional
    public UserDTO create(UserCreateDTO dto) {
        User user = new User();
        user.name = dto.name();
        user.email = dto.email();
        userRepository.persist(user);
        return new UserDTO(user.id, user.name, user.email);
    }

    @Transactional
    public UserDTO update(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.name = dto.name();
        user.email = dto.email();
        return new UserDTO(user.id, user.name, user.email);
    }*/
}
