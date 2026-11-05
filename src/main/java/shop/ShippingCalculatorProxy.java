package shop;

import java.util.function.Consumer;

public class ShippingCalculatorProxy implements ShippingCalculator {
    private final ShippingCalculator legacy;
    private final ShippingCalculator smart;
    private final FeatureFlags flags;
    private final Consumer<String> log;

    public ShippingCalculatorProxy(ShippingCalculator legacy,
                                   ShippingCalculator smart,
                                   FeatureFlags flags) {
        this(legacy, smart, flags, msg -> {});
    }

    public ShippingCalculatorProxy(ShippingCalculator legacy,
                                   ShippingCalculator smart,
                                   FeatureFlags flags,
                                   Consumer<String> log) {
        this.legacy = legacy;
        this.smart = smart;
        this.flags = flags;
        this.log = log;
    }

    @Override
    public double calculate(Order order) {
        boolean useSmart = flags.isEnabled("smart-shipping");
        ShippingCalculator target = useSmart ? smart : legacy;

        long start = System.nanoTime();
        double result = target.calculate(order);
        long ms = (System.nanoTime() - start) / 1_000_000;

        log.accept("shipping order=" + order.id()
                + " impl=" + (useSmart ? "smart" : "legacy")
                + " result=" + result + " took=" + ms + "ms");
        return result;
    }
}
