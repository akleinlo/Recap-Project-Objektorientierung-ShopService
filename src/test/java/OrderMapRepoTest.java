import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapRepoTest {

    @Test
    void getOrders() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();
        Instant fixedTime = Instant.parse("2025-09-10T14:28:11.601170Z");

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), Order.Status.PROCESSING, fixedTime);
        repo.addOrder(newOrder);

        //WHEN
        List<Order> actual = repo.getOrders();

        //THEN
        List<Order> expected = new ArrayList<>();
        Product product1 = new Product("1", "Apfel");
        expected.add(new Order("1", List.of(product1), Order.Status.PROCESSING, fixedTime));

        assertEquals(actual, expected);
    }

    @Test
    void getOrderById() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();
        Instant fixedTime = Instant.parse("2025-09-10T14:28:11.601170Z");

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), Order.Status.PROCESSING, fixedTime);
        repo.addOrder(newOrder);

        //WHEN
        Optional<Order> actual = repo.getOrderById("1");

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1), Order.Status.PROCESSING, fixedTime);

        assertEquals(actual, expected);
    }

    @Test
    void addOrder() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();
        Instant fixedTime = Instant.parse("2025-09-10T14:28:11.601170Z");
        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), Order.Status.PROCESSING, fixedTime);

        //WHEN
        Order actual = repo.addOrder(newOrder);

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1), Order.Status.PROCESSING, fixedTime);
        assertEquals(actual, expected);
        assertEquals(repo.getOrderById("1"), expected);
    }

    @Test
    void removeOrder() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();

        //WHEN
        repo.removeOrder("1");

        //THEN
        assertNull(repo.getOrderById("1"));
    }
}
