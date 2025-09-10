import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderListRepo implements OrderRepo{
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public Optional<Order> getOrderById(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }

    public Order addOrder(Order newOrder) {
        orders.add(newOrder);
        return newOrder;
    }

    @Override
    public Order updateOrder(Order updatedOrder) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).id().equals(updatedOrder.id())) {
                orders.set(i, updatedOrder);
                return updatedOrder;
            }
        }
        // Falls nicht vorhanden: hinzufügen und zurückgeben (oder alternativ Exception werfen)
        orders.add(updatedOrder);
        return updatedOrder;
    }

    public void removeOrder(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                orders.remove(order);
                return;
            }
        }
    }
}
