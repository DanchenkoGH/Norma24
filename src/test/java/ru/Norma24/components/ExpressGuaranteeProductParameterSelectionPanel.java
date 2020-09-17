package ru.Norma24.components;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ExpressGuaranteeProductParameterSelectionPanel extends Element {
    @FindBy(xpath = "//div[@class = 'choose-calc choose-product']")
    private WebElement productParameterPanel;

    public ExpressGuaranteeProductParameterSelectionPanel(WebDriver driver) {
        super(driver);
    }

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ExpressGuaranteeCalculator setGuaranteeSum(String sum) throws InterruptedException {
        //TODO Хорошим решением было бы вынести все локаторы в начало модуля
        WebElement guaranteeSum = productParameterPanel.findElement(By.xpath("//div[@class = 'slider-calc']/input"));
        //Отчистили поле ввода. Возможно есть более красивое решение, можно поискать
        guaranteeSum.sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        wait(500);
        assertTrue("Сумма гарантии должна быть от 10000 до 14000000. Исправьте и повторите.", (Integer.parseInt(sum) > 10000) & (Integer.parseInt(sum) < 14000000));
        guaranteeSum.sendKeys(sum);
        System.out.println("Сумма гарантии выставлен: " + sum);
        return new ExpressGuaranteeCalculator(driver);
    }

    public ExpressGuaranteeCalculator setGuaranteePeriod(int days) throws InterruptedException {
        //TODO найти другой локатор
        WebElement slider = productParameterPanel.findElement(By.xpath("(//div[@class = 'vue-slider-dot-handle'])[2]"));
        Actions move = new Actions(driver);
        //Сбросим ползунок в нулевую точку
        Action action = (Action) move.dragAndDropBy(slider, -87, 0).build();
        action.perform();
        //Шаг нестандартный, поэтому используем карту соответствия
        assertTrue("Срок гарантии должен быть не менее 10 дней и не более 1825. Исправьте и повторите.", (days >= 10) & (days <= 1825));
        action = (Action) move.dragAndDropBy(slider, myMap.get(days), 0).build();
        action.perform();
        wait(500);
        System.out.println("Срок гарантии выставлен: " + days);
        return new ExpressGuaranteeCalculator(driver);
    }

    // Шаг соотношения положения ползунка и срока гарантии не стандартный(2-5 дней), то используем такую карту
    // Возможно лучше вынести в отдельный модуль с общими данными(а может он здесь вообще не нужен)
    Map<Integer, Integer> myMap = new HashMap<Integer, Integer>() {{
        put(10, 0);
        put(12, 1);
        put(16, 2);
        put(20, 3);
        put(24, 4);

        put(989, 240);
        put(993, 241);
        put(997, 242);
        put(1001, 243);
        put(1005, 244);
        put(1009, 245);

    }};
}
