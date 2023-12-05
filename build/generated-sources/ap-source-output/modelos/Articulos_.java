package modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Facturas;
import modelos.Familias;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-12-05T18:08:18")
@StaticMetamodel(Articulos.class)
public class Articulos_ { 

    public static volatile CollectionAttribute<Articulos, Facturas> facturasCollection;
    public static volatile SingularAttribute<Articulos, String> codarticulo;
    public static volatile SingularAttribute<Articulos, String> nomarticulo;
    public static volatile SingularAttribute<Articulos, Familias> codfamilia;

}