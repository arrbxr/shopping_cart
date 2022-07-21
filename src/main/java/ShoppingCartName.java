import java.beans.Customizer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ShoppingCartName {
    static int totalPrice = 0;
    //public static Customer customer = null;

    public static String prodid = "";
    public static int qty = 0;

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        boolean f = true;

        while(f){
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.println("Enter your choice");
            choice = sc.nextInt();

            if(choice == 1){
                AdminPage.adminPanel();
                f = false;
            } else if(choice == 2){
                customerPanel();
            } else if(choice == 3){
                f = false;
                System.out.println("Thank you for shopping with us");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    public static void customerPanel() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            // here we need to display the product lists

            showProducts();
            System.out.println();
            System.out.println("1. Add Product To Cart");
            System.out.println("2. View Cart Items");
            System.out.println("3. Delete Cart");
            System.out.println("4. Exit");
            System.out.println("Enter Your Choice");
            choice = sc.nextInt();

            switch (choice) {

                case 1 -> {
                    showProducts();
                    readProductDetails();
                    createCartAndAddToCartList();
                }

                case 2 -> viewCartItems();
                case 3 -> deleteCart();
                default -> System.out.println("Main Menu");
            }

        }while(choice < 4);
    }

    private static void createCartAndAddToCartList() {
        Cart cart = new Cart(prodid, ProductUtility.getProductPrice(prodid), qty);
        CartUtility.addProductToCart(cart);
    }

    private static void readProductDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Product id: ");
        prodid = sc.next();
        System.out.println("Enter Quantity: ");
        qty = sc.nextInt();

    }

    private static void deleteCart() {
        // the code to delete cart
        Scanner sc = new Scanner(System.in);
        String prodid = "";
        System.out.println("Enter Product id: ");
        prodid = sc.next();




        Cart temp = null;
        for(Cart cart: CartUtility.cartList) {
            if(cart.getProdid().equals(prodid)) {
                temp = cart;
                break;
            } else {
                System.out.println("Cart not found...");
            }
        }

        CartUtility.cartList.remove(temp);
        System.out.println("Cart removed successfully");
        System.out.println();

    }

    private static void viewCartItems() throws SQLException {
        // the code to view the cart items
        for (Cart cart : CartUtility.cartList) {
            // product id, product price, quantity
            int price = ProductUtility.getProductPrice(cart.getProdid());
            String prodname = ProductUtility.getProductName(cart.getProdid());
            System.out.println("\tCart Items");
            System.out.println("-------------------");
            System.out.printf("%-10s %-14s %-8s %-10s", "PROD-ID", "PROD-NAME", "QUANTITY", "PRICE");
            System.out.println();
            System.out.printf("%-10s %-14s %-8s %-10s", cart.getProdid(), prodname, cart.getQty(), cart.getPrice());
            totalPrice += (price * cart.getQty());

            System.out.println();

            System.out.println();
            System.out.printf("%-10s %-14s %-8s %-10s %-9d", "Total:-", "", "", "", totalPrice);
            System.out.println();
            //System.out.println("View Cart items...");
        }
    }

    public static void showProducts(){
        System.out.println();
        System.out.printf("%-10s %-14s %-8s %-10s %-8s", "PROD-ID", "PROD-NAME", "PRICE", "CATEGORY", "QUANTITY");
        System.out.println();
        System.out.println("--------------------------------------------------");
        for(Product p: ProductUtility.productList){
            System.out.printf("%-10s %-14s %-8s %-10s %-8d", p.getProdid(), p.getProdname(), "$" + p.getPrice(), p.getCategory(), p.getQoh());
            System.out.println();
        }
    }

}
