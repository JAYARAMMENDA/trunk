
package com.example.netcracker.service;
import com.example.netcracker.model.Specification;
import com.example.netcracker.repository.SpecificationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class SpecificationService implements ISpecificationService {
    private SpecificationRepository specificRepository;
    @Override
    public Integer saveSpecification(Specification specification) {

    /*Specification specific=specificRepository.save(specification);
        Integer id=specific.getId();
        return id;*/

        return specificRepository.save(specification).getId();
    }

    @Override
    public Specification saveAllSpecification(Specification specification) {
        return specificRepository.save(specification);
    }

    @Override
    public List<Specification> getAllSpecifications() {
        return specificRepository.findAll();
    }

    @Override
    public Specification getOneSpecification(int SpecificId) {
        Optional<Specification> specification = specificRepository.findById(SpecificId);
        if (specification.isPresent()){
            return specification.get();
        }
        return null;
    }


    @Override
    public void deleteSpecification(int SpecificId) {
        specificRepository.deleteById(SpecificId);
    }

    @Override
    public void updateSpecification(Specification specification) {
        specificRepository.save(specification);
    }
}

