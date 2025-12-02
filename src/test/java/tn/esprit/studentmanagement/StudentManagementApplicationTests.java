package tn.esprit.studentmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class StudentManagementApplicationTests {

    @Test
    void contextLoads() {
        // Ce test est volontairement vide, il vérifie seulement que le contexte Spring se charge correctement
    }



    @Test
    void simpleTest() {
        int a = 2 + 2;
        assertEquals(4, a, "2 + 2 doit faire 4");
    }

}
