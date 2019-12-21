import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class IssuesPage {
    @FindBy(id = "id_l.D.h.header")
    private WebElement header;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div/a[5]")
    private WebElement createIssueButton;

    @FindBy(id = "__popup__1")
    private WebElement popupSuccess;

    void newIssue() {
        createIssueButton.click();
    }

    boolean isSuccessful() {
        return popupSuccess.isDisplayed();
    }
}
