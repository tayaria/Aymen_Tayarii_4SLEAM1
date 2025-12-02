package tn.esprit.studentmanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.controllers.DepartmentController;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.services.IDepartmentService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DepartmentControllerTest {

    @Mock
    private IDepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDepartment() {
        Department dep1 = new Department(); dep1.setIdDepartment(1L); dep1.setName("IT");
        Department dep2 = new Department(); dep2.setIdDepartment(2L); dep2.setName("HR");

        when(departmentService.getAllDepartments()).thenReturn(Arrays.asList(dep1, dep2));

        List<Department> result = departmentController.getAllDepartment();
        assertEquals(2, result.size());
        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    void testGetDepartmentById() {
        Department dep = new Department(); dep.setIdDepartment(1L); dep.setName("IT");
        when(departmentService.getDepartmentById(1L)).thenReturn(dep);

        Department result = departmentController.getDepartment(1L);
        assertEquals("IT", result.getName());
        verify(departmentService, times(1)).getDepartmentById(1L);
    }

    @Test
    void testCreateDepartment() {
        Department dep = new Department(); dep.setName("Finance");
        when(departmentService.saveDepartment(dep)).thenReturn(dep);

        Department result = departmentController.createDepartment(dep);
        assertEquals("Finance", result.getName());
        verify(departmentService, times(1)).saveDepartment(dep);
    }

    @Test
    void testUpdateDepartment() {
        Department dep = new Department(); dep.setName("UpdatedDept");
        when(departmentService.saveDepartment(dep)).thenReturn(dep);

        Department result = departmentController.updateDepartment(dep);
        assertEquals("UpdatedDept", result.getName());
        verify(departmentService, times(1)).saveDepartment(dep);
    }

    @Test
    void testDeleteDepartment() {
        doNothing().when(departmentService).deleteDepartment(1L);

        departmentController.deleteDepartment(1L);
        verify(departmentService, times(1)).deleteDepartment(1L);
    }
}
