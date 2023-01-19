package spring.security.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.security.entities.UserInWork;
import spring.security.repositories.ProductRepository;
import spring.security.repositories.UsersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;

    public List<UserInWork> findAll() {
        return usersRepository.findAll();
    }
}
