package me.alexeyshevchenko.agreement_backend.services;

import me.alexeyshevchenko.agreement_backend.dto.SpecificationDTO;
import me.alexeyshevchenko.agreement_backend.errors.SpecificationNotFoundException;
import me.alexeyshevchenko.agreement_backend.models.SpecificationEntity;
import me.alexeyshevchenko.agreement_backend.repository.SpecificationEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SpecificationService {
  @Autowired
  private SpecificationEntityRepository repository;

  public SpecificationDTO createSpecification (SpecificationDTO specification) {
    SpecificationEntity specificationToSave = new SpecificationEntity(specification);
    SpecificationEntity savedSpecification = repository.save(specificationToSave);
    return new SpecificationDTO(savedSpecification);
  }

  public SpecificationDTO getSpecificationById(Long id) throws SpecificationNotFoundException {
    Optional<SpecificationEntity> specification = repository.findById(id);
    SpecificationEntity specificationEntity = specification.orElseThrow(() -> new SpecificationNotFoundException(id));
    return new SpecificationDTO(specificationEntity);
  }

  // сделал т.к. нужен только для теста
  public SpecificationDTO findSpecificationByPrice(BigDecimal price) throws SpecificationNotFoundException {
    Optional<SpecificationEntity> specification = repository.findByPrice(price);
    SpecificationEntity specificationEntity = specification.orElseThrow(() -> new SpecificationNotFoundException(price));
    return new SpecificationDTO(specificationEntity);
  }

  public SpecificationDTO updateSpecification(SpecificationDTO specification) throws SpecificationNotFoundException {
    SpecificationEntity oldSpecification = repository.findById(specification.getId())
            .orElseThrow(() -> new SpecificationNotFoundException(specification.getId()));
    oldSpecification.setMesure(specification.getMesure());
    oldSpecification.setPrice(specification.getPrice());
    oldSpecification.setQuantity(specification.getQuantity());
    repository.save(oldSpecification);
    return new SpecificationDTO(oldSpecification);
  }
  public SpecificationDTO deleteSpecification(Long id) throws SpecificationNotFoundException {
    Optional<SpecificationEntity> existedSpecification = repository.findById(id);
    SpecificationEntity specification = existedSpecification.orElseThrow(() -> new SpecificationNotFoundException(id));
    repository.delete(specification);
    return new SpecificationDTO(specification);
  }
}

