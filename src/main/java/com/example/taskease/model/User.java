package com.example.taskease.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails { // Implement UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "sso_provider")
    private String ssoProvider; //  "google"

    @Column(name = "sso_id")
    private String ssoId;

    // UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // For now, return an empty list or a default role.  We'll add roles later.
        return List.of(new SimpleGrantedAuthority("ROLE_USER")); // Example: Give all users the ROLE_USER authority
        // return Collections.emptyList(); // Or, return an empty list if you don't have roles yet.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // For simplicity, assume accounts don't expire
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // For simplicity, assume accounts are not locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // For simplicity, assume credentials don't expire
    }

    @Override
    public boolean isEnabled() {
        return true; // For simplicity, assume all users are enabled.
    }

    //getPassword and getUsername is provided by lombok
}