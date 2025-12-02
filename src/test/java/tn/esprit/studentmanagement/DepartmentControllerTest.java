package tn.esprit.studentmanagement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import tn.esprit.studentmanagement.controllers.DepartmentController;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.services.IDepartmentService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)

class DepartmentControllerTest {

    @Mock
    private IDepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @Test
    void testGetAllDepartments() {
        Department dep1 = new Department();
        dep1.setIdDepartment(1L);
        dep1.setName("IT");
        dep1.setHead("head1");
        dep1.setLocation("test1");
        dep1.setPhone("55455455");
        Department dep2 = new Department();
        dep2.setIdDepartment(2L);
        dep2.setName("HR");
        dep2.setHead("head1");
        dep2.setLocation("test1");
        dep2.setPhone("55455455");


        Mockito.when(departmentService.getAllDepartments()).thenReturn(Arrays.asList(dep1, dep2));

        List<Department> result = departmentController.getAllDepartment();
        assertEquals(2, result.size());
    }
}
