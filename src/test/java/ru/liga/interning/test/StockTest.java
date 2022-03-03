package ru.liga.interning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.liga.interning.trading.LambdaOrderBuilder;
import ru.liga.interning.trading.MixedBuilder;
import ru.liga.interning.trading.NestedFunctionOrderBuilder;
import ru.liga.interning.trading.Order;
import ru.liga.interning.trading.Stock;
import ru.liga.interning.trading.Trade;

import static ru.liga.interning.trading.MethodChainingOrderBuilder.forCustomer;
import static ru.liga.interning.trading.NestedFunctionOrderBuilder.at;
import static ru.liga.interning.trading.NestedFunctionOrderBuilder.buy;
import static ru.liga.interning.trading.NestedFunctionOrderBuilder.on;
import static ru.liga.interning.trading.NestedFunctionOrderBuilder.sell;
import static ru.liga.interning.trading.NestedFunctionOrderBuilder.stock;

/**
 * @author Repkin Andrey {@literal <arepkin@at-consulting.ru>}
 */
public class StockTest {

    public Order imperativeTest() {
        //Создаём биржевую заявку в императивном (не декларативном DSL) стиле
        Order order = new Order();
        order.setCustomer("BigBank");
        Trade trade1 = new Trade();
        trade1.setType(Trade.Type.BUY);
        Stock stock1 = new Stock();
        stock1.setSymbol("IBM");
        stock1.setMarket("NYSE");
        trade1.setStock(stock1);
        trade1.setPrice(125.00);
        trade1.setQuantity(80);
        order.addTrade(trade1);
        Trade trade2 = new Trade();
        trade2.setType(Trade.Type.SELL);
        Stock stock2 = new Stock();
        stock2.setSymbol("GOOGLE");
        stock2.setMarket("NASDAQ");
        trade2.setStock(stock2);
        trade2.setPrice(375.00);
        trade2.setQuantity(50);
        order.addTrade(trade2);
        Assertions.assertNotNull(order);
        return order;
    }

    public Order dslNonFunctionalTest() { //пример создание заявки на DSL в нефункциональном стиле
        Order order = forCustomer("BigBank")
                .buy(80)
                .stock("IBM")
                .on("NYSE")
                .at(125.00)
                .sell(50)
                .stock("GOOGLE")
                .on("NASDAQ")
                .at(375.00)
                .end();
        Assertions.assertNotNull(order);
        return order;
    }

    public Order dslNestedFunctionsTest() { //пример создание заявки на DSL в помощью вложенных функций
        Order order = NestedFunctionOrderBuilder.order("BigBank",
                buy(80,
                        stock("IBM", on("NYSE")),
                        at(125.00)),
                sell(50,
                        stock("GOOGLE", on("NASDAQ")),
                        at(375.00))
        );
        Assertions.assertNotNull(order);
        return order;
    }

    public Order dslLambdaTest() { //пример создание заявки на DSL в помощью вложенных функций
        Order order = LambdaOrderBuilder.order(o -> {
            o.forCustomer("BigBank");
            o.buy(t -> {
                t.quantity(80);
                t.price(125.00);
                t.stock(s -> {
                    s.symbol("IBM");
                    s.market("NYSE");
                });
            });
            o.sell(t -> {
                t.quantity(50);
                t.price(375.00);
                t.stock(s -> {
                    s.symbol("GOOGLE");
                    s.market("NASDAQ");
                });
            });
        });
        Assertions.assertNotNull(order);
        return order;
    }

    public Order dslNestedFunctionAndLambdaTest() { //пример создание заявки вариант DSL вложенных функций и DSL с lambda.
        Order order =
                MixedBuilder.forCustomer("BigBank",
                        MixedBuilder.buy(t -> t.quantity(80)
                                .stock("IBM")
                                .on("NYSE")
                                .at(125.00)),
                        MixedBuilder.sell(t -> t.quantity(50)
                                .stock("GOOGLE")
                                .on("NASDAQ")
                                .at(375))
                );
        Assertions.assertNotNull(order);
        return order;
    }

    @Test
    public void assertEqualsOrders() { //проверяем, что все способы создают идентичные объекты Order

    }
}
