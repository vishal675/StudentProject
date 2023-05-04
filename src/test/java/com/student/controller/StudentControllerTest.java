package com.student.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

import com.student.entity.Student;
import com.student.service.StudentService;

@SpringBootTest(classes = StudentController.class)
class StudentControllerTest {
	@Autowired
	private StudentController studentController;

	@MockBean
	private StudentService studentService;

	@Test
	void testListStudents() {
		// Create a mock Model object
		Model model = mock(Model.class);

		// Create a sample list of students
		List<Student> students = new ArrayList<>();
		students.add(new Student("visha", "kumar", "asdas.com"));
		students.add(new Student("Jane", "Singh", "sadsa.com"));

		when(studentService.getAllStudents()).thenReturn(students);

		// Call the listStudents method and capture the returned view name
		String viewName = studentController.listStudents(model);

		// Verify that the Model object was correctly populated with the list of
		// students
		verify(model, times(1)).addAttribute("students", students);

		// Verify that the view name returned by the listStudents method is correct
		assertEquals("students", viewName);
	}

	@Test
	void createStudentFormtest() {
		// Create a mock Model object
		Model model = mock(Model.class);

		// Call the listStudents method and capture the returned view name
		String viewName = studentController.createStudentForm(model);

		// Verify that the Model object was correctly populated with the list of
		// students
		verify(model, times(1)).addAttribute(Mockito.eq("student"), Mockito.any(Student.class));

		// Verify that the view name returned by the listStudents method is correct
		assertEquals("create_student", viewName);
	}
}