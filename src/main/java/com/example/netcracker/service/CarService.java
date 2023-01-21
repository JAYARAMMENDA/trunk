package com.example.netcracker.service;

import com.example.netcracker.model.Car;
import com.example.netcracker.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class CarService implements ICarService {
    private CarRepository carRepository;
    @Override
    public Integer saveCars(Car car) {
      /*Car e1=carRepository.save(car);
        Integer id=e1.getId();
        return id;*/
        return carRepository.save(car).getId();
    }

    @Override
    public Car saveAllCars(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return  carRepository.findAll();
    }

    @Override
    public Car getOneCar(int carId) {
        Optional<Car> car= carRepository.findById(carId);
        if(car.isPresent()) {
            return car.get();
        }
        /*Throw the Exception Need to Implement*/
        return null;
    }

    @Override
    public void deleteCar(Integer carId) {
        carRepository.deleteById(carId);
    }

    @Override
    public void update(Car cars) {
        carRepository.save(cars);
    }

    @Transactional(readOnly = true)
    public boolean isPresent(Integer id) {
        return carRepository.existsById(id);
    }
}

