package modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Articulos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-12-01T16:15:27")
@StaticMetamodel(Familias.class)
public class Familias_ { 

    public static volatile SingularAttribute<Familias, String> nomfamilia;
    public static volatile CollectionAttribute<Familias, Articulos> articulosCollection;
    public static volatile SingularAttribute<Familias, String> codfamilia;

}