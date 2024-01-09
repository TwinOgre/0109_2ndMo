package com.ndMo9.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void signUp(String userId, String nickname, String password1) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUserId(userId);
        siteUser.setNickname(nickname);
        siteUser.setPassword(bCryptPasswordEncoder.encode(password1));
        siteUser.setCreateDate(LocalDateTime.now());

        this.userRepository.save(siteUser);
    }
}
