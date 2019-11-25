
package ru.neoflex.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Заказ
 */
@Entity
@Table(name = "el_order", schema = "APP")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Имя пользователя
     */
    @Column(name = "user_name")
    String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) &&
                userName.equals(order.userName) &&
                confirmed.equals(order.confirmed);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (confirmed != null ? confirmed.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", confirmed=" + confirmed +
                '}';
    }
}
 