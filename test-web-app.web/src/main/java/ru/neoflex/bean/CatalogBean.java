package ru.neoflex.bean;

import ru.neoflex.domain.ElephantType;
import ru.neoflex.domain.Item;
import ru.neoflex.domain.Order;
import ru.neoflex.domain.User;
import ru.neoflex.service.ElephantService;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@ManagedBean
@SessionScoped
public class CatalogBean {

    private static String PARAMETER_ELEPHANT_ID = "elid";
    private static String NO_ELEPHANT_SELECTED = "Нужно выбрать хотя бы одного слона";
    public static String INDEX_VIEW_ID = "index.jsf";
    public static String VIEW_ID = "catalog.jsf";

    @EJB
    private ElephantService elephantService;

    private Map<Long, Item> items = new HashMap<Long, Item>();
    private Order order;
    private User user;

    private String searchString = "";
    public String getSearchString() {
        return searchString;
    }

    public String getTotalString() {
        return order.getOrderDetails();
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public void init() {
        if (order == null) {
            order = new Order();
            if (user == null) {
                user = new User();
                user.setOrder(order);
                user.setUserName("");
            }
            // получим полный список слонов
            List<ElephantType> list = elephantService.searchElephant("%");

            items = new HashMap<Long, Item>(list.size());
            // заполним map для хранения единиц заказа
            for (ElephantType type : list) {
                Item item = new Item();
                item.setElephantType(type);
                item.setNumber(0);
                item.setOrder(order);
                items.put(type.getId(), item);
            }

            // добавим пункты к заказу
            order.setItems(new HashSet(items.values()));
            //order.setUser(user);
            //order = elephantService.saveOrder(order);
        }
    }

    public List<ElephantType> getAllElephants() {
        if (searchString != null)
            return elephantService.searchElephant("%" + searchString + "%");
        else
            return elephantService.searchElephant("%");
    }

    public ElephantService getElephantService() {
        return elephantService;
    }

    public void setElephantService(ElephantService elephantService) {
        this.elephantService = elephantService;
    }

    public Map<Long, Item> getItems() {
        return items;
    }

    public void setItems(Map<Long, Item> items) {
        this.items = items;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    protected Long getElId() {
        return Long.parseLong(
                FacesContext.getCurrentInstance().getExternalContext()
                        .getRequestParameterMap().get(PARAMETER_ELEPHANT_ID)); // PARAMETER_ELEPHANT_ID = "elid"
    }

    /**
     * Добавляем к заказу одного слона с выбранным id
     */
    public void add() {
        Long elId = getElId();
        if (elId != null) {
            Item item = items.get(elId);
            if (item != null) {
                item.setNumber(item.getNumber() + 1);
            }
        }
    }

    /**
     * Убираем из заказа одного слона с выбранным id
     */
    public void delete() {
        Long elId = getElId();
        if (elId != null) {
            Item item = items.get(elId);
            if (item != null && item.getNumber() > 0) {
                item.setNumber(item.getNumber() - 1);
            }
        }
    }

    public String doOk() {
        if (!order.isValidOrder()) {
            // добавляем сообщение об ошибке, если ни один слон не выбран
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    NO_ELEPHANT_SELECTED, NO_ELEPHANT_SELECTED);
            facesContext.addMessage(null, facesMessage);
            // и возврщаем null, чтобы остаться на той же странице
            return null;
        }
        order.setUser(user);
        elephantService.saveOrder(order);
        return INDEX_VIEW_ID + "?faces-redirect=true";
    }
}
