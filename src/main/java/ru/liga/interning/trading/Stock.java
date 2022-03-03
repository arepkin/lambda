package ru.liga.interning.trading;

import java.util.Objects;

/**
 * @author Repkin Andrey {@literal <arepkin@at-consulting.ru>}
 */
public class Stock {
    private String symbol;
    private String market;
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getMarket() {
        return market;
    }
    public void setMarket(String market) {
        this.market = market;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(getSymbol(), stock.getSymbol()) && Objects.equals(getMarket(), stock.getMarket());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSymbol(), getMarket());
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", market='" + market + '\'' +
                '}';
    }
}
