package ru.Norma24.tests;

import org.junit.Test;

public class Norma24Test extends BaseTest {

    @Test()
    public void norma24Test_01() throws InterruptedException {
        norma24().headerMainWidget.getProduct("Экспресс-гарантии");
        norma24().expressGuaranteeCalculator.checkProductCalculator("12000000", 1001,
                "1053106.85");
    }
}



