package com.example.netcracker.service;

import com.example.netcracker.model.Specification;

import java.util.List;

public interface ISpecificationService {
    public Integer saveSpecification(Specification specification);
    public Specification saveAllSpecification(Specification specification);
    public List<Specification> getAllSpecifications();
    public Specification getOneSpecification(int SpecificId);
    public void deleteSpecification(int SpecificId);
    public void updateSpecification(Specification specification);
}
