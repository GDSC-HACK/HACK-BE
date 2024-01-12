package com.gdsc.hack.domain;

import com.gdsc.hack.dto.response.FoodMapGetResponseDto;
import com.gdsc.hack.dto.response.MapNodeGetRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    public User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "post_id")
    public Post post;

    @OneToMany(mappedBy = "foodMap", cascade = CascadeType.REMOVE)
    public final List<MapNode> mapNodeList = new ArrayList<>();

    public FoodMapGetResponseDto toGetDto() {
        List<MapNodeGetRequestDto> mapNodeGetRequestDtoList = this.mapNodeList
                .stream()
                .map(MapNode::toGetDto)
                .toList();

        return FoodMapGetResponseDto
                .builder()
                .mapNodeList(mapNodeGetRequestDtoList)
                .build();
    }
}
