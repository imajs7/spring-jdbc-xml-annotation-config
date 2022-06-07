package com.springjdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.springjdbc.model.Student;
import com.springjdbc.util.StudentDao;

@Component("studentDao") // required only when autowired
public class StudentDaoImplementation implements StudentDao{
	
	@Autowired // required only when autowired
	private JdbcTemplate jdbcTemplate;

	public int insert(Student student) {
		String sql = "INSERT INTO student(id, name, city) VALUES (?,?,?)";
		int r = this.jdbcTemplate.update(sql, student.getId(), student.getName(), student.getCity());
		return r;
	}

	public int edit(Student student) {
		String sql = "UPDATE student SET name = ?, city = ? WHERE id = ?";
		int r = this.jdbcTemplate.update(sql, student.getName(), student.getCity(), student.getId());
		return r;
	}
	
	public int delete(int studentId) {
		String sql = "DELETE FROM student WHERE id = ?";
		int r = this.jdbcTemplate.update(sql, studentId);
		return r;
	}
	
	public Student getStudent(int studentId) {
		// selecting single student record
		String sql = "SELECT * FROM student WHERE id = ?";
		RowMapper<Student> rowMapper = new RowMapperImplementation();
		Student student = jdbcTemplate.queryForObject(sql, rowMapper, studentId);
		return student;
	}
	
	public List<Student> getAllStudents() {
		// selecting all students
		String sql = "SELECT * FROM student";
		RowMapper<Student> rowMapper = new RowMapperImplementation();
		List<Student> studentList = jdbcTemplate.query(sql, rowMapper);
		return studentList;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
