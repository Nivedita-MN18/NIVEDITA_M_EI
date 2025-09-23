import java.util.Scanner;
public class  Main {
    public static void main(String[] args) {
        StockSubject stockMarket = new StockSubject();
        StockDisplay display1 = new StockDisplay("Display1");
        StockDisplay display2 = new StockDisplay("Display2");
        stockMarket.addObserver(display1);
        stockMarket.addObserver(display2);
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("Enter Stock Name (or 'exit' to quit): ");
            String name = sc.next();
            if(name.equalsIgnoreCase("exit")) break;
            System.out.print("Enter Stock Price: ");
            double price = sc.nextDouble();
            stockMarket.setStockPrice(name, price);
            System.out.println("Updated " + name + " to $" + price + "\n");
        }
    }
}
