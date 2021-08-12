package com.mortgageappl.mortgage.dto;

import com.mortgageappl.mortgage.model.Mortgage;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Validated
public class DTOMortgage {
    @NotEmpty
    private long id;
    @NotEmpty
    private String customer;// ФИО продавца
    @NotEmpty
    private String passport;//данные паспорта
    @NotEmpty
    private String address;//адрес

    @NotEmpty
    private String phon;//номер телефона
    @NotEmpty
    private int    summa;//соответственно сумма ипотеки
    @NotEmpty
    private int    duration;//длительность - обычно в месяцах или годах
    @NotEmpty
    private String subject;//информацио недвижимости
    @NotEmpty
    private String supplier;//наименование или ФИО продавца
    @NotEmpty
    private String supAddress;//адрес продавца
    @NotEmpty
    private String inn;// ИНН

    public DTOMortgage(Mortgage morgage) {
        this.id = morgage.getId();
        this.customer = morgage.getCustomer();
        this.passport = morgage.getPassport();
        this.address = morgage.getAddress();
        this.phon = morgage.getPhon();
        this.summa = morgage.getSumma();
        this.duration = morgage.getDuration();
        this.subject = morgage.getSubject();
        this.supplier = morgage.getSupplier();
        this.supAddress = morgage.getSupAddress();
        this.inn = morgage.getInn();
    }

    public DTOMortgage(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DTOMorgage{" +
                "id=" + id +
                ", castomer='" + customer + '\'' +
                ", passport='" + passport + '\'' +
                ", address='" + address + '\'' +
                ", phon='" + phon + '\'' +
                ", summa=" + summa +
                ", duration=" + duration +
                ", subject='" + subject + '\'' +
                ", supplier='" + supplier + '\'' +
                ", supAddress='" + supAddress + '\'' +
                ", inn='" + inn + '\'' +
                '}';
    }
}
