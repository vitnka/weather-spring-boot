package com.voytenko.model;

import lombok.*;


import javax.persistence.*;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    @Size(min = 8, max = 64, message = "Password should contains from 8 to 64 symbols")
    @Column(nullable = false, length = 64)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Resource> resources;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
