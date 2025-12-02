package tn.esprit.studentmanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.controllers.StudentController;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.services.IStudentService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @Mock
    private IStudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() {
        Student s1 = new Student(); s1.setIdStudent(1L); s1.setEmail("Aymen@esprit.tn");
        Student s2 = new Student(); s2.setIdStudent(2L); s2.setEmail("Samar@esprit.tn");

        when(studentService.getAllStudents()).thenReturn(Arrays.asList(s1, s2));

        List<Student> result = studentController.getAllStudents();
        assertEquals(2, result.size());
        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    void testGetStudentById() {
        Student s = new Student(); s.setIdStudent(1L); s.setAddress("Aymen@esprit.tn");
        when(studentService.getStudentById(1L)).thenReturn(s);

        Student result = studentController.getStudent(1L);
        assertEquals("Aymen@esprit.tn", result.getAddress());
        verify(studentService, times(1)).getStudentById(1L);
    }

    @Test
    void testCreateStudent() {
        Student s = new Student(); s.setAddress("NewStudent");
        when(studentService.saveStudent(s)).thenReturn(s);

        Student result = studentController.createStudent(s);
        assertEquals("NewStudent", result.getAddress());
        verify(studentService, times(1)).saveStudent(s);
    }

    @Test
    void testUpdateStudent() {
        Student s = new Student(); s.setAddress("UpdatedStudent");
        when(studentService.saveStudent(s)).thenReturn(s);

        Student result = studentController.updateStudent(s);
        assertEquals("UpdatedStudent", result.getAddress());
        verify(studentService, times(1)).saveStudent(s);
    }

    @Test
    void testDeleteStudent() {
        doNothing().when(studentService).deleteStudent(1L);

        studentController.deleteStudent(1L);
        verify(studentService, times(1)).deleteStudent(1L);
    }
}
