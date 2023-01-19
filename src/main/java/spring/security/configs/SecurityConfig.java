package spring.security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> {
            try {
                authz.requestMatchers("/products/**").hasAnyRole("MANAGER", "ADMIN", "SUPER_ADMIN")
                        .requestMatchers("/all_users/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .requestMatchers("/edit_users_list/**").hasRole("SUPER_ADMIN")
                                .and()
                                .formLogin()
                                .and()
                                .logout().clearAuthentication(true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return http.build();
    }
/*  inMemory
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("100")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("100")
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    JDBCAuthentication
 */
@Bean
public UserDetailsManager users(DataSource dataSource) {
   /* UserDetails Bob = User.withDefaultPasswordEncoder()
            .username("Bob")
            .password("100")
            .roles("MANAGER")
            .build();
    UserDetails John = User.withDefaultPasswordEncoder()
            .username("John")
            .password("200")
            .roles("ADMIN", "MANAGER")
            .build();
    UserDetails Jack = User.withDefaultPasswordEncoder()
            .username("Jack")
            .password("300")
            .roles("SUPER_ADMIN", "ADMIN", "MANAGER")
            .build(); */
    JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
    /*if (users.userExists(Bob.getUsername())) {
        users.deleteUser(Bob.getUsername());
    }
    if (users.userExists(John.getUsername())) {
        users.deleteUser(John.getUsername());
    }
    if (users.userExists(Jack.getUsername())) {
        users.deleteUser(Jack.getUsername());
    }
    users.createUser(Bob);
    users.createUser(John);
    users.createUser(Jack);*/
    return users;
}

}

