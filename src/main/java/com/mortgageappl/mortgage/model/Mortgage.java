package com.mortgageappl.mortgage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mortgage")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Validated
public class Mortgage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "customer")
    private String customer;// ФИО продавца

    @Column(name = "passport")
    private String passport;//данные паспорта

    @Column(name = "address")
    private String address;//адрес

    @Column(name = "phone")
    private String phone;//номер телефона

    @Column(name = "summa")
    private int    summa;//соответственно сумма ипотеки

    @Column(name = "duration")
    private int    duration;//длительность - обычно в месяцах или годах

    @Column(name = "subject")
    private String subject;//информация недвижимости

    @Column(name = "supplier")
    private String supplier;//наименование или ФИО продавца

    @Column(name = "supAddress")
    private String supAddress;//адрес продавца

    @Column(name = "inn")
    private String inn;// ИНН

    public Mortgage() {
    }

    @Override
    public String toString() {
        return "Mortgage{" +
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

    public Mortgage(Long id, String customer, String passport, String address,
                    String phone, int summa, int duration, String subject,
                    String supplier, String supAddress, String inn) {
        this.id = id;
        this.customer = customer;
        this.passport = passport;
        this.address = address;
        this.phone = phone;
        this.summa = summa;
        this.duration = duration;
        this.subject = subject;
        this.supplier = supplier;
        this.supAddress = supAddress;
        this.inn = inn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSumma() {
        return summa;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupAddress() {
        return supAddress;
    }

    public void setSupAddress(String supAddress) {
        this.supAddress = supAddress;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mortgage mortgage = (Mortgage) o;
        return id == mortgage.id && summa == mortgage.summa && duration == mortgage.duration && Objects.equals(customer, mortgage.customer) && Objects.equals(passport, mortgage.passport) && Objects.equals(address, mortgage.address) && Objects.equals(phone, mortgage.phone) && Objects.equals(subject, mortgage.subject) && Objects.equals(supplier, mortgage.supplier) && Objects.equals(supAddress, mortgage.supAddress) && Objects.equals(inn, mortgage.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, passport, address, phone, summa, duration, subject, supplier, supAddress, inn);
    }
}
