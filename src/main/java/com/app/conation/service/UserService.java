package com.app.conation.service;

import com.app.conation.domain.City;
import com.app.conation.domain.CityRepository;
import com.app.conation.domain.User;
import com.app.conation.domain.UserRepository;
import com.app.conation.dto.SignUpRequestDto;
import com.app.conation.exception.CityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    public void userSignUp(SignUpRequestDto signUpRequestDto) {
        User signUpUser = User.builder()
            .userId(signUpRequestDto.getId())
            .nickname(signUpRequestDto.getNickname())
            .cityId(getCityByCityId(signUpRequestDto.getCityId()))
            .build();
    }

    private City getCityByCityId(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(CityNotFoundException::new);
    }
}
