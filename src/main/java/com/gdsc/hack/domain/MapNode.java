package com.gdsc.hack.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class MapNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "restaurant_name")
    public String name;

    public Double latitude;
    public Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "food_map_id")
    public FoodMap foodMap;
}
