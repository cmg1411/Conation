package com.app.conation.service;

import com.app.conation.domain.City;
import com.app.conation.domain.CityRepository;
import com.app.conation.domain.User;
import com.app.conation.domain.UserRepository;
import com.app.conation.dto.SignUpRequestDto;
import com.app.conation.exception.CityNotFoundException;
import com.app.conation.exception.PasswordsNotEqualException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    public void userSignUp(SignUpRequestDto signUpRequestDto) {
        if ( !signUpRequestDto.getPassword().equals(signUpRequestDto.getPasswordRepeat())) {
            throw new PasswordsNotEqualException();
        }

        User signUpUser = User.builder()
            .userId(signUpRequestDto.getId())
            .nickname(signUpRequestDto.getNickname())
            .cityId(getCityByCityId(signUpRequestDto.getCityId()))
            .password(signUpRequestDto.getPassword())
            .phoneNumber(signUpRequestDto.getPhoneNumber())
            .build();

        userRepository.save(signUpUser);
    }

    private City getCityByCityId(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(CityNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return userRepository.findById(Integer.parseInt(id)).orElseThrow(JWTDecodeException::new);
    }
}
