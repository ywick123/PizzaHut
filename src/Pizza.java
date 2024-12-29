import java.util.ArrayList;
import java.util.List;

class Pizza {
    private String size;
    private String crust;
    private String sauce;
    private List<String> toppings;
    private String cheese;
    private double basePrice;

    private Pizza(PizzaBuilder builder) {
        this.size = builder.size;
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        this.toppings = builder.toppings;
        this.cheese = builder.cheese;
        this.basePrice = builder.basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getDetails() {
        return "Size: " + size + ", Crust: " + crust + ", Sauce: " + sauce + ", Toppings: " + String.join(", ", toppings) + ", Cheese: " + cheese;
    }

    @Override
    public String toString() {
        return "Pizza(Size: " + size + ", Crust: " + crust + ", Sauce: " + sauce + ", Toppings: " + String.join(", ", toppings) + ", Cheese: " + cheese + ")";
    }

    public static class PizzaBuilder {
        private String size;
        private String crust;
        private String sauce;
        private List<String> toppings = new ArrayList<>();
        private String cheese;
        private double basePrice;

        public PizzaBuilder setSize(String size) {
            this.size = size;
            switch (size.toLowerCase()) {
                case "small":
                    this.basePrice = 8.0;
                    break;
                case "medium":
                    this.basePrice = 10.0;
                    break;
                case "large":
                    this.basePrice = 12.0;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid size. Choose Small, Medium, or Large.");
            }
            return this;
        }

        public PizzaBuilder setCrust(String crust) {
            this.crust = crust;
            return this;
        }

        public PizzaBuilder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public PizzaBuilder addTopping(String topping) {
            this.toppings.add(topping);
            this.basePrice += 1.5; // Add $1.5 for each topping
            return this;
        }

        public PizzaBuilder setCheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}