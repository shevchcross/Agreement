package me.alexeyshevchenko.agreement_backend.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class AgreementDTO {
    private long id;

    @NotNull
    private Date start;

    @NotNull
    private Date end;

    @NotNull
    private BigDecimal startAmount;

    @NotNull
    private BigDecimal currentAmount;

    public AgreementDTO() {
    }

    public AgreementDTO(long id, @NotNull Date start, @NotNull Date end, @NotNull BigDecimal startAmount, @NotNull BigDecimal currentAmount) {
        this.id = id;
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
