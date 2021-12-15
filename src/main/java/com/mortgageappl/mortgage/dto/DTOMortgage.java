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
    private String phone;//номер телефона
    @NotEmpty
    private int    summa;//соответственно сумма ипотеки
    @NotEmpty
    private int    duration;//длительность - обычно в месяцах или годах
    @NotEmpty
    private String subject;//информация о недвижимости
    @NotEmpty
    private String supplier;//наименование или ФИО продавца
    @NotEmpty
    private String supAddress;//адрес продавца
    @NotEmpty
    private String inn;// ИНН

    public DTOMortgage(Mortgage mortgage) {
        this.id = mortgage.getId();
        this.customer = mortgage.getCustomer();
        this.passport = mortgage.getPassport();
        this.address = mortgage.getAddress();
        this.phone = mortgage.getPhone();
        this.summa = mortgage.getSumma();
        this.duration = mortgage.getDuration();
        this.subject = mortgage.getSubject();
        this.supplier = mortgage.getSupplier();
        this.supAddress = mortgage.getSupAddress();
        this.inn = mortgage.getInn();
    }

    public DTOMortgage(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DTOMortgage{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", passport='" + passport + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", summa=" + summa +
                ", duration=" + duration +
                ", subject='" + subject + '\'' +
                ", supplier='" + supplier + '\'' +
                ", supAddress='" + supAddress + '\'' +
                ", inn='" + inn + '\'' +
                '}';
    }
}
