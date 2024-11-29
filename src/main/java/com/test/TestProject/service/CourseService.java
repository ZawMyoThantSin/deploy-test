package com.test.TestProject.service;

import com.test.TestProject.entity.CourseEntity;
import com.test.TestProject.exception.CourseNotFoundException;
import com.test.TestProject.model.CourseDTO;
import com.test.TestProject.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository repository;
    @Autowired
    private ModelMapper mapper;

    public CourseDTO save(CourseDTO courseDTO){
//        change and save
        CourseEntity course = mapper.map(courseDTO, CourseEntity.class);
        course = repository.save(course);
//        change again the courseDTO
        courseDTO = mapper.map(course,CourseDTO.class);

        return courseDTO;
    }

    public List<CourseDTO> getAll(){
        List<CourseEntity> ce = repository.findAll();
        List<CourseDTO> courseDTOS = ce.stream()
                .map(course->mapper.map(course, CourseDTO.class)).toList();

        return courseDTOS;
    }

    public CourseDTO getById(Integer id ){
        CourseEntity course =  repository.findById(id)
                .orElseThrow(()-> new CourseNotFoundException("Class not found!"));
       return  mapper.map(course, CourseDTO.class);
    }

    public CourseDTO update(Integer id, CourseDTO courseDTO){
        CourseDTO course = getById(id);
        if (courseDTO.getName() != null){
            course.setName(courseDTO.getName());
        }
        if (courseDTO.getDuration() != null){
            course.setDuration(courseDTO.getDuration());
        }
        if (courseDTO.getPrice() != 0){
            course.setPrice(courseDTO.getPrice());
        }
        CourseEntity courseEntity = mapper.map(course, CourseEntity.class);
        courseEntity = repository.save(courseEntity);

        return mapper.map(courseEntity, CourseDTO.class);
    }

    public CourseDTO deleteById(Integer id){
        CourseEntity course = repository.findById(id).orElseThrow(()->new CourseNotFoundException("course not found"));
        repository.deleteById(id);
        return mapper.map(course,CourseDTO.class);
    }
    public void updateCourseName(String name, Integer id){
        repository.updateCourseName(name, id);

    }

    public CourseDTO findByName(String name){
        CourseEntity course = repository.findByName(name);
        System.out.println(course);
        return mapper.map(course,CourseDTO.class);
    }
    public CourseDTO findByCourseName(String name){
        CourseEntity course = repository.finCourseByName(name);
        System.out.println(course);
        return mapper.map(course, CourseDTO.class);
    }
}
