import com.webtaxi.sql.SQLExecutor;
import com.webtaxi.users.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Optional;

public class TestSQL {


    @Test
    public void getUserByLoginAndPassword() throws SQLException {
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
            System.out.println(customer.toString());
        } else {
            throw new SQLException();
        }
        SQLExecutor.deleteCustomerByLogin("roma");
    }
}

