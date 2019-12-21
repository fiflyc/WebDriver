import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

class CreateIssueTest {
    private static PageWalker walker;

    @BeforeAll
    private static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    private void init() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("localhost:8080/login");
        walker = new PageWalker(driver);
    }

    @AfterEach
    private void clean() {
        walker.close();
    }

    private void checkSuccess() {
        WebDriver driver = walker.getDriver();
        IssuesPage page = new IssuesPage();
        PageFactory.initElements(driver, page);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(_driver -> page.isSuccessful());

        assertTrue(page.isSuccessful());
    }

    private void checkError(String message) {
        WebDriver driver = walker.getDriver();
        CreateIssuePage page = new CreateIssuePage();
        PageFactory.initElements(driver, page);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(_driver -> page.wasError(message));

        assertTrue(page.wasError(message));
    }

    private void createIssue(String summary, String description) {
        walker
                .login("tester", "test")
                .goToIssues()
                .newIssue()
                .createIssue(summary, description);
    }

    @Test
    void createIssue_OneLineSummaryOneLineDescription_Success() {
        createIssue("Some Summary", "Some Description");
        checkSuccess();
    }

    @Test
    void createIssue_OneLineSummaryEmptyDescription_Success() {
        createIssue("Some Summary", "");
        checkSuccess();
    }

    @Test
    void createIssue_EmptySummary_Fail() {
        createIssue("", "Some Description");
        checkError("Summary is required");
    }

    @Test
    void createIssue_MultiLineSummary_Success() {
        createIssue("Some\nSummary", "Diamonds are a girl's best friend");
        checkSuccess();
    }

    @Test
    void createIssue_MultiLineDescription_Success() {
        createIssue("Some Summary", "Some\nDescription");
        checkSuccess();
    }

    @Test
    void createIssue_WhiteSpacesAsSummary_Success() {
        createIssue("  \t", "Some Description");
        checkSuccess();
    }

    @Test
    void createIssue_LargeSummary_Success() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            builder.append(i);
        }
        createIssue(builder.toString(), "Some Description");
        checkSuccess();
    }

    @Test
    void createIssue_LargeDescription_Success() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            builder.append(i);
        }
        createIssue("Some Summary", builder.toString());
        checkSuccess();
    }

    @Test
    void createIssue_EmojiInSummary_Success() {
        createIssue("Do you see emoji?\uD83E", "Some Description");
        checkSuccess();  // Actually, I didn't see an emoji
    }

    @Test
    void createIssue_EmojiInDescription_Success() {
        createIssue("Some Summary", "Do you see emoji?\uD83E");
        checkSuccess();  // Actually, I didn't see an emoji
    }
}