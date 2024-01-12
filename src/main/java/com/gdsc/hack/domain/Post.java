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
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private final List<FoodMap> foodMapList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private String review;

    public void checkUserAndUpdateColumn(
        User user,
        String title,
        String content
    ) {
        if (this.user != user) {
            throw new IllegalArgumentException("Post 수정 실패: Post 작성자와 변경하려는 유저가 다릅니다.");
        }

        this.title = title;
        this.content = content;
    }

    @OneToMany(mappedBy = "post")
    private List<Favorite> favorites = new ArrayList<>();
}
