package com.example.filrouge_back.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String pseudo;

    private String lastName;

    @Column(unique = true)
    private String mail;

    private String password;

    private Date birthday;

    @ManyToOne()
    @JoinColumn(name = "Role_id")
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "user_genre", // Nom de la table de liaison
            joinColumns = @JoinColumn(name = "userEntity_id"), // Clé étrangère de UserEntity
            inverseJoinColumns = @JoinColumn(name = "genre_id") // Clé étrangère de Genre
    )
    private List<Genre> genres;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(List.of(new SimpleGrantedAuthority(role.getRoleName().toString())));
    }

    /**
     * Returns the property used to authenticate the user
     * Here, the authentication property is mail
     * @return user mail (not null)
     */
    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


