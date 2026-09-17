package com.trippaw.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserUpdateRequestDTO userUpdateRequestDTO;

    public User getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    public User findByName(String name){
        return userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    public Optional<User> findByProviderAndProviderId(String provider, String providerId) {
        return userRepository.findByProviderAndProviderId(provider, providerId);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User findByPrincipal(Principal principal) {
        Long targetId = Long.valueOf(principal.getName());

        return userRepository.findById(targetId)
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 유저입니다."));
    }

    @Transactional
    public User patchUser(User user, UserUpdateRequestDTO dto) {

        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getBirthDate() != null) user.setBirthDate(dto.getBirthDate());
        if (dto.getPhone() != null) user.setPhone(dto.getPhone());
        if (dto.getProfileImage() != null) user.setProfileImage(dto.getProfileImage());

        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
