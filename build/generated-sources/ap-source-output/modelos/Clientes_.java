package modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Facturas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-12-02T12:33:28")
@StaticMetamodel(Clientes.class)
public class Clientes_ { 

    public static volatile SingularAttribute<Clientes, String> codcliente;
    public static volatile CollectionAttribute<Clientes, Facturas> facturasCollection;
    public static volatile SingularAttribute<Clientes, String> nomcliente;

}