public class StockDisplay implements StockObserver {
    private String displayId;

    public StockDisplay(String id) {
        this.displayId = id;
    }

    @Override
    public void update(String stock, double price) {
        System.out.println(displayId + "->" + stock + " price: " + price);
    }

}
