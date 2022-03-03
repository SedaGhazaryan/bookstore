package task.bookstore.dto;

import lombok.Builder;
import lombok.Data;
import task.bookstore.model.enums.UserStatus;
import task.bookstore.model.enums.UserType;

@Data
@Builder
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private UserType userType;
    private UserStatus userStatus;
}
