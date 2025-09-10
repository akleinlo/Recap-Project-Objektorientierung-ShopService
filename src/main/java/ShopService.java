import java.util.ArrayList;
import java.util.List;
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

    // COnstructor with parameters
    public  ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId);
            if (productToOrder == null) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }
            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, Order.Status.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrdersWithStatus(Order.Status status) {
        return orderRepo.getOrders().stream()
                .filter(o -> o.status() == status)
                .collect(Collectors.toList());
    }
}
