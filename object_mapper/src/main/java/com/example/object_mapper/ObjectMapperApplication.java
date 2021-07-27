package com.example.object_mapper;

import com.example.object_mapper.dto.Car;
import com.example.object_mapper.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ObjectMapperApplication {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(ObjectMapperApplication.class, args);
        System.out.println("main");

        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("홍길동");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가1111");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가2222");
        car2.setType("SUV");

        List<Car> cars = Arrays.asList(car1, car2);
        user.setCars(cars);

        System.out.println(user.toString());

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();
        System.out.println("name : " + _name);
        System.out.println("age : " + _age);

//        JsonNode _cars = jsonNode.get("cars");
//        ArrayNode arrayNode = (ArrayNode) _cars;
        ArrayNode arrayNode = jsonNode.withArray("cars");
        List<Car> _carList = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        _carList.forEach(System.out::println);

    }

}
