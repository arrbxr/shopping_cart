import java.util.ArrayList;
import java.util.List;

public class CartUtility {

    public static List<Cart> cartList = new ArrayList<>();

    public static void addProductToCart(Cart cart){
        // 1. check if the cartlist is already contains a cart object with the specific customer id and product id
        // 2. if yes, then update the quantity of the of existing cart object, with the new quantity
        // 3. if no, then add the new cart object to the cartlist


        int index = -1;
        Cart temp = null;

        for(Cart c: cartList){
            index++;
            if((c.getProdid().equals(cart.getProdid()))){
                temp = c;
                break;
            }
        }

        if(temp == null){
            cartList.add(cart);
            System.out.println("Cart added...");
        } else {
            temp.setQty(temp.getQty() + cart.getQty());
            cartList.set(index, temp);
            System.out.println("Cart updated...");
        }

        // decrease the stock for the particular prodid that is added to the cart
        // We need to find the product object from the product list based on the product id that is added to the cart
        Product p = ProductUtility.findProduct(cart.getProdid());
        // we should then decrease the Qoh on the p object
        p.setQoh(p.getQoh() - cart.getQty());

        System.out.println(p.getProdname() + " is added to the cart...");

    }
}
