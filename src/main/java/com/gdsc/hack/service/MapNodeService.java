package com.gdsc.hack.service;

import com.gdsc.hack.domain.FoodMap;
import com.gdsc.hack.domain.MapNode;
import com.gdsc.hack.dto.request.MapNodeRequestDto;
import com.gdsc.hack.repository.MapNodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapNodeService {
	private final MapNodeRepository mapNodeRepository;

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
