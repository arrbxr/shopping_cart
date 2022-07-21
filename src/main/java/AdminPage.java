import java.sql.*;
import java.util.Scanner;

public class AdminPage {

    public static void adminPanel() throws SQLException {
        Connection con = ConnectionProvider.createConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username");
        String username = sc.nextLine();
        System.out.println("Enter your password");
        String password = sc.nextLine();

        if(username.equals("admin") && password.equals("admin")){
            System.out.println("\t\tWelcome to Admin Page");
            System.out.println("-------------------------------------");
            System.out.println();
            int choice;
            boolean flag = true;
            do{
                System.out.println("1. Show Products");
                System.out.println("2. Add Product");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Back to Main Menu");

                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        ShoppingCartName.showProducts();
                    }
                    case 2 -> {
                        addProducts();
                    }
                    case 3 -> {
                        updateProducts();
                    }
                    case 4 -> {
                        deleteProducts();
                    }
                    case 5 -> {
                        ShoppingCartName.main(null);
                        flag = false;
                    }
                    default -> {
                        System.out.println("Please enter valid number... ");
                    }
                }
            } while (flag);
        } else {
            System.out.println("Invalid username or password");
            ShoppingCartName.main(null);
        }
    }

    private static void deleteProducts() {
        Connection con = ConnectionProvider.createConnection();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the product id to delete");
        int prodid = sc.nextInt();
        try {
            String query = "delete from productlist where id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, prodid);

            int count = ps.executeUpdate();

            if(count > 0){
                System.out.println("Product deleted successfully");
            } else {
                System.out.println("Product not found");
            }

            FetchProduct.getAllProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateProducts() throws SQLException {
        Connection con = ConnectionProvider.createConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the product id");
        int productId = Integer.parseInt(sc.nextLine());

        String qery = "select * from productlist";

        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery(qery);

        boolean flag = true;
        while(rs.next()){
            if((productId == rs.getInt("id"))){
                System.out.println("Enter the new product name");
                String productName = sc.nextLine();
                System.out.println("Enter the new product price");
                String productPrice = sc.nextLine();
                System.out.println("Enter the new product quantity");
                String productQuantity = sc.nextLine();
                System.out.println("Enter the new product Category");
                String productCategory = sc.nextLine();

                String sql = "update productlist set prodName = ?, " +
                        "prodPrice = ?, " +
                        "prodQt = ?, " +
                        "prodCat = ? " +
                        "where id = ?";
                try {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, productName);
                    ps.setInt(2, Integer.parseInt(productPrice));
                    ps.setInt(3, Integer.parseInt(productQuantity));
                    ps.setString(4, productCategory);
                    ps.setInt(5, productId);
                    int i = ps.executeUpdate();
                    if(i > 0){
                        System.out.println("Product updated successfully");
                    } else {
                        System.out.println("Product update failed");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                FetchProduct.getAllProducts();
                flag = false;
            }
        }
        if(flag){
            System.out.println("Product not found");
        }
    }


    // adding product in the database
    private static void addProducts() throws SQLException {
        Connection con = ConnectionProvider.createConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the product id");
        int productId = Integer.parseInt(sc.nextLine());

        String query = "select id from productlist";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        boolean flag = true;
        while(rs.next()){
            if(productId == rs.getInt("id")){
                System.out.println("Product already exists");
                flag = false;
                break;
            }
        }

        if(flag) {
            System.out.println("Enter the product name");
            String productName = sc.nextLine();
            System.out.println("Enter the product price");
            String productPrice = sc.nextLine();
            System.out.println("Enter the product quantity");
            String productQuantity = sc.nextLine();
            System.out.println("Enter the product Category");
            String productCategory = sc.nextLine();

            String query2 = "insert into productlist values (?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = con.prepareStatement(query2);
                ps.setInt(1, productId);
                ps.setString(2, productName);
                ps.setString(3, productCategory);
                ps.setInt(4, Integer.parseInt(productPrice));
                ps.setInt(5, Integer.parseInt(productQuantity));

                int i = ps.executeUpdate();
                if (i > 0) {
                    System.out.println("Product added successfully");
                } else {
                    System.out.println("Product add failed");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            FetchProduct.getAllProducts();
        }

    }

}
