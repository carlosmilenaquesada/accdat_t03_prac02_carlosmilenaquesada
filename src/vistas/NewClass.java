package vistas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import controladores.*;

public class NewClass {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("accdat_t03_prac02_carlosmilenaquesadaPU");
        EntityManager em = emf.createEntityManager();
        ClientesJpaController cc = new ClientesJpaController(emf);
        try {
            System.out.println(cc.findClientes("CLI_001"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        emf.close();

    }
}
