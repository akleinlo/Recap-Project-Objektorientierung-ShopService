import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    // Default-Constructor
    public  ShopService() {
        this.productRepo = new ProductRepo();
        this.orderRepo = new OrderMapRepo();
    }

    // Constructor with parameters
    public  ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> maybeProduct = productRepo.getProductById(productId);
            if (maybeProduct.isEmpty()) {
                throw new IllegalArgumentException("Product with the ID: " + productId +
                        " does not exist.");
            }
            products.add(maybeProduct.get());
        }

        Order newOrder = new Order(
                UUID.randomUUID().toString(),
                products,
                Order.Status.PROCESSING,
                Instant.now());

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrdersWithStatus(Order.Status status) {
        return orderRepo.getOrders().stream()
                .filter(o -> o.status() == status)
                .collect(Collectors.toList());
    }

    public Order updateOrder(String orderID, Order.Status newStatus) {
        Optional<Order> maybeOrder = orderRepo.getOrderById(orderID);

        if (maybeOrder.isEmpty()) {
            throw new IllegalArgumentException("Order mit ID " + orderID + " nicht gefunden!");
        }

        Order existingOrder = maybeOrder.get();
        Order updatedOrder = existingOrder.withStatus(newStatus);
        orderRepo.updateOrder(updatedOrder);

        return updatedOrder;
    }
}
