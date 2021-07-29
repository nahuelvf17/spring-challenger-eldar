package com.novando.springchallengereldar.controller;

import com.novando.springchallengereldar.config.BcryptGenerator;
import com.novando.springchallengereldar.dto.LoginDto;
import com.novando.springchallengereldar.dto.UserDto;
import com.novando.springchallengereldar.entity.User;
import com.novando.springchallengereldar.service.UserServiceImpl;
import com.novando.springchallengereldar.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    BcryptGenerator bcryptGenerator;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto, BindingResult result) {

        if (result.hasErrors()) {
            return commonUtil.validar(result);
        }

        User user = new User();
        user.setPassword(bcryptGenerator.passwordEncoder(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setNombre(userDto.getNombre());
        user.setApellido(userDto.getApellido());

        try {
            userService.save(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con exito");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        final Optional<User> userDb = userService.findUserByEmail(loginDto.getEmail());

        if (!userDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no existe, debe registrarse");
        }

        if (bcryptGenerator.passwordDecoder(loginDto.getPassword(),userDb.get().getPassword()))
            return ResponseEntity.ok("Usuario logeado correctamente");

        return ResponseEntity.badRequest().body("La contraseña no coincide, ingrese la contraseña correcta, intente nuevamente");
    }
}
