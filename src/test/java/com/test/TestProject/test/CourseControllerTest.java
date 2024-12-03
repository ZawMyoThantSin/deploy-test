package com.test.TestProject.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.TestProject.controller.TestController;
import com.test.TestProject.entity.CourseEntity;
import com.test.TestProject.model.CourseDTO;
import com.test.TestProject.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {
    @Mock
    private CourseService service;

    @InjectMocks
    private TestController controller;

    @Mock
    private MockMvc mockMvc;

    private CourseDTO dto;
    private CourseEntity entity;
    @BeforeEach
    public void setUp(){
        dto = new CourseDTO();
        dto.setId(1);
        dto.setName("Java");
        dto.setDuration("3months");
        dto.setPrice(1500);

        entity= new CourseEntity();
        entity.setId(1);
        entity.setName("Java");
        entity.setDuration("3months");
        entity.setPrice(1500);
    }

    @Test
    public void save() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        Mockito.when(service.save(dto)).thenReturn(dto);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dto);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/course/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(HttpStatus.CREATED.value() , result.getResponse().getStatus());
    }
}
