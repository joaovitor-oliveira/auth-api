package com.joaovitor.authapi.dto.register;

import com.joaovitor.authapi.domain.user.UserRole;

public record RegisterResponseDTO(String username, UserRole role) {
}
