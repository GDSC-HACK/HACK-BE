package com.gdsc.hack.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String email;
    private String password;

<<<<<<< HEAD
    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites=new ArrayList<>();
=======
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    public final List<Post> postList = new ArrayList<>();
>>>>>>> 99069baaac2dddae3edb29d3849d409c787e6404
}
