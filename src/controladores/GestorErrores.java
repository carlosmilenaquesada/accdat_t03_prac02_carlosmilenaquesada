package controladores;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Clase para la gestión de algunos errores.
 *
 * En esta clase, realizo gestiones de control de errores tales como inputs
 * JTextField vacíos, mensajes de error, etc. Serán usados en toda la
 * aplicación, ya que ciertas comprobaciones y estados de los inputs son muy
 * recurrentes, evitando así repetir código en la aplicación
 */
public class GestorErrores {

    private static final Border BORDE_JTF_DEFECTO = new JTextField().getBorder();
    private static final Border BORDE_JTF_ERROR = new LineBorder(Color.red, 2);

    public static void cambiarABordeDefecto(JTextField[] jtfLista) {
        for (JTextField j : jtfLista) {
            j.setBorder(BORDE_JTF_DEFECTO);
        }
    }

    public static void cambiarABordeError(JTextField jtf) {
        jtf.setBorder(BORDE_JTF_ERROR);
    }

    /**
     * Validación de inputs. Recibe un array de JTextField y un array de
     * mensajes de error asociados a cada JTextField recibido. Cuando un
     * JTextField esté vacío (isEmpty() == true), se añade su mensaje de error
     * asociado una colección de errores que será devuelta por la función, y
     * además, el borde del JTextField cambia a Color.red, indicando así al
     * usuario que el campo no puede estar vacío.
     *
     * Por index, cada elemento del array JTextField 'jtfLista' está asociado a
     * un error en el array de String 'mensajeError'
     *
     * @param jtfLista La lista de JTextField sobre los que se realizarán la
     * comprobación.
     * @param mensajesError La lista de errores posibles que cada uno de los
     * JTextField emite en caso de estar vacío.
     * @return Un ArrayList de String con los errores recopilados.
     */
    public static ArrayList<String> validarInput(JTextField[] jtfLista, String[] mensajesError) {
        ArrayList<String> errores = new ArrayList<>();       
        cambiarABordeDefecto(jtfLista);
        for (int i = 0; i < jtfLista.length; i++) {
            if (jtfLista[i].getText().isEmpty()) {                
                cambiarABordeError(jtfLista[i]);
                errores.add(mensajesError[i]);
            }
        }
        return errores;
    }

    public static void mostrarErrores(ArrayList<String> errores) {
        JOptionPane.showMessageDialog(null, String.join("\n", errores), "Se encontraron errores", JOptionPane.ERROR_MESSAGE, null);
    }

    public static String[] mensajesInputsVaciosCliente = new String[]{
        "El campo del código de cliente no puede estar vacío",//0
        "El campo del nombre de cliente no puede estar vacío",//1
    };
    public static String[] mensajesInputsVaciosFamilia = new String[]{
        "El campo del código de familia no puede estar vacío",//0
        "El campo del nombre de familia no puede estar vacío",//1
    };
    public static String[] mensajesInputsVaciosArticulos = new String[]{
        "El campo del código de artículo no puede estar vacío",//0
        "El campo de familia de artículo no puede estar vacío",//1
        "El campo de nombre de artículo no puede estar vacío"//3
    };
    public static String[] mensajesInputsVaciosFactura = new String[]{
        "El campo de fecha de factura no puede estar vacío",//0
        "El campo de código de cliente de la factura no puede estar vacío"//1
    };
    
    public static String[] mensajesInputsVaciosLineasFactura = new String[]{
        "El campo del número de factura no puede estar vacío",//0
        "El campo del código de artículo no puede estar vacío",//1        
    };
    

    public static String[] mensajes = new String[]{
        
        "El cliente proporcionado no existe.",//0
        "No existe una factura con el número proporcionado.",//1
        "La familia proporcionada no existe.",//2
        "El cliente proporcionado no existe.",//3 
        "El artículo proporcionado no existe.",//4
        "Actualmente no existen clientes. Debe crear algún cliente antes de crear una factura.",//5
        "Debe seleccionar una factura para iniciar su modificación o borrado.",//6 
        "Debe seleccionar una factura para poder añadir una línea de factura",//7
        "Debe seleccionar una línea de factura para borrar.",//8
        "Debe seleccionar un artículo para generar una línea de factura.",//9
        "Debe seleccionar una familia para poder crear o modificar un artículo.",//10
    };
}
