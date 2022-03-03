package ru.liga.interning.trading;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Repkin Andrey {@literal <arepkin@at-consulting.ru>}
 */
public class Order {
    private String customer;
    private List<Trade> trades = new ArrayList<>();
    public void addTrade(Trade trade) {
        trades.add(trade);
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public double getValue() {
        return trades.stream().mapToDouble(Trade::getValue).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(getCustomer(), order.getCustomer()) && Objects.equals(trades, order.trades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), trades);
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer='" + customer + '\'' +
                ", trades=" + trades +
                '}';
    }
}
