package com.gdsc.hack.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class MapNode {

    @Column(nullable = false, name = "restaurant_name")
    public String name;

    public Double latitude;
    public Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "food_map_id")
    public FoodMap foodMap;
}
