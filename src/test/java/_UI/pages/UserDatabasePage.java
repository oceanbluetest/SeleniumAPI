package _UI.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserDatabasePage {
    public UserDatabasePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table[@id='list-table']/tbody/tr")
    public List<WebElement> tableRows;
}
