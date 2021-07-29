package com.novando.springchallengereldar.service;
import com.novando.springchallengereldar.entity.User;

import java.util.Optional;

public interface IUserService {
    public Optional<User> findById(Long id);
    public User save(User user) throws Exception;
    public void deleteById(Long id) throws Exception;
    public Optional<User> findUserByEmail(String email);
}
