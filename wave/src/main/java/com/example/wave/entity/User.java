package com.example.wave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_discord_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "user_discord_name", nullable = false)
    private String username;

    @Column(name = "user_discord_global_name", nullable = true)
    private String globalName;

    @Column(name = "user_discord_locale", nullable = true)
    private String locale;

    @Column(name = "heart_count_koreanbots", nullable = true)
    private Integer koreanlistHeartCount;

    @Builder
    public User(String userId, String username, String globalName, String locale) {
        this.userId = userId;
        this.username = username;
        this.globalName = globalName;
        this.locale = locale;
    }

}
