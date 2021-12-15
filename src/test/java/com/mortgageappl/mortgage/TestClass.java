package com.mortgageappl.mortgage;

import com.mortgageappl.mortgage.exseption.MismatchCheckException;
import com.mortgageappl.mortgage.services.CheckAll;
import org.assertj.core.api.Assertions;
import org.junit.Test;


public class TestClass {


    @Test(expected = MismatchCheckException.class)
    public void testCheckIncorrect() throws MismatchCheckException {
        CheckAll checkAll = new CheckAll();
        checkAll.checkInn("35300351463412");

    }

    @Test
    public void testCheckCorrect() throws MismatchCheckException {
        CheckAll checkAll = new CheckAll();
        checkAll.checkInn("353003514634");
        Assertions.assertThatNoException();
    }
}
