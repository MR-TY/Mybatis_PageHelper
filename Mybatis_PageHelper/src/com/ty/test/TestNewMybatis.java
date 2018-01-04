package com.ty.test;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.dao.EmployeeMapper;
import com.ty.entity.Department;
import com.ty.entity.Employee;

public class TestNewMybatis {
	InputStream inputStream = null;
	SqlSession session = null;
	SqlSession session1 = null;
	 {
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sessionFactory.openSession();
		session1 = sessionFactory.openSession();
	}
	 @Test
	 public void testAllEmployee(){
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		//-------要第几页，多少条数据-------
		Page page = PageHelper.startPage(1, 1);
		List<Employee> employees = employeeMapper.selectAllEmployee();
		for (Employee employee : employees) {
			System.out.println(employee);
		}
		System.out.println("当前的页码："+page.getPageNum());
		System.out.println("总的记录数："+page.getTotal());
		System.out.println("每页的记录数："+page.getPageSize());
		System.out.println("总页码：："+page.getPages());
		System.out.println("-------------------------------------");
		//--------使用pageInfo包装查询的结果
	/*	PageInfo<Employee> info = new PageInfo<>(employees);
		System.out.println("当前的页码1："+info.getPageNum());
		System.out.println("总的记录数1："+info.getTotal());
		System.out.println("每页的记录数1："+info.getPageSize());
		System.out.println("总页码1："+info.getPages());
		System.out.println("是否第一页:"+info.isIsFirstPage());*/
		
		//--------进行连续显示你多少页------------
		PageInfo<Employee> info = new PageInfo<>(employees,2);
		System.out.println("当前的页码1："+info.getPageNum());
		System.out.println("总的记录数1："+info.getTotal());
		System.out.println("每页的记录数1："+info.getPageSize());
		System.out.println("总页码1："+info.getPages());
		System.out.println("是否第一页:"+info.isIsFirstPage());
		int[] nums = info.getNavigatepageNums();
		for (int i = 0; i < nums.length; i++) {
			System.out.println("页码："+nums[i]);
		}
	 }
}
