package com.ndMo9.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserSecurityService userSecurityService;

    public void signUp(String username, String nickname, String password1) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setNickname(nickname);
        siteUser.setPassword(passwordEncoder.encode(password1));
        siteUser.setCreateDate(LocalDateTime.now());

        this.userRepository.save(siteUser);
    }

    public void login(String username, String password1) {
        Optional<SiteUser> os = this.userRepository.findByusername(username);
        if (os.isEmpty()) {
            throw new RuntimeException();
        }
        if (password1.equals(os.get().getPassword())) {
            this.userSecurityService.loadUserByUsername(username);
        }
    }
}
