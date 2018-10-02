package me.alexeyshevchenko.agreement_backend.services;

import me.alexeyshevchenko.agreement_backend.dto.AgreementDTO;
import me.alexeyshevchenko.agreement_backend.errors.AgreementNotFoundException;
import me.alexeyshevchenko.agreement_backend.models.AgreementEntity;
import me.alexeyshevchenko.agreement_backend.repository.AgreementEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AgreementService {
  @Autowired
  private AgreementEntityRepository repository;

  public AgreementDTO createAgreement (AgreementDTO agreement) {
    AgreementEntity agreementToSave = new AgreementEntity(agreement);
    AgreementEntity savedAgreement = repository.save(agreementToSave);
    return new AgreementDTO(savedAgreement);
  }

  public AgreementDTO getAgreementById(Long id) throws AgreementNotFoundException {
    Optional<AgreementEntity> agreement = repository.findById(id);
    AgreementEntity agreementEntity = agreement.orElseThrow(() -> new AgreementNotFoundException(id));
    return new AgreementDTO(agreementEntity);
  }

  // сделал т.к. нужен только для теста
  public AgreementDTO findAgreementByStartAmount(BigDecimal startAmount) throws AgreementNotFoundException {
    Optional<AgreementEntity> agreement = repository.findByStartAmount(startAmount);
    AgreementEntity agreementEntity = agreement.orElseThrow(() -> new AgreementNotFoundException(startAmount));
    return new AgreementDTO(agreementEntity);
  }

  public AgreementDTO updateAgreement(AgreementDTO agreement) throws AgreementNotFoundException {
    AgreementEntity oldAgreement = repository.findById(agreement.getId())
            .orElseThrow(() -> new AgreementNotFoundException(agreement.getId()));
    oldAgreement.setCurrentAmount(agreement.getCurrentAmount());
    oldAgreement.setEnd(agreement.getEnd());
    oldAgreement.setStart(agreement.getStart());
    oldAgreement.setStartAmount(agreement.getStartAmount());
    repository.save(oldAgreement);
    return new AgreementDTO(oldAgreement);
  }
  public AgreementDTO deleteAgreement(Long id) throws AgreementNotFoundException {
    Optional<AgreementEntity> existedAgreement = repository.findById(id);
    AgreementEntity agreement = existedAgreement.orElseThrow(() -> new AgreementNotFoundException(id));
    repository.delete(agreement);
    return new AgreementDTO(agreement);
  }
}

