import java.time.Instant;
import java.util.List;
import lombok.With;



@With
public record Order(
        String id,
        List<Product> products,
        Status status,
        Instant timestamp

) {
    public enum Status {
        PROCESSING,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }
}
