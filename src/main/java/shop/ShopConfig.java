package shop;

public class ShopConfig {
    public static Checkout checkout() {
        FeatureFlags flags = new FeatureFlags();
        flags.enable("smart-shipping");
        return new Checkout(new ShippingCalculatorProxy(
                new LegacyShippingCalculator(), new SmartShippingCalculator(), flags));
    }
}
