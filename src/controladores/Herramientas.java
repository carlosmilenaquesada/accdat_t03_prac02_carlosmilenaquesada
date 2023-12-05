package controladores;

import java.awt.Component;
import java.awt.Rectangle;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JDialog;

public class Herramientas {

    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("accdat_t03_prac02_carlosmilenaquesadaPU");

    public static Rectangle bondsDeDialogs(Component padre, JDialog jDialog) {
        return new Rectangle(padre.getX() + 10, padre.getY() + 10, jDialog.getWidth(), jDialog.getHeight());
    }
}
