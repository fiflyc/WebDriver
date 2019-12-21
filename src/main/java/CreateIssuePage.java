import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class CreateIssuePage {
    @FindBy(id = "id_l.I.ni.ei.eit.summary")
    private WebElement summaryField;

    @FindBy(id = "id_l.I.ni.ei.eit.description")
    private WebElement descriptionField;

    @FindBy(id = "id_l.I.ni.ei.submitButton_74_0")
    private WebElement submitButton;

    @FindBy(id = "__popup__1")
    private WebElement popupError;

    void createIssue(String summary, String description) {
        summaryField.sendKeys(summary);
        descriptionField.sendKeys(description);
        submitButton.click();
    }

    boolean wasError(String message) {
        return popupError.isDisplayed() && popupError.getText().contains(message);
    }
}
