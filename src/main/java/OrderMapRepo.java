import java.util.*;

public class OrderMapRepo implements OrderRepo{
    private Map<String, Order> orders = new HashMap<>();

    @Override
    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Optional<Order> getOrderById(String id) {
        return Optional.ofNullable(orders.get(id)); // jetzt Optional
    }

    @Override
    public Order addOrder(Order newOrder) {
        orders.put(newOrder.id(), newOrder);
        return newOrder;
    }

    @Override
    public Order updateOrder(Order updatedOrder) {
        orders.put(updatedOrder.id(), updatedOrder);
        return updatedOrder;
    }

    @Override
    public void removeOrder(String id) {
        orders.remove(id);
    }
}
