import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class LoginPage {
    @FindBy(id = "id_l.L.login")
    private WebElement loginField;

    @FindBy(id = "id_l.L.password")
    private WebElement passwordField;

    @FindBy(id = "id_l.L.loginButton")
    private WebElement loginButton;

    void login(String login, String password) {
        this.loginField.sendKeys(login);
        this.passwordField.sendKeys(password);
        this.loginButton.click();
    }
}
