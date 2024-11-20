package com.jtech.usermodule.service;

import com.jtech.usermodule.dto.UserRequestDTO;
import com.jtech.usermodule.dto.UserResponseDTO;
import com.jtech.usermodule.model.User;
import com.jtech.usermodule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User createUser(UserRequestDTO userRequest){
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return repository.save(user);
    }

    public Optional<User> findUserByUserName(String userName){
        return repository.findByUserName(userName);
    }

    public Optional<User> findById(Long id){
        return repository.findById(id);
    }

    public Optional<User> findByEmail(String email){return repository.findByEmail(email); }

    public List<UserResponseDTO> getAllUsers(){
        return repository.findAll().stream()
                .map(this::mapTpResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserResponseDTO> updateUser (String userName, UserRequestDTO userRequest){
        Optional<User> optionalUser = repository.findByUserName(userName);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setEmail(userRequest.getEmail());
            user.setUserName(userRequest.getPassword());
            user = repository.save(user);
            return Optional.of(mapTpResponseDTO(user));
        }
        return Optional.empty();
    }

    public boolean deleteUser(String userName){
        Optional<User> optionalUser = repository.findByUserName(userName);
        if(optionalUser.isPresent()){
            repository.delete(optionalUser.get());
            return true;
        }
        return false;
    }
    private UserResponseDTO mapTpResponseDTO(User user){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
