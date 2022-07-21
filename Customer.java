import java.util.Objects;

public class Customer {
    private String custid;
    private String custname;
    private String city;

    public Customer() {
        super();
    }

    public Customer(String custid, String custname, String city) {
        super();
        this.custid = custid;
        this.custname = custname;
        this.city = city;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getCustid(), customer.getCustid()) && Objects.equals(getCustname(), customer.getCustname()) && Objects.equals(getCity(), customer.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustid(), getCustname(), getCity());
    }
}
