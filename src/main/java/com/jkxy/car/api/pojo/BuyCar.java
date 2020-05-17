package com.jkxy.car.api.pojo;

public class BuyCar {
    private int id;
    private int num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "BuyCar{" +
                "id=" + id +
                ", num=" + num +
                '}';
    }
}
