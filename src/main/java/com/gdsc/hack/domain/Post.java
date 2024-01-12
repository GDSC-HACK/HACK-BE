package com.gdsc.hack.domain;

import com.gdsc.hack.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String title;
    public String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    public final List<FoodMap> foodMapList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    public User user;

    public String review;

    @OneToMany(mappedBy = "post")
    private List<Favorite> favorites = new ArrayList<>();

}
