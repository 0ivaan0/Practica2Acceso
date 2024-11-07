package exceptions;

public class InventoryException extends Exception {

    // Constructor vacío
    public InventoryException() {
        super();
    }

    // Constructor que recibe un mensaje personalizado
    public InventoryException(String message) {
        super(message);
    }

    // Método para obtener un mensaje específico cuando el inventario es insuficiente
    public String getInsufficientStockMessage() {
        return "Error: Insufficient stock available for the requested quantity.";
    }
}
