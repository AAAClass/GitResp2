package com.example.emp.service;

import com.example.emp.entity.Dept;
import com.example.emp.entity.Emp;
import com.example.emp.vo.EmpQuery;

import java.util.List;

public interface EmpService {

    List<Emp> getEmpList(EmpQuery param);

    Long countEmpList(EmpQuery param);
    void addEmp(Emp emp);

    List<Dept> getAllDept();

    void deleteEmpByIds(String ids);

    Emp getEmpById(Integer id);

    void updateEmp(Emp emp);
}
