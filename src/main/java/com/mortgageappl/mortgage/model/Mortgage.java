package com.mortgageappl.mortgage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mortgage")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mortgage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "castomer")
    private String castomer;// ФИО продавца

    @Column(name = "passport")
    private String passport;//данные паспорта

    @Column(name = "address")
    private String address;//адрес

    @Column(name = "phon")
    private String phon;//номер телефона

    @Column(name = "summa")
    private int    summa;//соответственно сумма ипотеки

    @Column(name = "duration")
    private int    duration;//длительность - обычно в месяцах или годах

    @Column(name = "subject")
    private String subject;//информацио недвижимости

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
                ", castomer='" + castomer + '\'' +
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

    public Mortgage(long id, String castomer, String passport, String address,
                    String phon, int summa, int duration, String subject,
                    String supplier, String supAddress, String inn) {
        this.id = id;
        this.castomer = castomer;
        this.passport = passport;
        this.address = address;
        this.phon = phon;
        this.summa = summa;
        this.duration = duration;
        this.subject = subject;
        this.supplier = supplier;
        this.supAddress = supAddress;
        this.inn = inn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCastomer() {
        return castomer;
    }

    public void setCastomer(String castomer) {
        this.castomer = castomer;
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

    public String getPhon() {
        return phon;
    }

    public void setPhon(String phon) {
        this.phon = phon;
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
        return id == mortgage.id && summa == mortgage.summa && duration == mortgage.duration && Objects.equals(castomer, mortgage.castomer) && Objects.equals(passport, mortgage.passport) && Objects.equals(address, mortgage.address) && Objects.equals(phon, mortgage.phon) && Objects.equals(subject, mortgage.subject) && Objects.equals(supplier, mortgage.supplier) && Objects.equals(supAddress, mortgage.supAddress) && Objects.equals(inn, mortgage.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, castomer, passport, address, phon, summa, duration, subject, supplier, supAddress, inn);
    }
}
