package com.mortgageappl.mortgage.services;

import com.mortgageappl.mortgage.exseption.MissmachCheckExeption;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@Component
public class CheckAll {

    private static final Pattern innPatter = Pattern.compile("\\d{10}|\\d{12}");
    private static final int[] checkArr = new int[] {3,7,2,4,10,3,5,9,4,6,8};
    public void checkInn(String inn) throws MissmachCheckExeption {
        inn = inn.trim();
        if (!innPatter.matcher(inn).matches()) {
            throw new MissmachCheckExeption("Не правильный ИНН. Должен быть 10 или 12 цифр");
        }
        boolean isCorrectInn=true;
        int length = inn.length();
        if (length == 12) {
            if (!( INNStep(inn, 2, 1) && INNStep(inn, 1, 0))) {
                isCorrectInn = false;
            }
        } else {
            if (!INNStep(inn, 1, 2)){
                isCorrectInn = false;
            }
        }
        if (! isCorrectInn)
            throw new MissmachCheckExeption("Не правильный ИНН. Проверьте!");
    }

    private static boolean INNStep(String inn, int offset, int arrOffset) {
        int sum = 0;
        int length = inn.length();
        for (int i = 0; i < length - offset; i++) {
            sum += (inn.charAt(i) - '0') * checkArr[i + arrOffset];
        }
        return (sum % 11) % 10 == inn.charAt(length - offset) - '0';
    }

}
