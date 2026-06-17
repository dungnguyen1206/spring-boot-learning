package edu.fu.service;

import edu.fu.dao.AuthRepository;
import edu.fu.entities.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthRepository authRepository;

    @Override
    public Users login(Users user) {
       return authRepository.login(user.getEmail(), user.getPassword());
    }
}
