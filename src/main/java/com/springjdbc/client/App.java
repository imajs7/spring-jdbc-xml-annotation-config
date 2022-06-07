package com.springjdbc.client;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springjdbc.model.Student;
import com.springjdbc.util.StudentDao;

public class App {
	
	static ApplicationContext context = new ClassPathXmlApplicationContext("com/springjdbc/util/config.xml"); 
	
    public static void main( String[] args ) {
    	
    	Scanner input = new Scanner(System.in);
    	
    	System.out.println("Enter option (1 - Insert | 2 - Edit | 3 - Delete | 4 - Show | 5 - Show All | 6 - Exit)");
    	int choice = input.nextInt();
    	
    	while(choice != 6) {
    		
    		switch (choice) {
    		
				case 1:
					//insert new record
					System.out.print("Enter new Id: ");
					int id = input.nextInt();
					System.out.print("Enter new First Name: ");
					String name = input.next();
					System.out.print("Enter new Last Name: ");
					name = name + " " + input.next();
					System.out.print("Enter new City: ");
					String city = input.next();
			        Student student1 = newStudent(id, name, city);
			        int insertRecord = insertRecord(student1);
			        System.out.println("Number of record added: " + insertRecord);
					break;
					
				case 2:
					// edit old record
					System.out.print("Enter Id to edit: ");
					int id1 = input.nextInt();
					System.out.print("Enter new First Name: ");
					String name1 = input.next();
					System.out.print("Enter new Last Name: ");
					name1 = name1 + " " + input.next();
					System.out.print("Enter new City: ");
					String city1 = input.next();
			        int editRecord = editRecord(id1, name1, city1);
			        System.out.println("Number of record update: " + editRecord);
					break;
					
				case 3:
					// delete old record
					System.out.println("Enter the id to delete: ");
					int id2 = input.nextInt();
			    	int deleteRecord = deleteRecord(id2);
			    	System.out.println("Number of records deleted: " + deleteRecord);
					break;
					
				case 4:
					// select records from table
					System.out.println("Enter the id to show: ");
					int id3 = input.nextInt();
			    	System.out.println( showStudentRecord(id3).toString() );
					break;
					
				case 5:
					// show all records
					showStudentRecord();
					break;
		
				case 6:
					// exit
					break;
			}	
    		
    		System.out.println("Enter option (1 - Insert | 2 - Edit | 3 - Delete | 4 - Show | 5 - Show All | 6 - Exit)");
	    	choice = input.nextInt();
    		
    	}
    	
    	
    	input.close();
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
