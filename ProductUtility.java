import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductUtility {
    static List <Product> productList = new ArrayList<>();
    static  {
        try {
            productList = FetchProduct.getAllProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getProductName(String proid) throws SQLException {
        String prodname = "";
        for(Product p: productList) {
            if (p.getProdid().equals(proid)) {
                prodname = p.getProdname();
                break;
            }
        }
        return prodname;
    }

    // factory method

    public static Product findProduct(String prodid){
        Product temp = null;
        for(Product p: productList){
            if(p.getProdid().equals(prodid)){
                temp = p;
                break;
            }
        }
        return temp;
    }

    public static int getProductPrice(String proid){
        int price = 0;
        for(Product p: productList) {
            if (p.getProdid().equals(proid)) {
                price = p.getPrice();
                break;
            }
        }
        return price;
    }
}
