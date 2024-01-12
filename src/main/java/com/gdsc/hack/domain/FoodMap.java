package com.gdsc.hack.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class FoodMap {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    public User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "post_id")
    public Post post;

    @OneToMany(mappedBy = "foodMap", cascade = CascadeType.REMOVE)
    public List<MapNode> mapNodeList = new ArrayList<>();
}
