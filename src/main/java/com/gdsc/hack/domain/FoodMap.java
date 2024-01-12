package com.gdsc.hack.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
public class FoodMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    public User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "post_id")
    public Post post;

    @OneToMany(mappedBy = "foodMap", cascade = CascadeType.REMOVE)
    public final List<MapNode> mapNodeList = new ArrayList<>();
}
