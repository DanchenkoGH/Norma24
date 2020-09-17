package ru.Norma24.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.sql.SQLOutput;

public class ExpressGuaranteeProductResultSumPanel extends Element {

    public ExpressGuaranteeProductResultSumPanel(WebDriver driver) {
        super(driver);
    }

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getResultSum() {
        String resultSum = removeLastChar((driver.findElement(By.xpath("//span[@class = 'choose-calc-subtitle']")).
                getText().replaceAll("\\s", "")));
        System.out.println("По выбранным параметрам подсчитана сумма комиссии: " + resultSum);
        return resultSum;
    }

    private static String removeLastChar(String s) {
        return (s == null || s.length() == 0) ? null : (s.substring(0, s.length() - 1));
    }
}
