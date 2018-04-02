package br.com.skip.the.dishes.security.application.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/user")
    public User teste() {
        return new User("augusto", "senha");
    }

}
