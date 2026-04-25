package com.example.service;

import com.example.dto.skillDto.SkillResponseDto;
import com.example.dto.userDto.UserCreateDto;
import com.example.dto.userDto.UserDto;
import com.example.dto.userDto.UserUpdateDto;
import com.example.exceptions.ErrorType;
import com.example.exceptions.MyProjectException;
import com.example.mapper.UserMapper;
import com.example.model.Users;
import com.example.repository.UserRepository;
import com.example.repository.UserSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;


    public UserDto register(UserCreateDto dto) {
        repository.findByPhoneNumber(dto.getPhoneNumber()).ifPresent(users -> {
            throw new MyProjectException(ErrorType.PHONE_ALREADY_REGISTERED);
        });
        Users entity = mapper.toEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (entity.getTimeBalans() == null) {
            entity.setTimeBalans(60);
        }
        Users save = repository.save(entity);
        return mapper.toDto(save);
    }

    public UserDto update(UserUpdateDto dto, Long id) {
        Users users = repository.findById(id).orElseThrow(
                () -> new MyProjectException(ErrorType.USER_NOT_FOUND)
        );

       // mapper.updateEntity(dto, users);

        Users updateUser = repository.save(users);
        return mapper.toDto(updateUser);
    }

    public Page<UserDto> getAllUsers(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Users> users = repository.searchAllWithSkills(keyword == null ? "" : keyword, pageable);
        return users.map(mapper::toDto);
    }


    public void deleteUser(Long id) {
        if (!repository.existsById(id)){
            throw new MyProjectException(ErrorType.USER_NOT_FOUND);
        }
        repository.deleteById(id);
    }
    public UserDto getUserById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(
                () -> new MyProjectException(ErrorType.USER_NOT_FOUND)
        ));
    }

    public UserDto findByPhoneNumber(String number) {
        Users users = repository.findByPhoneNumber(number).orElseThrow(
                () -> new MyProjectException(ErrorType.PHONE_ALREADY_REGISTERED)
        );
        return mapper.toDto(users);
    }
}
