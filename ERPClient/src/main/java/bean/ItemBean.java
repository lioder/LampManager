package bean;

import javafx.beans.property.*;

/**
 * Created by Kry·L on 2017/12/21.
 */
public class ItemBean{
    StringProperty date;
    StringProperty name;
    StringProperty isIn;
    IntegerProperty amount;
    StringProperty money;

    public ItemBean(String date, String name, String isIn, Integer amount, String money) {
        this.date = new SimpleStringProperty(date);
        this.name = new SimpleStringProperty(name);
        this.isIn = new SimpleStringProperty(isIn);
        this.amount = new SimpleIntegerProperty(amount);
        this.money = new SimpleStringProperty(money);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }


    public String getIsIn() {
        return isIn.get();
    }

    public StringProperty isInProperty() {
        return isIn;
    }

    public void setIsIn(String isIn) {
        this.isIn.set(isIn);
    }

    public int getAmount() {
        return amount.get();
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public String getMoney() {
        return money.get();
    }

    public StringProperty moneyProperty() {
        return money;
    }

    public void setMoney(String money) {
        this.money.set(money);
    }
}
