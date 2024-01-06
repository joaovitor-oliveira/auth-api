package com.joaovitor.authapi.dto.register;

import com.joaovitor.authapi.domain.user.UserRole;

public record RegisterDTO(String username, String password, UserRole role) {
}
