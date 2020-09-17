package ru.Norma24.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HeaderMainWidget extends Element {
    public HeaderMainProductList headerMainProductList;
    // Также сюда можно добавить другие элементы: Новости, Агентам, Документы, Личный кабинет и пр.

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public HeaderMainWidget(WebDriver driver) {
        super(driver);
        headerMainProductList = new HeaderMainProductList(driver);
        headerMainProductList.init(driver);
    }

     public HeaderMainWidget getProduct(String product) throws InterruptedException {
        headerMainProductList.goTo(product);
        return new HeaderMainWidget(driver);
    }
}
