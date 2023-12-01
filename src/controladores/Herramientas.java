package controladores;

import java.awt.Component;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDialog;
public class Herramientas {
    
    public static Rectangle bondsDeDialogs(Component padre, JDialog jDialog) {
        return new Rectangle(padre.getX() + 10, padre.getY() + 10, jDialog.getWidth(), jDialog.getHeight());
    }

    private static final DecimalFormat DECIMAL_FORMAT_PRECIO = new DecimalFormat("0.00");

    public static BigDecimal stringABigDecimalPrecio(String numeroEnTexto) throws ParseException {
        if (!numeroEnTexto.matches("^[0-9]+(?:\\.[0-9]{1,2})?$")) {
            throw new ParseException("", 0);
        }
        return BigDecimal.valueOf(DECIMAL_FORMAT_PRECIO.parse(numeroEnTexto).doubleValue());
    }

    private static final DecimalFormat DECIMAL_FORMAT_ENTERO = new DecimalFormat("0");

    public static BigDecimal stringABigDecimalEntero(String numeroEnTexto) throws ParseException {
        if (!numeroEnTexto.matches("^[0-9]+$")) {
            throw new ParseException("", 0);
        }
        return BigDecimal.valueOf(DECIMAL_FORMAT_ENTERO.parse(numeroEnTexto).longValue());
    }

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public static String dateAStringFormateado(Date fecha) {
        return SIMPLE_DATE_FORMAT.format(fecha);
    }

    public static Date stringADateFormateado(String fecha) throws ParseException {
        return SIMPLE_DATE_FORMAT.parse(fecha);
    }

    public static String[] mensajes = new String[]{
        //FacturaJDialog
        //gestión de facturas
        "Debe proporcionar un número de factura.",//0
        "El número de factura proporcionado no tiene un formato válido.",//1
        "No se puede crear la factura porque ya existe una factura con ese código.",//2
        "El cliente proporcionado no existe.",//3
        "La fecha de factura proporcionada no tiene un formato válido.",//4
        "No se pudo crear la factura.\nDescripción del error:\n",//5
        "No existe una factura con el número proporcionado.",//6
        "No se pudo modificar la factura.\nDescripción del error:\n",//7
        "No se pudo borrar la factura.\nDescripción del error:\n",//8
        //gestión de líneas
        "Debe proporcionar un código de artículo y un número de factura.",//9
        "No existe un artículo con el código proporcionado.",//10
        "Ya existe una línea con ese mismo artículo para la factura proporcionada",//11
        "No se pudo crear la línea de factura.\nDescripción del error:\n",//12
        "No existe una línea con ese artículo para la factura proporcionada",//13
        "No se pudo borrar la línea de factura.\nDescripción del error:\n",//14
        //FamiliaJDialog
        //gestión de familias
        "Debe proporcionar un código de familia.",//15
        "No se puede crear la familia porque ya existe una familia con ese código.",//16
        "No se pudo crear la familia.\nDescripción del error:\n",//17
        "La familia proporcionada no existe.",//18
        "No se pudo modificar la familia.\nDescripción del error:\n",//19
        "No se pudo borrar la familia.\nDescripción del error:\n",//20
        //gestión de articulos
        "Debe proporcionar un código de artículo.",//21
        "El precio proporcionado no tiene un formato válido.",//22
        "No se pudo crear el artículo.\nDescripción del error:\n",//23
        "No se pudo modificar el artículo.\nDescripción del error:\n",//24
        "No se pudo borrar el artículo.\nDescripción del error:\n",//25
        //ClientesJDialog
        //gestión de clientes
        "Debe proporcionar un código de cliente.",//26
        "El cliente proporcionado no existe.",//27
        "No se pudo crear el cliente.\nDescripción del error:\n",//28
        "No se pudo borrar el cliente.\nDescripción del error:\n",//29
        //Mensajes de borrado
        "Borrado de cliente",//30
        "Al borrar un cliente, se borrarán también todas las facturas (incluyendo líneas de factura) asociadas a dicho cliente.\n"
        + "Este proceso es irreversible. ¿Está seguro de querer borrar el cliente?",//31
        "Borrado de factura",//32
        "Al borrar una factura, se borrarán también todas las líneas de factura asociadas a dicha factura.\n"
        + "Este proceso es irreversible. ¿Está seguro de querer borrar la factura?",//33
        "Borrado de línea de factura",//34
        "El borrado de la línea es irreversible. ¿Desea continuar con el borrado?",//35
        "Borrado de familia",//36
        "Al borrar una familia, se borrarán también todos los artículos que pertenezcan a la familia, así "
        + "como las líneas de factura donde dichos artículos aparezcan.\n"
        + "Este proceso es irreversible. ¿Está seguro de querer borrar la familia?",//37
        "Borrado de artículo",//38
        "Al borrar un artículo, se borrarán también todas las líneas de factura donde dicho artículo aparezca.\n"
        + "Este proceso es irreversible. ¿Está seguro de querer borrar el artículo?",//39        
        "No se pudo modificar el cliente.\nDescripción del error:\n",//40
    };

}
