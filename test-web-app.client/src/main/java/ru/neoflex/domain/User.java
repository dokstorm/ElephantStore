package ru.neoflex.domain;

import javax.persistence.*;

@Entity
@Table(name = "el_user")
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "EL_USER_SEQ")
    @SequenceGenerator( name = "EL_USER_SEQ", sequenceName = "EL_USER_SEQ" )
    Long id;

    @Column(name = "user_name")
    String userName;

    /*@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Order order;*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    Order order;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (id == null || user.id == null)
            return userName.equals(user.userName);
        else return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
