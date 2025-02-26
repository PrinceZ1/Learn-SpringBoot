package com.princez1.learn_springboot.service;

import com.princez1.learn_springboot.dto.request.UserCreateRequest;
import com.princez1.learn_springboot.dto.request.UserUpdateRequest;
import com.princez1.learn_springboot.dto.response.ApiResponse;
import com.princez1.learn_springboot.dto.response.UserResponse;
import com.princez1.learn_springboot.entity.User;
import com.princez1.learn_springboot.exception.AppException;
import com.princez1.learn_springboot.exception.ErrorCode;
import com.princez1.learn_springboot.mapper.UserMapper;
import com.princez1.learn_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserResponse createUser(UserCreateRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);

        return userMapper.toUserResponse(userRepository.save(user));
    }
    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }
}
