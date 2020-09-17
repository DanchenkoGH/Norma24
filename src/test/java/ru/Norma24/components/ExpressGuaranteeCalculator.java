package ru.Norma24.components;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

public class ExpressGuaranteeCalculator extends Element {
    public ExpressGuaranteeProductParameterSelectionPanel expressGuaranteeProductParameterSelectionPanel;
    public ExpressGuaranteeProductResultSumPanel expressGuaranteeProductResultSumPanel;

    public ExpressGuaranteeCalculator(WebDriver driver) {
        super(driver);
        expressGuaranteeProductParameterSelectionPanel = new ExpressGuaranteeProductParameterSelectionPanel(driver);
        expressGuaranteeProductParameterSelectionPanel.init(driver);

        expressGuaranteeProductResultSumPanel = new ExpressGuaranteeProductResultSumPanel(driver);
        expressGuaranteeProductResultSumPanel.init(driver);
    }

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ExpressGuaranteeCalculator checkProductCalculator(String guaranteeSum, int guaranteePeriod,
                                                             String guaranteeResultSum) throws InterruptedException {
        System.out.println("Выполняется проверка калькулятора для \"Экспресс-гарантии\"");
        expressGuaranteeProductParameterSelectionPanel.setGuaranteeSum(guaranteeSum);
        expressGuaranteeProductParameterSelectionPanel.setGuaranteePeriod(guaranteePeriod);
        assertTrue("Сумма коммисии расчитана неверно", guaranteeResultSum.equals(expressGuaranteeProductResultSumPanel.getResultSum()));
        System.out.println("Проверка калькулятора для \"Экспресс-гарантии\" выполнена успешно");
        return new ExpressGuaranteeCalculator(driver);
    }
}
