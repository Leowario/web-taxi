import com.webtaxi.sql.SQLExecutor;
import com.webtaxi.users.Customer;
import com.webtaxi.users.Driver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Optional;
import java.util.PriorityQueue;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Vitalii Usatyi
 */
@DisplayName("JDBC API should:")
class JdbcTest {
    @Test
    @DisplayName("get User by login")
    void getUserByLoginAndPassword() throws SQLException {
        Customer roman = Customer.builder()
                .setLogin("roma")
                .setPassword("123abc")
                .setFirstName("Roman")
                .setLastName("Momanov")
                .setRating(5)
                .build();
        SQLExecutor.addCustomer(roman);
        Optional<Customer> optional = SQLExecutor.selectCustomerByLoginAndPassword("roma", "123abc");
        if (optional.isPresent()) {
            Customer customer = optional.get();
            Assertions.assertEquals("Roman", customer.getFirstName());
            Assertions.assertEquals("Momanov", customer.getLastName());
            Assertions.assertEquals("roma", customer.getLogin());
        } else {
            throw new SQLException();
        }
        SQLExecutor.deleteCustomerByLogin("roma");
    }

    @Test
    @DisplayName("get all available drivers by car class")
    void getAvailableDriversByCarClass() throws SQLException {
        Optional<PriorityQueue<Driver>> optional = SQLExecutor.selectAllAvailableDriversByCarClass("standard");
        if (!optional.isPresent()) {
            throw new SQLException();
        }
        PriorityQueue<Driver> drivers = optional.get();
        while (drivers.iterator().hasNext()) {
            Driver currentDriver = checkNotNull(drivers.poll());
            System.out.print(currentDriver.getFirstName() + " || ");
            System.out.print(currentDriver.getCar().getCarClass() + " || ");
            System.out.print(currentDriver.getRating() + " || ");
            System.out.println();
        }
    }
}

