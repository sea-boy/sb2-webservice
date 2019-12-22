package com.follow.book.springboot.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
//@RequiredArgsConstructor
//@AllArgsConstructor
@Setter
public class HelloResponseDto {
    private String name;
    private int amount;

    public HelloResponseDto(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
}
