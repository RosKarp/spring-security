package spring.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity                                 // все сущности хранятся в MySQL
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInWork {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "username")
        private String fullName;

        @Column(name = "password")
        private String pass;

        @Column(name = "enabled")
        private Integer enable;

        @ManyToMany
        @JoinTable(
                name = "users_roles",
                joinColumns = @JoinColumn(name = "name_user"),
                inverseJoinColumns = @JoinColumn(name = "id_role")
        )
        private List<Authority> Authorities;
}
