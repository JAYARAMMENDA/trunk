package com.example.netcracker.service;

import com.example.netcracker.model.Car;

import java.util.List;

public interface ICarService {

    public Integer saveCars(Car car);
    public Car saveAllCars(Car car);
    public List<Car> getAllCars();
    public Car getOneCar(int carId);
    public void deleteCar(Integer carId);
    public void update(Car cars);

    public boolean isPresent(Integer id) ;



}
