package me.alexeyshevchenko.agreement_backend.models;
import me.alexeyshevchenko.agreement_backend.dto.BankDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "MY_BANK_ENTITY")
public class BankEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID_MY_BANK_ENTITY")
    private long id;

    @NotNull
    @Size(min=3, max=45)
    @Column(name = "NAME_MY_BANK_ENTITY")
    private String name;

    @NotNull
    @Size(min=3, max=45)
    @Column(name = "ACCOUNT1_MY_BANK_ENTITY")
    private String account1;

    @NotNull
    @Size(min=3, max=45)
    @Column(name = "ACCOUNT2_MY_BANK_ENTITY")
    private String account2;

    @NotNull
    @Size(min=6, max=6)
    @Column(name = "MFO_MY_BANK_ENTITY")
    private String mfo;

    public BankEntity() {
    }

    public BankEntity(@NotNull @Size(min = 3, max = 45) String name, @NotNull @Size(min = 3, max = 45) String account1, @NotNull @Size(min = 3, max = 45) String account2, @NotNull @Size(min = 6, max = 6) String mfo) {
        this.name = name;
        this.account1 = account1;
        this.account2 = account2;
        this.mfo = mfo;
    }

    public BankEntity(BankDTO bankDTO) {
        this.name = bankDTO.getName();
        this.account1 = bankDTO.getAccount1();
        this.account2 = bankDTO.getAccount2();
        this.mfo = bankDTO.getMfo();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount1() {
        return account1;
    }

    public void setAccount1(String account1) {
        this.account1 = account1;
    }

    public String getAccount2() {
        return account2;
    }

    public void setAccount2(String account2) {
        this.account2 = account2;
    }

    public String getMfo() {
        return mfo;
    }

    public void setMfo(String mfo) {
        this.mfo = mfo;
    }
}
