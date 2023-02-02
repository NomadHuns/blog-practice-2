package shop.mtcoding.blog.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.model.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User login(String username, String password) {
        User principal = userRepository.findByUsernameAndPassword(username, password);
        if (principal == null) {
            return null;
        }
        return principal;
    }

    public int join(String username, String password, String email) {
        int check = checkUsername(username);
        if (check != 1) {
            return -1;
        }
        int result = userRepository.insert(username, password, email);
        return result;
    }

    public int checkUsername(String username) {
        User principal = userRepository.findByUsername(username);
        if (principal != null) {
            return -1;
        }
        return 1;
    }
}
