package entity_test;

import org.example.spring_hibernate.entity.Employee;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class EmployeeTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private static Employee employee1;
    private static Employee employee2;
    private static Employee employee3;

    @BeforeClass
    public static void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        employee1 = new Employee("Masha", "Ivanova", "Sale", 800);
        employee1.setId(1);
        employee2 = new Employee("Masha", "Ivanova", "Sale", 800);
        employee2.setId(1);
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void equalsMethodTest() {
        Assert.assertEquals(employee1,employee2);
        employee1.setName("Misha");
        Assert.assertNotEquals(employee1,employee2);
    }

    @Test
    public void shouldHaveNoViolations() {

        employee3 = new Employee("Masha", "Ivanova", "Sale", 800);

        Set<ConstraintViolation<Employee>> violations
                = validator.validate(employee3);


        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalid() {

        employee3 = new Employee("M", "", "   ", 10);
        Set<ConstraintViolation<Employee>> violations
                = validator.validate(employee3);

        Assert.assertEquals(violations.size(), 4);

    }

}
