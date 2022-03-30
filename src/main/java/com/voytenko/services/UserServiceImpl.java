package com.voytenko.services;

import com.voytenko.config.MailConfig;
import com.voytenko.dto.CreateUserDto;
import com.voytenko.dto.UserDto;
import com.voytenko.model.User;
import com.voytenko.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JavaMailSender javaMailSender;
    private final MailConfig mailConfig;


    @Override
    public UserDto findById(Integer id) {
        return UserDto.fromModel(userRepository.findById(id).get());
    }

    @Override
    public List<UserDto> findAll() {
        return UserDto.fromModel(userRepository.findAll());
    }

    @Override
    public UserDto signUp(CreateUserDto createUserDto, String url) {

        User savedUser = userRepository.save(
                User.builder()
                        .email(createUserDto.getEmail())
                        .name(createUserDto.getName())
                        .password(encoder.encode(createUserDto.getPassword()))
                        .verificationCode(RandomString.make(64))
                        .build());
        sendVerificationMail(savedUser.getEmail(), url, savedUser.getName(), savedUser.getVerificationCode());
        return UserDto.fromModel(savedUser);
    }

    @Override
    public UserDto findByEmail(String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            System.out.println(currentUserName);
        }
        return userRepository.getByEmail(email).stream().map(UserDto::fromModel).findFirst().orElse(null);
    }

    @Override
    public boolean verify(String verificationCode) {
        Optional<User> user = userRepository.findByVerificationCode( verificationCode);
        if (user.isPresent()){
            user.get().setVerificationCode(null);
            user.get().setEnabled(true);
            userRepository.save(user.get());
            return true;
        }
        return false;
    }

    @Override
    public void sendVerificationMail(String email, String url, String name, String code) {
        String from = mailConfig.getFrom();
        String sender = mailConfig.getSender();
        String subject = mailConfig.getSubject();
        String content = mailConfig.getContent();


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(from, sender);

            helper.setTo(email);
            helper.setSubject(subject);

            content = content.replace("{name}", name);
            content = content.replace("{url}", url + "/verification?code=" + code);

            helper.setText(content, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }

    @Override
    public List<UserDto> getAll() {
        return UserDto.fromModel(userRepository.findAll());
    }
}
