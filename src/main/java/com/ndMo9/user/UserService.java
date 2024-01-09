package com.ndMo9.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserSecurityService userSecurityService;

    public void signUp(String userId, String nickname, String password1) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUserId(userId);
        siteUser.setNickname(nickname);
        siteUser.setPassword(bCryptPasswordEncoder.encode(password1));
        siteUser.setCreateDate(LocalDateTime.now());

        this.userRepository.save(siteUser);
    }

    public void login(String userId, String password1) {
        Optional<SiteUser> os = this.userRepository.findByuserId(userId);
        if (os.isEmpty()) {
            throw new RuntimeException();
        }
        if (password1.equals(os.get().getPassword())) {
            this.userSecurityService.loadUserByUsername(userId);
        }
    }
}
