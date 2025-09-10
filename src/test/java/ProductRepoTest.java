import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @org.junit.jupiter.api.Test
    void getProducts() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        repo.addProduct(new Product("1", "Apfel"));

        //WHEN
        List<Product> actual = repo.getProducts();

        //THEN
        List<Product> expected = List.of(new Product("1", "Apfel"));
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getProductById() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        Product p = new Product("1", "Apfel");
        repo.addProduct(p);

        //WHEN
        Optional<Product> actual = repo.getProductById("1");

        //THEN
        assertTrue(actual.isPresent());
        assertEquals(p, actual.get());
    }

    @org.junit.jupiter.api.Test
    void addProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        Product newProduct = new Product("2", "Banane");

        //WHEN
        Product actual = repo.addProduct(newProduct);

        //THEN
        Product expected = new Product("2", "Banane");
        assertEquals(expected, actual);
        assertTrue(repo.getProductById("2").isPresent());
        assertEquals(expected, repo.getProductById("2").get());
    }

    @org.junit.jupiter.api.Test
    void removeProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        Product p = new Product("1", "Apfel");
        repo.addProduct(p);

        //WHEN
        repo.removeProduct("1");

        //THEN
        assertTrue(repo.getProductById("1").isEmpty());
    }
}
