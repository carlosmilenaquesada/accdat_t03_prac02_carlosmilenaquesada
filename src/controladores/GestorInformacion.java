/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author Administrador
 */
public class GestorInformacion {

    public static String[] panelBorradoCliente = new String[]{
        "Se encontraron facturas asociadas al cliente que pretende borrar, si continúa, se borrarán"
            + " también dichas facturas y líneas de factura.\n"
        + "Este proceso es irreversible. ¿Está seguro de querer continuar?",
        "Borrado de cliente", "NO BORRAR NADA", "BORRAR CLIENTE, FACTURAS ASOCIADAS Y LÍNEAS DE FACTURA ", "CANCELAR"};

}
