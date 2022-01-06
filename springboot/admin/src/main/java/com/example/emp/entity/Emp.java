package com.example.emp.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;
@Data
public class Emp implements Serializable {
    private Integer empId;
    private String name ;
    private String sex;
    private Integer age;
    private  Double sal;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String address;
    private Integer deptId;

    private Dept dept;

}

