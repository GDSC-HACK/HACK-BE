package com.gdsc.hack.service;

import com.gdsc.hack.domain.FoodMap;
import com.gdsc.hack.domain.MapNode;
import com.gdsc.hack.domain.Post;
import com.gdsc.hack.domain.User;
import com.gdsc.hack.dto.request.MapNodeRequestDto;
import com.gdsc.hack.dto.request.PostRequestDto;
import com.gdsc.hack.repository.FoodMapRepository;
import com.gdsc.hack.repository.MapNodeRepository;
import com.gdsc.hack.repository.PostRepository;
import com.gdsc.hack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final MapNodeRepository mapNodeRepository;
    private final FoodMapRepository foodMapRepository;

    @Transactional
    public Post writePost(PostRequestDto dto) {
        // 예외처리가 필요합니다. auth 수정되면 그 다음에 예외처리 시작
        User user = userRepository.findByEmail(dto.getEmail());
        Post post = Post
                .builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .build();

        return postRepository.save(post);
    }

    @Transactional
    public FoodMap writeFoodMap(Post post, PostRequestDto dto) {
        FoodMap foodMap = FoodMap
                .builder()
                .user(post.getUser())
                .post(post)
                .build();

        List<MapNodeRequestDto> mapNodeList = dto
                .getFoodMap()
                .getMapNodeList();
        return foodMapRepository.save(foodMap);
    }

    @Transactional
    public void writeMapNode(FoodMap foodMap, List<MapNodeRequestDto> dtoList) {
        dtoList.forEach(nodeDto -> {
            Double latitude = nodeDto.getLatitude();
            Double longitude = nodeDto.getLongitude();
            String restaurantName = nodeDto.getRestaurantName();

            MapNode mapNode = MapNode
                    .builder()
                    .name(restaurantName)
                    .latitude(latitude)
                    .longitude(longitude)
                    .foodMap(foodMap)
                    .build();

            mapNodeRepository.save(mapNode);
        });
    }

}
