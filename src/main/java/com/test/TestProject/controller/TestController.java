package com.test.TestProject.controller;

import com.test.TestProject.model.CourseDTO;
import com.test.TestProject.model.HttpResponse;
import com.test.TestProject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class TestController {
    @Autowired
    private CourseService service;

    @GetMapping("/")
    public String index(){
        return "Hello World";
    }

//    @PostMapping("/save")
//    public CourseDTO save(@RequestBody CourseDTO courseDTO){
//        System.out.println("In Controller: "+courseDTO);
//        return service.save(courseDTO);
//    }

    @GetMapping("/all")
    public List<CourseDTO> getAll(){
        return service.getAll();
    }

    @PutMapping("/update/{id}")
    public CourseDTO updateById(@PathVariable("id")Integer id, @RequestBody CourseDTO courseDTO){
        return service.update(id,courseDTO);
    }
    @PatchMapping("/update")
    public void updateCourseName(@RequestParam String name, @RequestParam Integer id){
         service.updateCourseName(name, id);
    }


    @PostMapping("/find")
    public CourseDTO findByName(@RequestParam("name")String name){
        System.out.println("Name"+ name);
        return service.findByName(name);
    }

    @PostMapping("/findbyname")
    public CourseDTO findByCourseName(@RequestParam String name){
        System.out.println("Name: "+ name);
        return service.findByCourseName(name);
    }

    @GetMapping("/{id}")
    public CourseDTO getById(@PathVariable("id")int id){
        return service.getById(id);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<CourseDTO> delete(@PathVariable("id")Integer id){
        CourseDTO courseDTO = service.deleteById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>( courseDTO, headers, HttpStatus.OK);
    }

    // important
    @PostMapping("/save")
    public ResponseEntity<HttpResponse<CourseDTO>> save(@RequestBody CourseDTO courseDTO){
        System.out.println("In Controller: "+courseDTO);
        CourseDTO dto = service.save(courseDTO);
        HttpResponse<CourseDTO> response = null;
        if (dto!= null){
             response = new HttpResponse<>();
            response.setDate(new Date());
            response.setHttpStatus(HttpStatus.CREATED);
            response.setStatusCode(HttpStatus.CREATED.value());
            response.setData(dto);
            response.setMessage("User Created Successfully...");
        }else {
           response = new HttpResponse<>();
            response.setDate(new Date());
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Fail to create User...");
        }

        return new ResponseEntity<HttpResponse<CourseDTO>>(response,response.getHttpStatus());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<HttpResponse<CourseDTO>> getById(@PathVariable("id")int id){
//        CourseDTO courseDTO = service.getById(id);
//        HttpResponse response = null;
//        if (courseDTO!= null){
//            response= new HttpResponse<CourseDTO>();
//            response.setDate(new Date());
//            response.setHttpStatus(HttpStatus.OK);
//            response.setStatusCode(HttpStatus.OK.value());
//            response.setData(courseDTO);
//        }else {
//            ErrorHandling errorHandling=  new ErrorHandling();
//            CourseNotFoundException notFoundException = new CourseNotFoundException();
//            return errorHandling.handleCourseNotFoundException(notFoundException);
//        }
//        return new ResponseEntity<>(response, response.getHttpStatus());
//    }

}
