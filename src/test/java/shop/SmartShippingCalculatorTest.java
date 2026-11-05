package shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SmartShippingCalculatorTest {
    SmartShippingCalculator smart = new SmartShippingCalculator();

    @Test void cobra2_5PorKilo() {
        Order bogota = new Order("o1", "c1", "Bogotá", 100, 4);
        assertEquals(10.0, smart.calculate(bogota));
    }

    @Test void sumaRecargoDe5FueraDeBogota() {
        Order medellin = new Order("o2", "c1", "Medellín", 100, 4);
        assertEquals(15.0, smart.calculate(medellin)); // 4 kg × 2,5 + 5
    }
}
