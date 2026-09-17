package com.trippaw.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // U-006
    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    // U-007
    @PatchMapping("/{userId}")
    public User patchUser(@PathVariable Long userId, @RequestBody UserUpdateRequestDTO dto) {
        User targetUser = userService.findById(userId);
        return userService.patchUser(targetUser, dto);
    }

    // U-008
    @DeleteMapping("/{userId}")
    public void delectUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    // U-009
    @PatchMapping("/me")
    public User patchMe(@RequestBody UserUpdateRequestDTO dto, Principal principal) {
        User targetUser = userService.findByPrincipal(principal);
        return userService.patchUser(targetUser, dto);
    }

    // U-010
    @DeleteMapping("/me")
    public void deleteMe(Principal principal) {
        User targetUser = userService.findByPrincipal(principal);
        userService.deleteUser(targetUser.getId());
    }

    // U-011
    @GetMapping("/me")
    public User findByProviderAndProviderId(Principal principal) {
        return userService.findByPrincipal(principal);
    }
}
