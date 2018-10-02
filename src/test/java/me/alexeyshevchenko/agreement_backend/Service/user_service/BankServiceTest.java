package me.alexeyshevchenko.agreement_backend.Service.user_service;

import me.alexeyshevchenko.agreement_backend.dto.BankDTO;
import me.alexeyshevchenko.agreement_backend.errors.BankNotFoundException;
import me.alexeyshevchenko.agreement_backend.repository.BankEntityRepository;
import me.alexeyshevchenko.agreement_backend.services.BankService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankServiceTest {
    @Autowired
    private BankService service;

    @Autowired
    private BankEntityRepository repository;

    @BeforeEach
    public void init() {
        repository.deleteAll();
    }

    @AfterEach
    public void after() {
        repository.deleteAll();
    }

    @Test
    public void createBankSuccessfull() throws Exception {
        BankDTO bankInput = service.createBanke(new BankDTO("Privat", "123456789", "987654321", "123456"));
        BankDTO bankFomeBase = service.findBankByMfo("123456");
        Long a = bankFomeBase.getId();
        assertThat(a, greaterThan(0L));
    }

    @Test
    public void getBankById() throws Exception {
        BankDTO bankInput = service.createBanke(new BankDTO("Privat", "123456789", "987654321", "123456"));
        BankDTO BankFomeBase = service.getBankById(bankInput.getId());

        assertThat(BankFomeBase.getName(), equalToIgnoringCase(bankInput.getName()));
    }

    @Test
    public void getBankByIdBankNotFound() throws Exception {
        BankDTO bankInput = service.createBanke(new BankDTO("Privat", "123456789", "987654321", "123456"));
        assertThrows(BankNotFoundException.class,
                () -> {
                    service.getBankById(bankInput.getId() + 1);
                });
    }

    @Test
    public void updateProductSuccessfull() throws Exception {
        BankDTO bankInput = service.createBanke(new BankDTO("Privat", "123456789", "987654321", "123456"));
        BankDTO bankUpdate = service.updateBank(new BankDTO(bankInput.getId(),"Aval", "1111111111", "2222222222", "222333"));
        BankDTO newBank = service.getBankById(bankInput.getId());
        assertThat(newBank.getName(), equalToIgnoringCase(bankUpdate.getName()));
    }

    @Test
    public void updateProductNotFound() throws Exception {
        BankDTO bankInput = service.createBanke(new BankDTO("Privat", "123456789", "987654321", "123456"));
        BankDTO bankUpdate = new BankDTO(bankInput.getId()+1,"Aval", "1111111111", "2222222222", "222333");

        assertThrows(BankNotFoundException.class,
                () -> {
                    service.updateBank(bankUpdate);
                });
    }
    @Test
    public void deleteBankNotFound() throws Exception{
        BankDTO bankInput = service.createBanke(new BankDTO("Privat", "123456789", "987654321", "123456"));
        assertThrows(BankNotFoundException.class,
                ()->{
                    service.deleteBank(bankInput.getId()+1);
                });
    }

}
