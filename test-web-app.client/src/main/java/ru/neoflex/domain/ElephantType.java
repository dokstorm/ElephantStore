
package ru.neoflex.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Сущность с товарной позицией
 */
@Entity
@Table(name = "el_type", schema = "APP")
@NamedQueries({
        @NamedQuery(name = ElephantType.SEARCH_QUERY,
                query = "select e from el_type e where upper(e.name_type) like '%:pattern%' or upper(e.description) like '%:pattern%' order by e.id")
})
public class ElephantType {

    public static final String SEARCH_QUERY = "ElephantType.SearchQuery";
    public static final String PARAMETER_PATTERN = "pattern";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Название слона
     */
    @Column(name = "name_type")
    String name;

    /**
     * Описание
     */
    @Column(name = "description")
    String description;

    /**
     * Ссылка на картинку
     */
    @Column(name = "pic")
    String picture;

    /**
     * Цена
     */
    @Column(name = "price")
    BigDecimal price;

    /**
     * Предметы заказа с этой позицией
     */
    @OneToMany (mappedBy ="elephantType", fetch = FetchType.LAZY)
    Set<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigDecimal getPrice() {
        if (price == null) {
            price = BigDecimal.ZERO;
        }
        // ограничим число знаков после запятой
        return price.setScale(2);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ElephantType");
        sb.append("{id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", picture='").append(picture).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElephantType that = (ElephantType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}