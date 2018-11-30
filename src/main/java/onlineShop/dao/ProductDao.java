package onlineShop.dao;
import java.util.*;
import onlineShop.model.Product;
public interface ProductDao {
	Product getProductById(int productId);

    void deleteProduct(int productId);
    
    void addProduct(Product product);
    
    void updateProduct(Product product);
    
    List<Product> getAllProducts();

}
