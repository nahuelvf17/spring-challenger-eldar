package com.novando.springchallengereldar.service;

import com.novando.springchallengereldar.entity.User;
import com.novando.springchallengereldar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) throws Exception {

        final Optional<User> checkUser = findUserByEmail(user.getEmail());
        if(checkUser.isPresent()) {
            throw new Exception("Ya existe un usuario registrado con este email");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        userRepository.deleteById(id);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
