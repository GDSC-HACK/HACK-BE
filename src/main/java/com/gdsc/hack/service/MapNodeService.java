package com.gdsc.hack.service;

import com.gdsc.hack.domain.FoodMap;
import com.gdsc.hack.domain.MapNode;
import com.gdsc.hack.domain.User;
import com.gdsc.hack.dto.request.MapNodeEditRequestDto;
import com.gdsc.hack.dto.request.MapNodeRequestDto;
import com.gdsc.hack.repository.MapNodeRepository;
import com.gdsc.hack.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapNodeService {
	private final MapNodeRepository mapNodeRepository;
	private final UserRepository userRepository;

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
					.user(foodMap.getUser())
					.build();

			mapNodeRepository.save(mapNode);
		});
	}

	@Transactional
	public void editMapNode(List<MapNodeEditRequestDto> dtoList) {
		dtoList.forEach(nodeEditDto -> {
			User user = userRepository.findByEmail(nodeEditDto.getEmail())
					.orElseThrow(()-> new IllegalArgumentException("editMapNode: 유저를 찾을 수 없습니다."));

			MapNode mapNode = mapNodeRepository
					.findById(nodeEditDto.getId())
					.orElseThrow(() -> new EntityNotFoundException("식당 수정 실패: 존재하는 식당 엔티티가 아닙니다."));

			String restaurantName = nodeEditDto.getRestaurantName();
			System.out.println(restaurantName);
			Double latitude = nodeEditDto.getLatitude();
			Double longitude = nodeEditDto.getLongitude();

			mapNode.checkUserAndUpdateColumn(user, restaurantName, latitude, longitude);
		});
	}
}
