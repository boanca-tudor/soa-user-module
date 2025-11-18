package com.ubb.userModule.user.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDto {
    protected String email;

    protected String password;

    protected String fullName;
}
