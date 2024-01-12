package com.gdsc.hack.domain;

import com.gdsc.hack.dto.response.MapNodeGetRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MapNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "restaurant_name")
    private String name;

    private Double latitude;
    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "food_map_id")
    private FoodMap foodMap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    
    public void checkUserAndUpdateColumn(
        User user,
        String name,
        Double latitude,
        Double longitude
    ) {
        if (this.user != user) {
            throw new IllegalArgumentException("식당 수정 실패: 저자와 변경하려는 유저가 다릅니다.");
        }

        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MapNodeGetRequestDto toGetDto() {
        return MapNodeGetRequestDto
                .builder()
                .restaurantName(this.name)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .build();
    }
}
