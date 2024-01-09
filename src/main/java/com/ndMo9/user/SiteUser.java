package com.ndMo9.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String userId;

    @Column(unique = true)
    private String nickname;

    @Column
    private String password;

    @CreatedDate
    private LocalDateTime createDate;

    private LocalDateTime modifyDate;
}
