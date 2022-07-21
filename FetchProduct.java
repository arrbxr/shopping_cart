import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FetchProduct {

    public static List<Product> productList = new ArrayList<>();

    public static List<Product> getAllProducts() throws SQLException {
        productList.clear();
        Connection con = ConnectionProvider.createConnection();

        String query = "create temporary table temp select * from productlist";

        Statement smt = con.createStatement();
        smt.executeUpdate(query);
        String query1 = "select * from temp";
        ResultSet rs = smt.executeQuery(query1);

        while(rs.next()){
            Product product = new Product();
            product.setProdid(rs.getString("id"));
            product.setProdname(rs.getString("prodName"));
            product.setCategory(rs.getString("prodCat"));
            product.setPrice(rs.getInt("prodPrice"));
            product.setQoh(rs.getInt("prodQt"));
            productList.add(product);
        }
        con.close();
        return productList;
    }
}
