package me.alexeyshevchenko.agreement_backend.services;

import me.alexeyshevchenko.agreement_backend.dto.BankDTO;
import me.alexeyshevchenko.agreement_backend.errors.BankNotFoundException;
import me.alexeyshevchenko.agreement_backend.models.BankEntity;
import me.alexeyshevchenko.agreement_backend.repository.BankEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankEntityRepository bankEntityRepository;

    public BankDTO createBanke (BankDTO bank) {
        BankEntity bankToSave = new BankEntity(bank);
        BankEntity savedBank = bankEntityRepository.save(bankToSave);
        return new BankDTO(savedBank);
    }

    public BankDTO getBankById(Long id) throws BankNotFoundException {
        Optional<BankEntity> bank = bankEntityRepository.findById(id);
        BankEntity bankEntity = bank.orElseThrow(() -> new BankNotFoundException(id));
        return new BankDTO(bankEntity);
    }

    // сделал т.к. нужен только для теста
    public BankDTO findBankByMfo(String mfo) throws BankNotFoundException {
        Optional<BankEntity> bank = bankEntityRepository.findByMfo(mfo);
        BankEntity bankEntity = bank.orElseThrow(() -> new BankNotFoundException(mfo));
        return new BankDTO(bankEntity);
    }

    public BankDTO updateBank(BankDTO bank) throws BankNotFoundException {
        BankEntity oldBank = bankEntityRepository.findById(bank.getId())
                .orElseThrow(() -> new BankNotFoundException(bank.getId()));
        oldBank.setAccount1(bank.getAccount1());
        oldBank.setAccount2(bank.getAccount2());
        oldBank.setMfo(bank.getMfo());
        oldBank.setName(bank.getName());
        bankEntityRepository.save(oldBank);
        return new BankDTO(oldBank);
    }
    public BankDTO deleteBank(Long id) throws BankNotFoundException {
        Optional<BankEntity> existedBank = bankEntityRepository.findById(id);
        BankEntity bank = existedBank.orElseThrow(() -> new BankNotFoundException(id));
        bankEntityRepository.delete(bank);
        return new BankDTO(bank);
    }
}


