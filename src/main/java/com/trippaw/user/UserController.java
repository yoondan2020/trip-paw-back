package com.trippaw.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    @GetMapping("/me")
    public Optional<User> findByProviderAndProviderId(Principal principal) {
        User user = userService.findByName(principal.getName());
        return userService.findByProviderAndProviderId(user.getProvider(), user.getProviderId());
    }
}
