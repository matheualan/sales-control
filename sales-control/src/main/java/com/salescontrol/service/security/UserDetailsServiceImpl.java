package com.salescontrol.service.security;

import com.salescontrol.repository.security.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
//        return usersRepository.findByLogin(username);
        return Optional.ofNullable(usersRepository.findByLogin(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

}