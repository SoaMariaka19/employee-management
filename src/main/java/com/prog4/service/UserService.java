package com.prog4.service;

import com.prog4.entity.Member;
import com.prog4.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository memberRepository;
    private BCryptPasswordEncoder passwordEncoder;

    private Member findById(Long id){
        return memberRepository.getById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> findUser = memberRepository.findByUsername(username);
        if (findUser.isEmpty()) throw new UsernameNotFoundException("존재하지 않는 username 입니다.");

        log.info("loadUserByUsername member.username = {}", username);
        Member member = findUser.get();
        Member newUser = new Member();
        newUser.setUsername(member.getUsername());
        newUser.setPassword(passwordEncoder.encode(member.getPassword()));
        return newUser;
    }

    public Member save(Member user){
        return this.findById(memberRepository.save(user).getId());
    }
}