package task.bookstore.service;

import task.bookstore.dto.SignUpDto;
import task.bookstore.dto.UserDto;

public interface UserService {
    UserDto save(SignUpDto signUpDto);

    void blockUser(int userId);

    void unblockUser(int userId);
}
