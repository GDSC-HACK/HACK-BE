package com.gdsc.hack.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ResponseDto<T> {
	private final String message;
	private final T result;

	public static <T> ResponseDto<T> create() {
		return new ResponseDto<>(null, null);
	}

	public static <T> ResponseDto<T> create(String message) {
		return new ResponseDto<>(message, null);
	}

	public static <T> ResponseDto<T> create(String message, T result) {
		return new ResponseDto<>(message, result);
	}
}
