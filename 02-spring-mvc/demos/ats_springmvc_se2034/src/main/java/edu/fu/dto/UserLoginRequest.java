package edu.fu.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {
    private String fullName;
    private String password;
    private String email;
}
