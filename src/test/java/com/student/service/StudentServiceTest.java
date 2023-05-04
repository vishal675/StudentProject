package com.student.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.student.entity.Student;
import com.student.repo.StudentRepository;
import com.student.service.impl.StudentServiceImpl;

@SpringBootTest(classes = StudentServiceImpl.class)
public class StudentServiceTest {

	@MockBean
	private StudentRepository studentRepository;

	@Autowired
	private StudentService studentService;

	@Test
	public void getAllStudentTest() {
		Student student = new Student();
		student.setEmail("ahsahg.com");
		student.setFirstName("name");
		student.setId((long) 1);
		student.setLastName("lst");

		List<Student> lstud = new ArrayList<>();
		lstud.add(student);
		when(studentRepository.findAll()).thenReturn(lstud);
		List<Student> lstudnew = studentService.getAllStudents();
		assertThat(lstudnew).isNotEmpty();

	}

	@Test
	public void saveStudentTest() {
		Student student = new Student();
		student.setEmail("ahsahg.com");
		student.setFirstName("name");
		student.setId((long) 1);
		student.setLastName("lst");

		when(studentRepository.save(Mockito.any())).thenReturn(student);
		Student studnew = studentService.saveStudent(student);
		assertThat(studnew.getFirstName()).isNotEmpty();

	}

	@Test
	public void getStudentByIdTest() {
		Student student = new Student();
		student.setEmail("ahsahg.com");
		student.setFirstName("name");
		student.setId((long) 1);
		student.setLastName("lst");

		when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(student));
		Student studnew = studentService.getStudentById((long) 1);
		assertThat(studnew.getFirstName()).isNotEmpty();

	}

	@Test
	public void updateStudentTest() {
		Student student = new Student();
		student.setEmail("ahsahg.com");
		student.setFirstName("name");
		student.setId((long) 1);
		student.setLastName("lst");

		when(studentRepository.save(Mockito.any())).thenReturn(student);
		Student studnew = studentService.updateStudent(student);
		assertThat(studnew.getFirstName()).isNotEmpty();

	}

	@Test
	public void deleteStudentTest() {
		Long id = 123L;
		studentService.deleteStudentById(id);
		verify(studentRepository, times(1)).deleteById(id);

	}
}
