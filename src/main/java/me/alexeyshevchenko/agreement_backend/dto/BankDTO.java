package me.alexeyshevchenko.agreement_backend.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BankDTO {
    private long id;

    @NotNull
    @Size(min=3, max=45)
    private String name;

    @NotNull
    @Size(min=3, max=45)
    private String account1;

    @NotNull
    @Size(min=3, max=45)
    private String account2;

    @NotNull
    @Size(min=6, max=6)
    private String mfo;

    public BankDTO() {
    }

    public BankDTO(long id, @NotNull @Size(min = 3, max = 45) String name, @NotNull @Size(min = 3, max = 45) String account1, @NotNull @Size(min = 3, max = 45) String account2, @NotNull @Size(min = 6, max = 6) String mfo) {
        this.id = id;
        this.name = name;
        this.account1 = account1;
        this.account2 = account2;
        this.mfo = mfo;
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
