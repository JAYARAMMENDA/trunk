package com.example.netcracker.repository;

import com.example.netcracker.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional(propagation= Propagation.REQUIRED)
public interface CarRepository extends JpaRepository<Car, Integer> {



}
