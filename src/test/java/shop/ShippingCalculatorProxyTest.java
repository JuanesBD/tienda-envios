package shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ShippingCalculatorProxyTest {
    Order order = new Order("o1", "c1", "Medellín", 100, 4);
    FeatureFlags flags = new FeatureFlags();
    List<String> logs = new ArrayList<>();
    ShippingCalculatorProxy proxy = new ShippingCalculatorProxy(
            new LegacyShippingCalculator(), new SmartShippingCalculator(), flags, logs::add);

    @Test void usaLegacyConFlagApagado() {
        assertEquals(10.0, proxy.calculate(order));
    }

    @Test void usaSmartConFlagEncendido() {
        flags.enable("smart-shipping");
        assertEquals(15.0, proxy.calculate(order)); // 4 kg × 2,5 + 5
    }

    @Test void checkoutFuncionaConCualquierCalculador() {
        Checkout checkout = new Checkout(o -> 7.0);
        assertEquals(107.0, checkout.total(order));
    }

    @Test void registraQueImplementacionRespondioYCuantoTardo() {
        proxy.calculate(order);
        flags.enable("smart-shipping");
        proxy.calculate(order);

        assertEquals(2, logs.size());
        assertTrue(logs.get(0).contains("impl=legacy") && logs.get(0).contains("took="));
        assertTrue(logs.get(1).contains("impl=smart"));
    }
}
