
package ru.neoflex.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Заказ
 */
@Entity
@Table(name = "el_order")
public class Order {

    @Id
    @SequenceGenerator( name = "EL_ORDER_SEQ", sequenceName = "EL_ORDER_SEQ")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "EL_ORDER_SEQ")
    Long id;

    /**
     * Имя пользователя
     */
    /*@Column(name = "user_name")
    String userName;*/

    /**
     * Признак подтверждения
     */
    @Column(name = "confirmed")
    Boolean confirmed;

    /**
     * Список предметов заказа
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Item> items;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    User user;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;*/

    public String getOrderDetails() {
        BigDecimal total = BigDecimal.ZERO;
        int number = 0;
        for (Item item : items) {
            if (item.number > 0) {
                number = number + item.number;
                total = total.add(item.getElephantType().getPrice().multiply(
                        new BigDecimal(item.number)));
            }
        }
        return "Всего слонов: " + number + " на сумму: " + total;
    }

    public boolean isValidOrder() {
        int number = 0;
        for (Item item : items) {
            if (item.number > 0) {
                number = number + item.number;
            }
        }
        if (number == 0) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }*/

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        if (id == null || order.id == null)
            return confirmed.equals(order.confirmed);
        else return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", confirmed=" + confirmed +
                '}';
    }
}
 