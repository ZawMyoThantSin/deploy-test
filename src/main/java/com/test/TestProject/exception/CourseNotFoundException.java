package com.test.TestProject.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String msg){
        super(msg   );
    }
}
