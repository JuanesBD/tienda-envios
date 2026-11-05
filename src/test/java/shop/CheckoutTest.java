package shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CheckoutTest {
    @Test void totalIncluyeEnvioLegacy() {
        Order order = new Order("o1", "c1", "Medellín", 100, 4);
        assertEquals(110.0, new Checkout().total(order));
    }
}
