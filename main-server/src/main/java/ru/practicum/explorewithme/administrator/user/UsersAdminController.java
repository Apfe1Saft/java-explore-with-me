package ru.practicum.explorewithme.administrator.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.user.UserDto;
import ru.practicum.explorewithme.model.user.UserMapper;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/users")
@Slf4j
@RequiredArgsConstructor
public class UsersAdminController {
    private final UserAdminService service;

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(name = "ids", defaultValue = "[]") long[] ids,
                                  @RequestParam(name = "from", defaultValue = "0") int from,
                                  @RequestParam(name = "size", defaultValue = "0") int size) {
        log.debug("Admin: GET /admin/users request with ids: {}, from: {}, size: {}", ids, from, size);
        if (ids.length > 0) {
            return service.getUsers(ids);
        }
        if (from != 0 && size != 0) {
            return service.getUsers(from, size);
        }
        return service.getUsers();
    }

    @PostMapping
    public UserDto postUser(@RequestBody final UserDto userDto) {
        log.debug("Admin: POST /admin/users request ");
        return service.postUser(UserMapper.toUser(userDto));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") long id) {
        log.debug("Admin: DELETE /admin/users/{userId} request with userId: " + id);
        service.deleteUser(id);
    }
}
