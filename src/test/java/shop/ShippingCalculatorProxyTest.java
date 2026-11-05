package shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ShippingCalculatorProxyTest {
    Order order = new Order("o1", "c1", "Medellín", 100, 4);
    FeatureFlags flags = new FeatureFlags();
    // Stub temporal hasta que exista SmartShippingCalculator
    ShippingCalculatorProxy proxy = new ShippingCalculatorProxy(
            new LegacyShippingCalculator(), o -> 0.0, flags);

    @Test void usaLegacyConFlagApagado() {
        assertEquals(10.0, proxy.calculate(order));
    }

    @Test void usaSmartConFlagEncendido() {
        flags.enable("smart-shipping");
        assertEquals(0.0, proxy.calculate(order));
    }
}
