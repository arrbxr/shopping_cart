import java.util.Objects;

public class TempCart {
    private String prodid;
    private String prodname;
    private int price;
    private int qty;

    public TempCart() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TempCart(String prodid, String prodname, int price, int qty) {
        this.prodid = prodid;
        this.prodname = prodname;
        this.price = price;
        this.qty = qty;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
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

    public String getProdid() {
        return prodid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TempCart)) return false;
        TempCart tempCart = (TempCart) o;
        return getPrice() == tempCart.getPrice() && getQty() == tempCart.getQty() && Objects.equals(getProdid(), tempCart.getProdid()) && Objects.equals(getProdname(), tempCart.getProdname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProdid(), getProdname(), getPrice(), getQty());
    }

    @Override
    public String toString() {
        return "TempCart{" +
                "prodid='" + prodid + '\'' +
                ", prodname='" + prodname + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
