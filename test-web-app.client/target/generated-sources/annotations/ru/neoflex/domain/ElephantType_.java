/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package ru.neoflex.domain;

import java.math.BigDecimal;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=ru.neoflex.domain.ElephantType.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Tue Nov 26 06:54:06 VET 2019")
public class ElephantType_ {
    public static volatile SingularAttribute<ElephantType,String> description;
    public static volatile SingularAttribute<ElephantType,Long> id;
    public static volatile SetAttribute<ElephantType,Item> items;
    public static volatile SingularAttribute<ElephantType,String> name;
    public static volatile SingularAttribute<ElephantType,String> picture;
    public static volatile SingularAttribute<ElephantType,BigDecimal> price;
}
