package ru.practicum.explorewithme.administrator.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.administrator.exception.NotFoundException;
import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.model.user.UserDto;
import ru.practicum.explorewithme.model.user.UserMapper;
import ru.practicum.explorewithme.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Getter
public class UserAdminServiceImpl implements UserAdminService {
    private final UserRepository repository;

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : repository.findAll()) {
            userDtoList.add(UserMapper.toUserDto(user));
        }
        return userDtoList;
    }

    @Override
    public List<UserDto> getUsers(long[] ids) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (long i : ids) {
            if (repository.findById(i).isPresent())
                userDtoList.add(UserMapper.toUserDto(repository.findById(i).get()));
        }
        return userDtoList;
    }

    @Override
    public List<UserDto> getUsers(int from, int size) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : repository.findAll(PageRequest.of(from, size, Sort.by("id").descending())).
                stream().collect(Collectors.toList())) {
            userDtoList.add(UserMapper.toUserDto(user));
        }
        return userDtoList;
    }

    @Override
    public UserDto postUser(User user) {
        repository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public void deleteUser(long id) {
        if (!repository.existsById((long) id)) {
            throw new NotFoundException("");
        }
        repository.deleteById(id);
    }
}
