package com.jkxy.car.api.service;

import com.jkxy.car.api.pojo.BuyCar;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.pojo.FuzzyQuery;
import com.jkxy.car.api.utils.JSONResult;

import java.util.List;


public interface CarService {

    List<Car> findAll();

    Car findById(int id);

    List<Car> findByCarName(String carName);

    void deleteById(int id);

    void updateById(Car car);

    void insertCar(Car car);

    JSONResult buyCarById(BuyCar buyCar);

    List<Car> fuzzyQueryByCarName(FuzzyQuery fuzzyQuery);
}
