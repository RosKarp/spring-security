package spring.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.security.entities.UserInWork;

@Repository
public interface UsersRepository extends JpaRepository<UserInWork, String> {
}
