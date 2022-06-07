package com.springjdbc.client;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springjdbc.model.Student;
import com.springjdbc.util.StudentDao;

/**
 * Contains main method
 *
 */
public class App {
	
	static ApplicationContext context = new ClassPathXmlApplicationContext("com/springjdbc/util/config.xml"); 
	
    public static void main( String[] args ) {
    	
    	int choice = 5;
    	
    	switch (choice) {
			case 1:
				//insert new record
		        Student student1 = newStudent(5, "Vikash", "Vijag");
		        int insertRecord = insertRecord(student1);
		        System.out.println("Number of record added: " + insertRecord);
				break;
				
			case 2:
				// edit old record
		        int editRecord = editRecord(2, "Anupama Singhdeo", "Saltlake");
		        System.out.println("Number of record update: " + editRecord);
				break;
				
			case 3:
				// delete old record
		    	int deleteRecord = deleteRecord(4);
		    	System.out.println("Number of records deleted: " + deleteRecord);
				break;
				
			case 4:
				// select records from table
		    	System.out.println( showStudentRecord(2).toString() );
				break;
	
			default:
				// select all records from table
		    	showStudentRecord();
				break;
		}	
    	
    }
    
    public static int insertRecord(Student student) {
    	StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
    	return studentDao.insert( student );
    }
    
    public static int editRecord(int studentId, String name, String city) {
    	StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
    	Student student = studentDao.getStudent(studentId);
    	student.setName(name);
    	student.setCity(city);
    	return studentDao.edit( student );
    }
    
    public static int deleteRecord(int studentId) {
    	StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
    	return studentDao.delete( studentId );
    }
    
    public static Student showStudentRecord(int studentId) {
    	StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
    	Student student = studentDao.getStudent(studentId);
    	return student;
    }
    
    public static void showStudentRecord() {
    	StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
    	List<Student> studentList = studentDao.getAllStudents();
    	showList(studentList);
    }
    
    public static Student newStudent(int id, String name, String city) {
    	Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setCity(city);
        return student;
    }
    
    public static void showList(List<Student> studentList) {
		for(Student s : studentList) {
			System.out.println( s.toString() );
		}
	}
    
}
