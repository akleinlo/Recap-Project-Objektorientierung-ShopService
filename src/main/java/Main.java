import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // 1. Produkte erstellen und ins ProduktRepo packen
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("2", "Orange"));
        productRepo.addProducts(List.of(
                new Product("3", "Orange"),
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
        System.out.println();

        // Und jetzt nochmal mit dem Optional
        System.out.println("------------------ OPTIONALS ------------------");
        // Optionales Produkt abrufen
        Optional<Product> maybeProduct = productRepo.getProductById("3");

        // Überprüfen, ob Produkt existiert und Name ausgeben
        if (maybeProduct.isPresent()) {
            System.out.println("Gefunden: " + maybeProduct.get().name());
        }
        else {
            System.out.println("Produkt nicht gefunden.");
        }

        productRepo.getProductById("4").ifPresentOrElse(
                p -> System.out.println("Gefunden: " + p.name()),
                () -> System.out.println("Produkt 4 existiert nicht.")
        );

        productRepo.getProductById("5").ifPresentOrElse(
                p -> System.out.println("Gefunden: " + p.name()),
                () -> System.out.println("Produkt 5 exitiert nicht.")
        );
        System.out.println();

        // Und nun erzeugen wir einen Fehler
        System.out.println("Lasset uns einen Fehler erzeugen!");
        // ein neuer ShopService
        ShopService shopService1 = new ShopService();

        try {
            // Hier ist "99" eine Produkt-ID, die es nicht gibt
            shopService1.addOrder(List.of("99"));
        } catch(IllegalArgumentException e) {
            System.out.println("Fehler abgefangen: " + e.getMessage());
        }



    }
}
