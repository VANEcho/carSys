package com.jkxy.car.api.service.Impl;

import com.jkxy.car.api.dao.CarDao;
import com.jkxy.car.api.pojo.BuyCar;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.pojo.FuzzyQuery;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car findById(int id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findByCarName(String carName) {
        return carDao.findByCarName(carName);
    }

    @Override
    public void deleteById(int id) {
        carDao.deleteById(id);
    }

    @Override
    public void updateById(Car car) {
        carDao.updateById(car);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public JSONResult buyCarById(BuyCar buyCar) {
        /*
        查询库存数量
         */
        int total = carDao.getTotal(buyCar.getId());
        if (total < buyCar.getNum()){
            return JSONResult.errorMsg("车辆库存不足");
        }
        /*
        执行购买操作时，对表加行锁
         */
        carDao.getLock(buyCar.getId());
        carDao.buyCar(total - buyCar.getNum(),buyCar.getId());
        return JSONResult.ok();
    }

    @Override
    public List<Car> fuzzyQueryByCarName(FuzzyQuery fuzzyQuery) {
        int startIndex = (fuzzyQuery.getPageNo() - 1) * fuzzyQuery.getPageSize();
        fuzzyQuery.setPageNo(startIndex);
        List<Car> cars = carDao.fuzzyQueryByCarName(fuzzyQuery);
        return carDao.fuzzyQueryByCarName(fuzzyQuery);
    }
}
