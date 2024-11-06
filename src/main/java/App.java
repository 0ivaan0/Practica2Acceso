import dao.ConnectionManager;
import model.Product;
import service.ProductService;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class App {

    public static void main(String[] args) {

          ProductService productService = new ProductService();

//        Product newProduct = new Product();
//        newProduct.setName("Pelota");
//        newProduct.setDescription("Pelota Description");
//        newProduct.setStock(70);
//        newProduct.setPrice(4.99);
//        newProduct.setAvailable(true);
//
//        Product pCreado = productService.newProduct(newProduct);
//        System.out.println(pCreado.getId());

        Product modificarProduct = new Product();
        modificarProduct.setId(12);
        modificarProduct.setName("Pelota");
        modificarProduct.setDescription("Pelota pequeña");
        modificarProduct.setStock(70);
        modificarProduct.setPrice(9.99);
        modificarProduct.setAvailable(true);

        Product pModificado = productService.updateProduct(modificarProduct);

    }
}
