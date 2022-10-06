package ru.practicum.explorewithme.model.user;

public class UserMapper {
    public static UserDto toUserDto(User user){
        return new UserDto(user.getEmail(),user.getId(),user.getName());
    }
    public static User toUser(UserDto userDto){
        return new User(userDto.getId(),userDto.getEmail(), userDto.getName());
    }
    public static UserShortDto toUserShortDto(User user){
        return new UserShortDto(
                user.getId(),
                user.getName()
        );
    }
}
