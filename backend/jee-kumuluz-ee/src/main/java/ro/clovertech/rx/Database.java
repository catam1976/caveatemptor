package ro.clovertech.rx;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Benjamin Kastelic
 * @since 2.3.0
 */
public class Database {
    private static List<Customer> customers = new ArrayList<>();

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static Customer getCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId))
                return customer;
        }

        return null;
    }

    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static void deleteCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                customers.remove(customer);
                break;
            }
        }
    }
}
