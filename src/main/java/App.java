import dao.ConnectionManager;
import model.Client;
import model.Product;
import service.ClientService;
import service.ProductService;
import service.SalesService;

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


        ClientService cs = new ClientService();

        //--------------INSERT CLIENT-------------------
//        Client cliente = new Client();
//        cliente.setName("Ivan");
//        cliente.setSurname("Klk");
//        cliente.setEmail("juan.perez@example.com");
//        cliente.setPurchases(20);
//        cliente.setId(2);
//        Client cId = cs.newClient(cliente);
//        System.out.println(cId.getId());
//        Client cliente = new Client();
//        cliente.setName("Ivan");
//        cliente.setSurname("Lopez");
//        cliente.setEmail("ivan.lopez@example.com");
//        cliente.setPurchases(20);
//        Client cId = cs.newClient(cliente);
//        System.out.println(cId.getId());

        //--------------UPDATE CLIENT----------------
//        Client c = cs.updateClient(cliente);
//        System.out.println(c.getName()+" "+c.getSurname());

        //--------------DELETE CLIENT----------------
//        Client c = new Client();
//        c.setId(2);
//        boolean h =cs.deleteClient(c);
//        System.out.println(h);

        //--------------GET ALL---------------------
//        List<Client> list = cs.getAllClients();
//        for (Client c : list) {
//            System.out.println(c);
//        }

        //-------------GET BY EMAIL---------------
//        Client c01 = cs.getClientByEmail("ivan.lopez@example.com");
//        System.out.println(c01);

        //-------------INSERT SALES-----------------
        SalesService ss = new SalesService();
        Product product = productService.getById(12);
        Client client = cs.getClientByEmail("juan.perez@example.com");
        ss.newSale(product,client,10);
    }
}
