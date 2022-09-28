package com.example.bank.controller;

import com.example.bank.dto.ClientDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@EnableWebMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    @Rollback(false)
    void createClient() throws Exception {

        String expected = "  {\n" +
                "            \"firstName\": \"user1\",\n" +
                "                \"lastName\": \"uuuu\",\n" +
                "                \"dateOfBirth\": \"2000-09-27\",\n" +
                "                \"job\": \"Student\",\n" +
                "                \"email\": \"user@gmail.com\",\n" +
                "                \"phoneNumber\": \"+332588888988\"\n" +
                "}";


        ClientDto newClient = ClientDto.builder()
                .firstName("user1")
                .lastName("uuuu")
                .dateOfBirth(LocalDate.of(2000, 9, 27))
                .job("Student")
                .email("user@gmail.com")
                .phoneNumber("+332588888988")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/bank/client")
                        .content(objectMapper.writeValueAsString(newClient))
                        .contentType(MediaType.APPLICATION_JSON_VALUE);


         mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().json(expected))
                .andReturn();

    }


    @Test
    @Order(2)
    @Rollback(false)
    void updateClient() throws Exception {

        String expected = "  {\n" +
                "            \"firstName\": \"user1\",\n" +
                "                \"lastName\": \"uuuu\",\n" +
                "                \"dateOfBirth\": \"2000-09-27\",\n" +
                "                \"job\": \"Student\",\n" +
                "                \"email\": \"user@gmail.com\",\n" +
                "                \"phoneNumber\": \"+3325888887822\"\n" +
                "         \n" +
                "}";

        String clientDto = "  {\n" +
                "                \"phoneNumber\": \"+3325888887822\"\n" +
                "}";


        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.patch("/bank/client/{clientId}", 1L)
                        .content(clientDto)
                        .contentType(MediaType.APPLICATION_JSON_VALUE);


        mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    @Order(3)
    @Rollback(false)
    void deleteClient() throws Exception {

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.delete("/bank/client/{clientId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE);


        mvc.perform(builder)
                .andExpect(status().isOk());
    }
}
