package ru.Norma24.pages;

import org.openqa.selenium.WebDriver;
import ru.Norma24.components.ExpressGuaranteeCalculator;
import ru.Norma24.components.HeaderMainWidget;


public class Norma24 {
    WebDriver driver;

    public HeaderMainWidget headerMainWidget;
    public ExpressGuaranteeCalculator expressGuaranteeCalculator;

    public Norma24(WebDriver driver) {
        this.driver = driver;
        headerMainWidget = new HeaderMainWidget(driver);
        headerMainWidget.init(driver);

        expressGuaranteeCalculator = new ExpressGuaranteeCalculator(driver);
        expressGuaranteeCalculator.init(driver);
    }
}