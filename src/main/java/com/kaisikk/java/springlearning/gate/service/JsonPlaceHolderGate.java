package com.kaisikk.java.springlearning.gate.service;

import com.kaisikk.java.springlearning.gate.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class JsonPlaceHolderGate {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto getUserListDto() {

        UserDto userDto = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1", UserDto.class);
        return userDto;
    }

    @Async
    public CompletableFuture<UserDto> getUserListDtoAsync() throws InterruptedException {
        UserDto userDto = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1", UserDto.class);
        Thread.sleep(5000);
        return CompletableFuture.completedFuture(userDto);
    }

}
