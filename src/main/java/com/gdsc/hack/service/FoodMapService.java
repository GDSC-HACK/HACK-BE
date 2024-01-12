package com.gdsc.hack.service;

import com.gdsc.hack.domain.FoodMap;
import com.gdsc.hack.domain.Post;
import com.gdsc.hack.dto.request.MapNodeRequestDto;
import com.gdsc.hack.dto.request.PostRequestDto;
import com.gdsc.hack.repository.FoodMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodMapService {
	private final FoodMapRepository foodMapRepository;

	@Transactional
	public FoodMap writeFoodMap(Post post, PostRequestDto dto) {
		FoodMap foodMap = FoodMap
				.builder()
				.user(post.getUser())
				.post(post)
				.build();

		return foodMapRepository.save(foodMap);
	}
}
