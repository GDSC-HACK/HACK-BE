package com.gdsc.hack.dto;


import com.gdsc.hack.domain.Favorite;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class FavoriteDTO {
    private Long postId;
    private String email;

    @Builder
    public FavoriteDTO(Long postId, String email) {
        this.postId = postId;
        this.email = email;
    }

    public static FavoriteDTO from(Favorite favorite){
        return FavoriteDTO.builder()
                .postId(favorite.getPost().getId())
                .email(favorite.getUser().getEmail())
                .build();
    }
}