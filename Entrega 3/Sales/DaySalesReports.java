package sales;

public class DaySalesReports {

    private String name;
    private double unityPrice;
    private int quantity;

    public DaySalesReports(String name, double unityPrice, int quantity) {
        this.name = name;
        this.unityPrice = unityPrice;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getUnityPrice() {
        return unityPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnityPrice(double unityPrice) {
        this.unityPrice = unityPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}