package com.mortgageappl.mortgage.dto;

import com.mortgageappl.mortgage.model.Mortgage;


public class DTOMortgage {
    private long id;
    private String castomer;// ФИО продавца
    private String passport;//данные паспорта
    private String address;//адрес
    private String phon;//номер телефона
    private int    summa;//соответственно сумма ипотеки
    private int    duration;//длительность - обычно в месяцах или годах
    private String subject;//информацио недвижимости
    private String supplier;//наименование или ФИО продавца
    private String supAddress;//адрес продавца
    private String inn;// ИНН

    public DTOMortgage(Mortgage morgage) {
        this.id = morgage.getId();
        this.castomer = morgage.getCastomer();
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
}
