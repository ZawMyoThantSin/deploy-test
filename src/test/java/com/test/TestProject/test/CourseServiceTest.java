package com.test.TestProject.test;

import com.test.TestProject.entity.CourseEntity;
import com.test.TestProject.model.CourseDTO;
import com.test.TestProject.repository.CourseRepository;
import com.test.TestProject.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository repository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private CourseService service;

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
    public void save(){
        Mockito.when(mapper.map(dto, CourseEntity.class)).thenReturn(entity);
        Mockito.when(repository.save(entity)).thenReturn(entity);
        Mockito.when(mapper.map(entity, CourseDTO.class)).thenReturn(dto);

        CourseDTO resDto = service.save(dto);
        assertNotNull(resDto);
        assertEquals(dto, resDto);
        Mockito.verify(mapper , times(1)).map(dto, CourseEntity.class);
        Mockito.verify(repository , times(1)).save(entity);
        Mockito.verify(mapper, times(1)).map(entity, CourseDTO.class);

    }

}
