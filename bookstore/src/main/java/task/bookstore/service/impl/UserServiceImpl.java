package task.bookstore.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import task.bookstore.dto.SignUpDto;
import task.bookstore.dto.UserDto;
import task.bookstore.exception.UserNotFoundException;
import task.bookstore.model.User;
import task.bookstore.model.enums.UserStatus;
import task.bookstore.model.enums.UserType;
import task.bookstore.repositories.UserRepository;
import task.bookstore.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto save(SignUpDto signUpDto) {
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new RuntimeException("Email is already taken!");
        }

        User user = new User();
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setUserType(UserType.USER);
        user.setUserStatus(UserStatus.ACTIVE);
        User savedUser = userRepository.save(user);

        return mapToDTO(savedUser);
    }

    @Override
    public void blockUser(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException();
        }
        User user = userOptional.get();
        user.setUserStatus(UserStatus.BLOCKED);
        userRepository.save(user);
    }

    @Override
    public void unblockUser(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException();
        }
        User user = userOptional.get();
        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    private UserDto mapToDTO(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .userType(user.getUserType())
                .userStatus(user.getUserStatus())
                .build();

    }

    private User mapToEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .userType(userDto.getUserType())
                .userStatus(userDto.getUserStatus()).build();

    }

}
