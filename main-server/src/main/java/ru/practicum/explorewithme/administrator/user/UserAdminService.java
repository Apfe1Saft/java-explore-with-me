package ru.practicum.explorewithme.administrator.user;

import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.model.user.UserDto;

import java.util.List;

public interface UserAdminService {
    List<UserDto> getUsers();

    List<UserDto> getUsers(long[] ids);

    List<UserDto> getUsers(int from, int size);

    UserDto postUser(User user);

    void deleteUser(long id);
}
