import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Produkte erstellen und ins ProduktRepo packen
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("2", "Orange"));
        productRepo.addProducts(List.of(new Product("3", "Orange"),
                new Product("4", "Kokosnuss")));

        System.out.println();

        System.out.println("Produkte im Shop: ");
        productRepo.getProducts().forEach(p -> System.out.println(p.name()));

        // 2. Order-Repo vorbereiten
        OrderRepo orderRepo = new OrderMapRepo();

        // 3. ShopService initalisieren (mit ProductRepo und OrderMapRepo)
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // 4. Bestellungen hinzufügen
        shopService.addOrder(List.of("1", "2", "3"));

        // 5. Alle Bestellungen mit Status PROCESSING abrufen
        List<Order> processingOrders = shopService.getOrdersWithStatus(Order.Status.PROCESSING);
        System.out.println("Bestellungen mit Status PROCESSING: ");
        System.out.println(processingOrders);
        System.out.println("Und nochmal schön Zeile nach Zeile: ");
        processingOrders.forEach(o -> {
            System.out.println("Order ID: " + o.id());
            o.products().forEach(p -> System.out.println(" - " + p.name()));
        });

    }
}
