package edu.fu.service;

import edu.fu.dto.UserLoginRequest;
import edu.fu.entities.Users;

public interface AuthService {
    Users login(Users user);

}
