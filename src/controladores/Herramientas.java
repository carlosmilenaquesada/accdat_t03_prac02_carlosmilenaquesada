package controladores;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Herramientas {

    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("accdat_t03_prac02_carlosmilenaquesadaPU");

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

    
    
    

}
