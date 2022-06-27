package Sales;

public class Purchase {
    private int id;
    private double value;
    private boolean status;
    private String invoice;

    public Purchase(int id, double value, boolean status, String invoice) {
        this.id = id;
        this.value = value;
        this.status = status;
        this.invoice = invoice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
}
