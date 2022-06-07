package com.springjdbc.util;

import java.util.List;

import com.springjdbc.model.Student;

public interface StudentDao {
	public int insert(Student student);
	public int edit(Student student);
	public int delete(int studentId);
	public Student getStudent(int studentId);
	public List<Student> getAllStudents();
}
