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

    public static String[] panelBorradoFamilia = new String[]{
        "Se encontraron artículos asociados la familia que pretende borrar, si continúa, se borrarán"
        + " también dichos artículos y líneas de factura donde estén presentes los artículos.\n"
        + "Este proceso es irreversible. ¿Está seguro de querer continuar?",
        "Borrado de familia", "NO BORRAR NADA", "BORRAR FAMILIA, ARTÍCULOS ASOCIADOS Y LÍNEAS DE FACTURA DE LOS ARTÍCULOS", "CANCELAR"};

    public static String[] panelBorradoArticulo = new String[]{
        "Se encontraron líneas de factura en las que aparece el artículo que se pretende borrar, si continúa, dichas líneas de factura"
        + "serán borradas también (solo se borrarán las líneas, no la factura completa).\n"
        + "Este proceso es irreversible. ¿Está seguro de querer continuar?",
        "Borrado de artículo", "NO BORRAR NADA", "BORRAR ARTÍCULO Y LÍNEAS DE FACTURA DEL ARTÍCULO", "CANCELAR"};

    public static String[] panelBorradoFacturas = new String[]{
        "Se encontraron líneas de factura en la factura que se pretende borrar, si continúa, dichas líneas de factura"
        + "serán borradas también.\n"
        + "Este proceso es irreversible. ¿Está seguro de querer continuar?",
        "Borrado de facturas", "NO BORRAR NADA", "BORRAR FACTURAS Y LÍNEAS DE FACTURA", "CANCELAR"};

}
