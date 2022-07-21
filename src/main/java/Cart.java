import java.util.Objects;

public class Cart {
   // private String custId;
    private String prodid;
    private int price;
    private int qty;

    public Cart(){
        super();
    }

    public Cart(String prodid, int price, int qty){
        super();
        this.prodid = prodid;
        this.price = price;
        this.qty = qty;
    }

    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart cart)) return false;
        return getPrice() == cart.getPrice() && getQty() == cart.getQty() && Objects.equals(getProdid(), cart.getProdid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProdid(), getPrice(), getQty());
    }
}
