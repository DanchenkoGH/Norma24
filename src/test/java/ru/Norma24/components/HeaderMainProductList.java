package ru.Norma24.components;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HeaderMainProductList extends Element {
    // Пукнт(кнопка) "Продукты"
    @FindBy(xpath = "//li/a[contains(@class, 'nav-link')]")
    private WebElement productButton;

    // Список продуктов
    @FindBy(xpath = "//div[contains(@class, 'dropdown-menu')]")
    private WebElement productMenu;

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public HeaderMainProductList(WebDriver driver) {
        super(driver);
    }

    //Для навигации по меню продуктов
    protected HeaderMainWidget goTo(String item) throws InterruptedException {
        String productHeader;

        System.out.println("Выполняется выбор подукта: \"" + item + "\"");
        productButton.click();
        productMenu.findElement(By.xpath("./span/a[. = '" + item + "']")).click();

        // Для того чтобы избавиться от лишнего передаваемого параметра с имененм хедера продукта
        // на открывшейся странице продукта, используем соответствия имен
        switch (item) {
            case "Экспресс-гарантии":
                productHeader = "Экспресс банковские гарантии";
                break;
            case "Банковские гарантии":
                productHeader = "Банковские гарантии";
                break;
            case "Тендерный займ":
                productHeader = "Кредиты для участников госзаказа";
                break;
            default:
                throw new IllegalStateException("Продукт: \"" + item + "\" отсутствует в списке. Добавьте, пожалуйста, " +
                        "хедер страницы продукта.");
        }
        System.out.println("Выбор продукта \"" + item + "\" выполнен успешно.");
        checkPageHeader(productHeader);
        return new HeaderMainWidget(driver);
    }

    //для проверки перехода на страницу продукта
    private HeaderMainWidget checkPageHeader(String productHeader) throws InterruptedException {
        wait(1000);
        try {
            waitVisiblityOfElement(By.xpath("//h1[. = '" + productHeader + "']"));
        } catch (TimeoutException error) {
            System.out.println("Переход на страницу продукта не был выполнен.\n" + error.getMessage());
            throw error;
        }
        return new HeaderMainWidget(driver);
    }
}
