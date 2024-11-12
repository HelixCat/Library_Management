package com.mahdi.website.multiThread;

import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.mapper.UserMapper;
import com.mahdi.website.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MultiThread {

    private final UserMapper userMapper;

    public void main(String[] args) {

        User user = new User();
        UserDTO userDTO = userMapper.toDTO(user);
        System.out.println(user);
        System.out.println(userDTO);


    }
}
