package exceptions;

import java.sql.SQLException;

public class DuplicateClientException  extends SQLException {

    // Constructor vacío
    public DuplicateClientException() {
        super();
    }

    // Constructor que recibe un mensaje personalizado
    public DuplicateClientException(String message) {
        super(message);
    }

    // Método para obtener el mensaje de error de correo duplicado
    public String getMessageMail() {
        return "Error: The client's email is already registered.";
    }

    // Método para obtener el mensaje de error de ID duplicado
    public String getMessageID() {
        return "Error: The client's ID is already registered.";
    }

}
