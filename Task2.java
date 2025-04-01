import java.util.*;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Task2 {
    static HashMap<String, Stock> market = new HashMap<>();
    static HashMap<String, Integer> portfolio = new HashMap<>();
    static double balance = 10000.0;  // Starting balance
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        market.put("AAPL", new Stock("AAPL", 150.0));
        market.put("GOOGL", new Stock("GOOGL", 2800.0));
        market.put("TSLA", new Stock("TSLA", 900.0));

        while (true) {
            System.out.println("\n1. Buy Stock\n2. Sell Stock\n3. View Portfolio\n4. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Enter stock symbol to buy (AAPL, GOOGL, TSLA): ");
                String symbol = scanner.next().toUpperCase();
                if (market.containsKey(symbol)) {
                    System.out.println("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    double cost = quantity * market.get(symbol).price;
                    if (cost <= balance) {
                        balance -= cost;
                        portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + quantity);
                        System.out.println("Bought " + quantity + " shares of " + symbol);
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                } else {
                    System.out.println("Invalid stock!");
                }
            } else if (choice == 2) {
                System.out.println("Enter stock symbol to sell: ");
                String symbol = scanner.next().toUpperCase();
                if (portfolio.containsKey(symbol)) {
                    System.out.println("Enter quantity to sell: ");
                    int quantity = scanner.nextInt();
                    if (portfolio.get(symbol) >= quantity) {
                        balance += quantity * market.get(symbol).price;
                        portfolio.put(symbol, portfolio.get(symbol) - quantity);
                        System.out.println("Sold " + quantity + " shares of " + symbol);
                    } else {
                        System.out.println("Not enough shares to sell!");
                    }
                } else {
                    System.out.println("Stock not in portfolio!");
                }
            } else if (choice == 3) {
                System.out.println("Portfolio: " + portfolio);
                System.out.println("Balance: $" + balance);
            } else {
                break;
            }
        }

        scanner.close();
    }
}

