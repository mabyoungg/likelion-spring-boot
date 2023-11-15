package com.example.likelionspringboot.global.resultData;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResultData<T> {
    private String resultCode;
    private String message;
    private T data;
}
