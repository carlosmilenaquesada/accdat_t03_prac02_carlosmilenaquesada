package controladores;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
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
        "El campo de nombre de artículo no puede estar vacío"
    };

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
        "",//26
        "El cliente proporcionado no existe.",//27
        "No se pudo crear el cliente.\nDescripción del error:\n",//28
        "No se pudo borrar el cliente.\nDescripción del error:\n",//29

        //Mensajes de borrado
        "",//30
        "",//31
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
