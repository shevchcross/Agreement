package me.alexeyshevchenko.agreement_backend.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "MY_AGREEMENT_ENTITY")
public class AgreementEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID_MY_AGREEMENT_ENTITY")
    private long id;

    @NotNull
    @Column(name = "START_MY_AGREEMENT_ENTITY")
    private Date start;

    @NotNull
    @Column(name = "END_MY_AGREEMENT_ENTITY")
    private Date end;

    @NotNull
    @Column(name = "START_AMOUNT_MY_AGREEMENT_ENTITY")
    private  BigDecimal startAmount;

    @NotNull
    @Column(name = "CURRENT_AMOUNT_MY_AGREEMENT_ENTITY")
    private BigDecimal currentAmount;

    public AgreementEntity() {
    }

    public AgreementEntity(@NotNull Date start, @NotNull Date end, @NotNull BigDecimal startAmount, @NotNull BigDecimal currentAmount) {
        this.start = start;
        this.end = end;
        this.startAmount = startAmount;
        this.currentAmount = currentAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public BigDecimal getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(BigDecimal startAmount) {
        this.startAmount = startAmount;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }
}
