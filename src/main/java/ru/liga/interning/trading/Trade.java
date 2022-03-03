package ru.liga.interning.trading;

import java.util.Objects;

/**
 * @author Repkin Andrey {@literal <arepkin@at-consulting.ru>}
 */
public class Trade {
    public enum Type { BUY, SELL }
    private Type type;
    private Stock stock;
    private int quantity;
    private double price;
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Stock getStock() {
        return stock;
    }
    public void setStock(Stock stock) {
        this.stock = stock;
    }
    public double getValue() {
        return quantity * price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return getQuantity() == trade.getQuantity() && Double.compare(trade.getPrice(), getPrice()) == 0 && getType() == trade.getType() && Objects.equals(getStock(), trade.getStock());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getStock(), getQuantity(), getPrice());
    }

    @Override
    public String toString() {
        return "Trade{" +
                "type=" + type +
                ", stock=" + stock +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
