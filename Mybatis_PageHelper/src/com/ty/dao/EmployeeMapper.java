package com.ty.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ty.entity.Employee;

public interface EmployeeMapper {
	public List<Employee> selectAllEmployee();
}
