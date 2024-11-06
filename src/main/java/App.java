import dao.ConnectionManager;
import model.Product;
import service.ProductService;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class App {

    public static void main(String[] args) {

          ProductService productService = new ProductService();

          //----------------INSERT PRODUCT----------------
//        Product newProduct = new Product();
//        newProduct.setName("Pelota");
//        newProduct.setDescription("klklklk Description");
//        newProduct.setStock(100);
//        newProduct.setPrice(16.99);
//        newProduct.setAvailable(true);
//        Product pCreado = productService.newProduct(newProduct);
//        System.out.println(pCreado.getId());


        //----------UPDATE PRODUCT------------
//        Product modificarProduct = new Product();
//        modificarProduct.setId(11);
//        modificarProduct.setName("Pelota");
//        modificarProduct.setDescription("Pelota pequeña");
//        modificarProduct.setStock(70);
//        modificarProduct.setPrice(9.99);
//        modificarProduct.setAvailable(true);
//        Product pModificado = productService.updateProduct(modificarProduct);

        //--------------DELETE PRODUCT----------------
//        boolean b = productService.deleteProduct(modificarProduct);
//        System.out.println(b);

        //--------------GET BY ID--------------------
//        Product p = productService.getById(12);
//        System.out.println(p);

        //--------------GET ALL----------------
//        List<Product> lp = productService.getAllProducts();
//        for (Product p: lp) {
//            System.out.println(p);
//        }

        //--------------GET BY NAME----------------
//        List<Product> lp = productService.getProdcutsByNameALike(newProduct);
//        for (Product p: lp) {
//            System.out.println(p);
//        }

    }
}
