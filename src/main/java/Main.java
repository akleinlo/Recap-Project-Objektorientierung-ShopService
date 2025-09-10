import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // 1. Produkte erstellen und ins ProduktRepo packen
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("2", "Orange"));
        productRepo.addProducts(List.of(
                new Product("3", "Zitrone"),
                new Product("4", "Kokosnuss"))
        );

        System.out.println();
        System.out.println("Produkte im Shop: ");
        productRepo.getProducts().forEach(p -> System.out.println(p.name()));
        System.out.println();

        // 2. Order-Repo vorbereiten
        OrderRepo orderRepo = new OrderMapRepo();

        // 3. ShopService initalisieren (mit ProductRepo und OrderMapRepo)
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // 4. Bestellungen hinzufügen
        Order order1 = shopService.addOrder(List.of("2", "3"));
        System.out.println("Hinzugefügte Bestellung:");
        System.out.println("ID: " + order1.id() + ", Status: " + order1.status() +
                ", Timestamp: " + order1.timestamp());
        System.out.println();

        // 5. Alle Bestellungen mit Status PROCESSING abrufen
        List<Order> processingOrders = shopService.getOrdersWithStatus(Order.Status.PROCESSING);
        System.out.println("Bestellungen mit Status PROCESSING: ");
        processingOrders.forEach(o -> {
            System.out.println("Order ID: " + o.id());
            o.products().forEach(p -> System.out.println(" - " + p.name()));
        });
        System.out.println();

        // --- Lombok updateOrder testen ---
        System.out.println("Lombok updateOrder testen:");
        try {
            Order updatedOrder = shopService.updateOrder(order1.id(), Order.Status.SHIPPED);
            System.out.println("Bestellung aktualisiert:");
            System.out.println("ID: " + updatedOrder.id() + ", Neuer Status: " + updatedOrder.status());
        } catch (IllegalArgumentException e) {
            System.out.println("Fehler beim Update: " + e.getMessage());
        }

        // --- Exception testen für falsche orderID ---
        System.out.println("Update mit falscher Order-ID testen:");
        try {
            shopService.updateOrder("35647853647834fsdkhdjsf34", Order.Status.SHIPPED);
        } catch (IllegalArgumentException e) {
            System.out.println("Fehler abgefangen: " + e.getMessage());
        }

        System.out.println();
        System.out.println("------------------ OPTIONALS ------------------");
        // Optionales Produkt abrufen
        productRepo.getProductById("3").ifPresentOrElse(
                p -> System.out.println("Gefunden: " + p.name()),
                () -> System.out.println("Produkt 3 existiert nicht.")
        );

        productRepo.getProductById("4").ifPresentOrElse(
                p -> System.out.println("Gefunden: " + p.name()),
                () -> System.out.println("Produkt 4 existiert nicht.")
        );

        productRepo.getProductById("5").ifPresentOrElse(
                p -> System.out.println("Gefunden: " + p.name()),
                () -> System.out.println("Produkt 5 existiert nicht.")
        );
        System.out.println();

        // Fehlerhafte Produkt-ID testen
        System.out.println("Lasset uns einen Fehler erzeugen!");
        ShopService shopService1 = new ShopService();
        try {
            shopService1.addOrder(List.of("99"));
        } catch (IllegalArgumentException e) {
            System.out.println("Fehler abgefangen: " + e.getMessage());
        }
    }
}
