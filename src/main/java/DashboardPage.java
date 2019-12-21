import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class DashboardPage {
    @FindBy(xpath = "/html/body/div[1]/div[1]/div/a[2]")
    private WebElement issuesButton;

    void goToIssues() {
        issuesButton.click();
    }
}
