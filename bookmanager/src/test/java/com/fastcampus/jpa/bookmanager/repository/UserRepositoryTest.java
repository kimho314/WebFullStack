package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Address;
import com.fastcampus.jpa.bookmanager.domain.Gender;
import com.fastcampus.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @BeforeEach
    void save(){
        List<User> newUsers = new ArrayList<>();
        newUsers.add(User.builder()
                .name("hoseop")
                .email("hoseop@gmail.com")
                .build());
        newUsers.add(User.builder()
                .name("hoseop2")
                .email("hoseop2@gmail.com")
                .build());
        newUsers.add(User.builder()
                .name("hoseop3")
                .email("hoseop3@gmail.com")
                .build());
        newUsers.add(User.builder()
                .name("hoseop4")
                .email("hoseop4@gmail.com")
                .build());
        newUsers.add(User.builder()
                .name("hoseop")
                .email("fastcampus.com")
                .build());
        userRepository.saveAll(newUsers);
    }

    @Test
//    @Transactional
    void crud(){


//        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));

//        List<User> users = userRepository.findAllById(Lists.newArrayList(1L,3L,5L));

//        users.forEach(System.out::println);

//        User user = userRepository.findById(1L).orElse(null);
//        System.out.println(user);
//
//        long count = userRepository.count();
//        System.out.println("count : " + count);
//
//        boolean exist = userRepository.existsById(1L);
//        System.out.println("exist : " + exist);
//
//        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
//
//        userRepository.findAll().forEach(System.out::println);

//        Page<User> users = userRepository.findAll(PageRequest.of(0,3));
//        System.out.println("totalElements : " + users.getTotalElements());
//        System.out.println("totalPages : " + users.getTotalPages());
//        System.out.println("numberOfElemets : " + users.getNumberOfElements());
//        System.out.println("sort : " + users.getSort());
//        System.out.println("size : " + users.getSize());
//        users.getContent().forEach(System.out::println);

        ExampleMatcher matcher = ExampleMatcher.matching()
                                               .withIgnorePaths("name")
                                               .withMatcher("email",endsWith());
        Example<User> example = Example.of(new User("ma","fastcampus.com"),matcher);
        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void select(){
        System.out.println(userRepository.findByName("hoseop"));
        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("hoseop@gmail.com", "hoseop"));
        System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("fastcampus.com", "hoseop"));

        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThan" + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));

        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());
        System.out.println("findByAddressIsNotEmpty : " + userRepository.findByAddressIsNotEmpty());

        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("hoseop", "hoseop2")));
    }

    @Test
    void pagingAndSortingTest(){
        System.out.println("findTop1ByName : " + userRepository.findTopByName("hoseop"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc : " + userRepository.findFirstByNameOrderByIdDescEmailAsc("hoseop"));
        System.out.println("findFirstByNameWithSortParam : " + userRepository.findFirstByName("hoseop", Sort.by(Sort.Order.desc("id"))));

        System.out.println("findByNameWithPaging : " + userRepository.findByName("hoseop", PageRequest.of(0,5,Sort.by(Sort.Order.desc("id")))).getContent());
    }

    @Test
    void enumTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void userHistoryTest(){
        User user = new User();
        user.setEmail("martin.fastcampus.com");
        user.setName("martin-now");

        userRepository.save(user);

        user.setName("martin-now-now");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }
}