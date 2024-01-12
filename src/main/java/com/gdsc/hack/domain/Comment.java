package com.gdsc.hack.domain;

import com.gdsc.hack.dto.response.CommentDetailResponseDto;
import com.gdsc.hack.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public CommentDetailResponseDto toDetailDto() {
        return CommentDetailResponseDto
                .builder()
                .commentId(this.id)
                .author(this.user.getNickname())
                .content(this.content)
                .build();
    }
}
