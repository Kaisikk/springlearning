package com.kaisikk.java.springlearning.gate.service;

import com.kaisikk.java.springlearning.gate.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JsonPlaceHolderGate {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto getUserListDto() {

        UserDto userDto = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1", UserDto.class);
//        response = (UserListDto) gson.fromJson(jsonResponse, UserListDto.class);
        return userDto;
    }

}
