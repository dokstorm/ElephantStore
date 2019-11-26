package ru.neoflex.bean;

import ru.neoflex.domain.ElephantType;
import ru.neoflex.domain.Item;
import ru.neoflex.domain.Order;
import ru.neoflex.service.ElephantService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@ManagedBean
@SessionScoped
public class CatalogBean {
    @EJB
    private ElephantService elephantService;

    private Map<Long, Item> items = new HashMap<Long, Item>();
    private Order order;

    public void init() {
        if (order == null) {
            order = new Order();
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
            order = elephantService.saveOrder(order);

        }
    }

    public List<ElephantType> getAllElephants() {
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
}
