import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageWalker {
    private WebDriver driver;
    private State state;

    PageWalker(WebDriver driver) {
        this.driver = driver;
        state = State.LOGIN;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public State getState() {
        return state;
    }

    public PageWalker login(String login, String password) {
        if (state != State.LOGIN) {
            throw new IllegalStateException();
        }
        state = State.DASHBOARD;

        LoginPage page = new LoginPage();
        PageFactory.initElements(driver, page);
        page.login(login, password);

        return this;
    }

    public PageWalker goToIssues() {
        if (state != State.DASHBOARD) {
            throw new IllegalStateException();
        }
        state = State.ISSUES;

        DashboardPage page = new DashboardPage();
        PageFactory.initElements(driver, page);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.goToIssues();

        return this;
    }

    public PageWalker newIssue() {
        if (state != State.ISSUES) {
            throw new IllegalStateException();
        }
        state = State.NEW_ISSUE;

        IssuesPage page = new IssuesPage();
        PageFactory.initElements(driver, page);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.newIssue();

        return this;
    }

    public PageWalker createIssue(String summary, String description) {
        if (state != State.NEW_ISSUE) {
            throw new IllegalStateException();
        }
        state = State.ISSUES;

        CreateIssuePage page = new CreateIssuePage();
        PageFactory.initElements(driver, page);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.createIssue(summary, description);

        return this;
    }

    public void close() {
        state = State.CLOSED;
        driver.close();
    }
}
