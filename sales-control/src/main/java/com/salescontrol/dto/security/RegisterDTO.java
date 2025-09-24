package com.salescontrol.dto.security;

import com.salescontrol.model.security.UsersRole;

public record RegisterDTO(String login, String password, UsersRole role) {
}
