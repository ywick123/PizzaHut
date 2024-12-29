import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Pizza Shop!");

        // Step 1: Pizza Customization
        Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder();

        System.out.println("Choose your crust (Thin Crust / Thick Crust / Stuffed Crust): ");
        String crust = scanner.nextLine();
        builder.setCrust(crust);

        System.out.println("Select your pizza size (Small / Medium / Large): ");
        String size = scanner.nextLine();
        builder.setSize(size);

        System.out.println("Choose your sauce (Tomato / Alfredo / Pesto): ");
        String sauce = scanner.nextLine();
        builder.setSauce(sauce);

        System.out.println("Available toppings: Pepperoni, Mushrooms, Olives, Bell Peppers, Onions, Chicken, Bacon");
        System.out.println("Enter toppings (type 'done' when finished): ");
        while (true) {
            String topping = scanner.nextLine();
            if (topping.equalsIgnoreCase("done")) {
                break;
            }
            builder.addTopping(topping);
        }

        System.out.println("Choose your cheese (Mozzarella / Cheddar / Parmesan): ");
        String cheese = scanner.nextLine();
        builder.setCheese(cheese);

        Pizza pizza = builder.build();
        System.out.println("Your customized pizza: " + pizza);
        System.out.println("Base Price: $" + pizza.getBasePrice());

        // Step 2: Add Extra Toppings
        System.out.println("Would you like extra toppings? (yes / no): ");
        String extraToppingChoice = scanner.nextLine();
        double finalPrice = pizza.getBasePrice();
        if (extraToppingChoice.equalsIgnoreCase("yes")) {
            System.out.println("Enter extra topping: ");
            String extraTopping = scanner.nextLine();
            ExtraToppingDecorator decoratedPizza = new ExtraToppingDecorator(pizza, extraTopping);
            finalPrice = decoratedPizza.getPrice();
            System.out.println("Decorated Pizza: " + decoratedPizza.getDescription());
            System.out.println("Updated Total Price: $" + finalPrice);
        }

        // Step 3: Delivery or Pickup
        System.out.println("Would you like Pickup or Delivery? (Pickup / Delivery): ");
        String orderType = scanner.nextLine();
        String deliveryAddress = "";
        if (orderType.equalsIgnoreCase("Delivery")) {
            System.out.println("Enter your delivery address: ");
            deliveryAddress = scanner.nextLine();
        }

        // Step 4: Order Tracking
        OrderTracker tracker = new OrderTracker();
        Customer customer = new Customer("John");
        tracker.attach(customer);

        System.out.println("Tracking your order...");
        tracker.setState("Order placed.");
        Thread.sleep(2000); // 2-second delay
        tracker.setState("Order is being prepared.");
        Thread.sleep(3000); // 3-second delay
        tracker.setState(orderType.equalsIgnoreCase("Delivery") ? "Order is out for delivery." : "Order is ready for pickup.");
        Thread.sleep(2000); // 2-second delay

        // Step 4: Payment
        System.out.println("Choose payment method (CreditCard / DigitalWallet): ");
        String paymentMethodChoice = scanner.nextLine();
        PaymentStrategy paymentMethod;
        if (paymentMethodChoice.equalsIgnoreCase("CreditCard")) {
            paymentMethod = new CreditCardPayment();
        } else {
            paymentMethod = new DigitalWalletPayment();
        }
        paymentMethod.pay(pizza.getBasePrice());


        // Generate Invoice
        String orderId = UUID.randomUUID().toString();
        String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("\n========== BILL ==========");
        System.out.println("Order ID: " + orderId);
        System.out.println("Date: " + dateTime);
        System.out.println("Customer: John");
        System.out.println("Order Type: " + orderType);
        if (orderType.equalsIgnoreCase("Delivery")) {
            System.out.println("Delivery Address: " + deliveryAddress);
        }
        System.out.println("Order Details: " + pizza.getDetails());
        System.out.println("Total Amount: $" + finalPrice);
        System.out.println("=============================");

        scanner.close();
    }
}
