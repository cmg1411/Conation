package com.app.conation.service;

import com.app.conation.domain.Region;
import com.app.conation.domain.RegionRepository;
import com.app.conation.domain.User;
import com.app.conation.domain.UserRepository;
import com.app.conation.dto.SignUpRequestDto;
import com.app.conation.exception.AlreadyExistIdException;
import com.app.conation.exception.CityNotFoundException;
import com.app.conation.exception.JWTDecodeException;
import com.app.conation.exception.PasswordsNotEqualException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RegionRepository regionRepository;

    public void userSignUp(SignUpRequestDto signUpRequestDto) {
        isPasswordsEqual(signUpRequestDto);

        Optional<User> userFound = userRepository.findByNickname(signUpRequestDto.getId());
        if (userFound.isPresent()) {
            throw new AlreadyExistIdException();
        }

        User signUpUser = User.builder()
            .userId(signUpRequestDto.getId())
            .nickname(signUpRequestDto.getNickname())
            .regionId(getRegionByCityId(signUpRequestDto.getRegionId()))
            .password(signUpRequestDto.getPassword())
            .phoneNumber(signUpRequestDto.getPhoneNumber())
            .build();

        userRepository.save(signUpUser);
    }

    private void isPasswordsEqual(SignUpRequestDto signUpRequestDto) {
        if ( !signUpRequestDto.getPassword().equals(signUpRequestDto.getPasswordRepeat())) {
            throw new PasswordsNotEqualException();
        }
    }

    private Region getRegionByCityId(Long cityId) {
        return regionRepository.findById(cityId).orElseThrow(CityNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return userRepository.findById(Long.parseLong(id)).orElseThrow(JWTDecodeException::new);
    }
}
