/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package ru.neoflex.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=ru.neoflex.domain.Order.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Tue Nov 26 06:34:14 VET 2019")
public class Order_ {
    public static volatile SingularAttribute<Order,Boolean> confirmed;
    public static volatile SingularAttribute<Order,Long> id;
    public static volatile SetAttribute<Order,Item> items;
    public static volatile SingularAttribute<Order,String> userName;
}
