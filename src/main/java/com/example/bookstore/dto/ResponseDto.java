package com.example.bookstore.dto;

public class ResponseDto {
    public String message;
    public Object data;
    public String token;

    public ResponseDto(String message,Object data) {
        this.message = message;
        this.data=data;
    }
    public ResponseDto(String message,Object data,String token){
        this.message=message;
        this.data=data;
        this.token=token;
    }
}
