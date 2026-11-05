package shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CheckoutTest {
    Order order = new Order("o1", "c1", "Medellín", 100, 4);

    @Test void totalIncluyeEnvioSmart() {
        assertEquals(115.0, new Checkout(new SmartShippingCalculator()).total(order));
    }

    @Test void checkoutFuncionaConCualquierCalculador() {
        Checkout checkout = new Checkout(o -> 7.0);
        assertEquals(107.0, checkout.total(order));
    }
}
