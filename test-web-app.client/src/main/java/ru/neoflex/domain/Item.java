package ru.neoflex.domain;

import javax.persistence.*;
/**
 *  Сущность для хранения единицы заказа
 */
@Entity
@Table(name = "el_item", schema="APP")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Тип слона
     */
    @ManyToOne
    @JoinColumn(name = "elefant_type_id")
    ElephantType elephantType;

    /**
     * Заказ
     */
    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    /**
     * Количество едениц
     */
    @Column (name = "ordered_number")
    Integer number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ElephantType getElephantType() {
        return elephantType;
    }

    public void setElephantType(ElephantType elephantType) {
        this.elephantType = elephantType;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id) &&
                number.equals(item.number);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}