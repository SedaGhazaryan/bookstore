package task.bookstore.security;

import org.springframework.security.core.authority.AuthorityUtils;
import task.bookstore.model.User;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserType().name(), user.getUserStatus().name()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
