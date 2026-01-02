package com.luna.globalexceptiondemo;

import com.luna.globalexceptiondemo.controller.ProductController;
import com.luna.globalexceptiondemo.dto.GetProductRespDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.nio.charset.StandardCharsets;

@WebMvcTest(ProductController.class)
@AutoConfigureRestTestClient
public class ProductControllerTest {
    @Autowired
    private RestTestClient restTestClient;


    @Test
    void getProductByIdTest_SuccessResponse() {
        restTestClient.get()
                .uri("/product/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .exchange()
                .expectBody(GetProductRespDto.class)
                .isEqualTo(new GetProductRespDto(1L));
    }

    @Test
    void getProductByIdTest_ResourceNotFoundException() {
        RestTestClient.ResponseSpec responseSpec = restTestClient.get()
                .uri("/product/{id}", 2)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .exchange();
        responseSpec.expectStatus().isNotFound();
        responseSpec.expectBody().jsonPath("$.detail").isEqualTo("Product with ID 2 not found.");
    }
}
