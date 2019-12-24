package com.follow.book.springboot.web.dto;

import lombok.Getter;

@Getter
public class PostsSaveResponseDto {
    private Long id;

    public static PostsSaveResponseDto of(Long id) {
        PostsSaveResponseDto dto = new PostsSaveResponseDto();
        dto.id = id;
        return dto;
    }
}
