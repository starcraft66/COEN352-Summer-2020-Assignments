package co.tdude.COEN352Assignment1;

public class Inventory {
    protected String sku;
    protected String description;
    protected String bin;
    protected String location;
    protected String unit;
    protected int qty;
    protected int reorderQty;
    protected double cost;
    protected boolean reorder;

    public Inventory(String sku, String description, String bin, String location, String unit, int qty, int reorderQty, double cost, boolean reorder) {
        this.sku = sku;
        this.description = description;
        this.bin = bin;
        this.location = location;
        this.unit = unit;
        this.qty = qty;
        this.reorderQty = reorderQty;
        this.cost = cost;
        this.reorder = reorder;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getReorderQty() {
        return reorderQty;
    }

    public void setReorderQty(int reorderQty) {
        this.reorderQty = reorderQty;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isReorder() {
        return reorder;
    }

    public void setReorder(boolean reorder) {
        this.reorder = reorder;
    }

    public double getInventoryValue() {
        return this.qty * this.cost;
    }
}
