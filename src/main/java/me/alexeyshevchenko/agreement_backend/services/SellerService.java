package me.alexeyshevchenko.agreement_backend.services;

import me.alexeyshevchenko.agreement_backend.dto.SellerDTO;
import me.alexeyshevchenko.agreement_backend.errors.SellerNotFoundException;
import me.alexeyshevchenko.agreement_backend.models.SellerEntity;
import me.alexeyshevchenko.agreement_backend.repository.SellerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SellerService {
  @Autowired
  private SellerEntityRepository repository;

  public SellerDTO createSeller (SellerDTO seller) {
    SellerEntity sellerToSave = new SellerEntity(seller);
    SellerEntity savedSeller = repository.save(sellerToSave);
    return new SellerDTO(savedSeller);
  }

  public SellerDTO getsellerById(Long id) throws SellerNotFoundException {
    Optional<SellerEntity> seller = repository.findById(id);
    SellerEntity sellerEntity = seller.orElseThrow(() -> new SellerNotFoundException(id));
    return new SellerDTO(sellerEntity);
  }

  // сделал т.к. нужен только для теста
  public SellerDTO findSellerByEdrpou(String edrpou) throws SellerNotFoundException {
    Optional<SellerEntity> seller = repository.findByedrpou(edrpou);
    SellerEntity sellerEntity = seller.orElseThrow(() -> new SellerNotFoundException(edrpou));
    return new SellerDTO(sellerEntity);
  }

  public SellerDTO updateSeller(SellerDTO seller) throws SellerNotFoundException {
    SellerEntity oldSeller = repository.findById(seller.getId())
            .orElseThrow(() -> new SellerNotFoundException(seller.getId()));
    oldSeller.setDirector(seller.getDirector());
    oldSeller.setEdrpou(seller.getEdrpou());
    oldSeller.setName(seller.getName());
    oldSeller.setNds(seller.isNds());
    repository.save(oldSeller);
    return new SellerDTO(oldSeller);
  }
  public SellerDTO deleteSeller(Long id) throws SellerNotFoundException {
    Optional<SellerEntity> existedSeller = repository.findById(id);
    SellerEntity seller = existedSeller.orElseThrow(() -> new SellerNotFoundException(id));
    repository.delete(seller);
    return new SellerDTO(seller);
  }
}

