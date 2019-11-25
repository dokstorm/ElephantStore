package ru.neoflex.service;

import ru.neoflex.domain.ElephantType;
import ru.neoflex.domain.Order;

import javax.ejb.Local;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Сервис, обеспечивающий работу магазина
 */
@Local
public interface ElephantService extends Serializable {

    /**
     * Возвращает список объектов ElephantType, у которых название или описание содержат pattern
     * или весь список, если pattern не задан
     *
     * @param pattern
     * @return Список найденных объектов типа ElephantType
     */
    List<ElephantType> searchElephant(String pattern);

    /**
     * Сохраняет заказ
     *
     * @param order
     */
    void saveOrder(Order order);

}