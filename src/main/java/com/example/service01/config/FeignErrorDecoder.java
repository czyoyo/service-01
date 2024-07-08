package com.example.service01.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Component
@RequiredArgsConstructor
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        return switch (response.status()) {
            case 400 ->
                // 클라이언트의 요청이 부적절한 경우.
                new ResponseStatusException(HttpStatus.valueOf(response.status()), "400 error");
            case 404 ->
                // 클라이언트가 요청한 자원을 서버에서 찾을 수 없는 경우.
                new ResponseStatusException(HttpStatus.valueOf(response.status()), "404 error");
            default -> new Exception(response.reason());
        };
    }
}
