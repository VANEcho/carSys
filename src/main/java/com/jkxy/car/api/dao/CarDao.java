package com.jkxy.car.api.dao;

import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.pojo.FuzzyQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CarDao {
    @Select("select * from carMessage")
    List<Car> findAll();

    @Select("select * from carMessage where id = #{id}")
    Car findById(int id);

    @Select("select * from carMessage where carName = #{carName}")
    List<Car> findByCarName(String carName);

    @Delete("delete from carMessage where id = #{id}")
    void deleteById(int id);

    @Update("update carMessage set carName=#{carName},carType=#{carType},price=#{price},carSeries=#{carSeries} where id = #{id}")
    void updateById(Car car);

    @Insert("insert into carMessage(carName,carType,price,carSeries) values(#{carName},#{carType},#{price},#{carSeries})")
    void insertCar(Car car);

    @Select("select * from carnum where id = #{id}")
    int getLock(int id);

    @Update("update carnum set carNum = #{surplus} where id = #{id}")
    boolean buyCar(int surplus,int id);

    @Select("select carNum from carnum where id = #{id}")
    int getTotal(int id);

    @Select("select * from carMessage where carmessage.carName like CONCAT('%',#{carName},'%') limit #{pageNo},#{pageSize}")
    List<Car> fuzzyQueryByCarName(FuzzyQuery fuzzyQuery);
}
