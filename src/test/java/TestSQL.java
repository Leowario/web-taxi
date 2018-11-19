import com.webtaxi.sql.SQLExecutor;
import com.webtaxi.users.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TestSQL {


    @Test
public void getUserByLoginAndPassword(){
        Customer customer = Customer.builder()
                .setLogin("roma")
                .setPassword("123abc")
                .setFirstName("Roman")
                .setLastName("Momanov")
                .setRating(5)
                .build();
        Optional<Customer> optional= SQLExecutor.selectCustomerByLoginAndPassword("roma","123abc");
        if (optional.isPresent()) {
            System.out.println(customer.toString());
        }

    }
}

