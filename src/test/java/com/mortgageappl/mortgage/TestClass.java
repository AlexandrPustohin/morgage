package com.mortgageappl.mortgage;

import com.mortgageappl.mortgage.exseption.MissmachCheckExeption;
import com.mortgageappl.mortgage.services.CheckAll;
import org.assertj.core.api.Assertions;
import org.junit.Test;


public class TestClass {


    @Test(expected = MissmachCheckExeption.class)
    public void testCheckIncorrect() throws MissmachCheckExeption {
        CheckAll checkAll = new CheckAll();
        checkAll.checkInn("35300351463412");

    }

    @Test
    public void testCheckCorrect() throws MissmachCheckExeption {
        CheckAll checkAll = new CheckAll();
        checkAll.checkInn("353003514634");
        Assertions.assertThatNoException();
    }
}
