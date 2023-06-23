package ru.nordclan.RestSkelleton.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nordclan.RestSkelleton.dto.UserDTO;
import ru.nordclan.RestSkelleton.repository.UserRepository;

/**
 * @author Shlokov Andrey
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserDTO(userRepository.findByEmail(email));
    }
}