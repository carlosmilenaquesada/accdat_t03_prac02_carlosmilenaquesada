package modelos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Articulos;
import modelos.Clientes;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-12-05T18:08:18")
@StaticMetamodel(Facturas.class)
public class Facturas_ { 

    public static volatile SingularAttribute<Facturas, Clientes> codcliente;
    public static volatile SingularAttribute<Facturas, Long> numfactura;
    public static volatile SingularAttribute<Facturas, Date> fechafactura;
    public static volatile CollectionAttribute<Facturas, Articulos> articulosCollection;

}