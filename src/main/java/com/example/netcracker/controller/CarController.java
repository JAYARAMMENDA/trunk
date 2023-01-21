package com.example.netcracker.controller;

import com.example.netcracker.model.Car;
import com.example.netcracker.service.CarService;
import com.example.netcracker.service.ICarService;
import com.example.netcracker.service.SpecificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    private ICarService carService;
    //private SpecificationService specificationService;

    @GetMapping("/car")
    private List<Car> getAllCars() {
        return carService.getAllCars();
    }

    //creating a get mapping that retrieves the detail of a specific book
   /* @GetMapping("/car/{carId}")
    private Car getCar(@PathVariable("carId") int carId) {
        return carService.getOneCar(carId);
    }*/

    //creating delete mapping that deletes a specified book
    @DeleteMapping("/car/{carId}")
    private void deleteCar(@PathVariable("carId") int carId) {
        carService.deleteCar(carId);
    }

    //creating post mapping that post the book detail in the database
    @PostMapping("/saveCar")
    private int saveCar(@RequestBody Car car) {
        carService.saveCars(car);
        return car.getId();
    }

    @PostMapping("/saveCars")
    private Car saveCars(@RequestBody Car car) {
        return carService.saveAllCars(car);
    }

    //creating put mapping that updates the book detail
    @PutMapping("/UpdateCar")
    private  ResponseEntity<String> update(@RequestBody Car car) {
        ResponseEntity<String> resp=null;
        boolean present=carService.isPresent(car.getId());
        if(present){
            carService.update(car);
            resp=new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
        }
        else {
            resp=new ResponseEntity<String>("Record '"+car.getId()+" ' notfound",HttpStatus.BAD_REQUEST);
        }
        return resp;
    }

}
