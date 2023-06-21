package fotius.example.todo;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;
import java.util.List;

class TodoListWebPageTest {

  private WebDriver driver;

  @BeforeEach
  void setup() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    driver = new ChromeDriver(options);
  }

  @AfterEach
  void cleanup() {
    // TODO: comment quit method call to keep browser open after test
    driver.quit();
  }

  @Test
  void addItemsToList() {
    goToHtmlPage();

    final WebElement todoInput = driver.findElement(By.id("todo"));
    final WebElement addButton = driver.findElement(By.id("add"));

    todoInput.sendKeys("Element 1");
    addButton.click();
    todoInput.sendKeys("Element 2");
    addButton.click();

    Assertions.assertTrue(todoInput.getText().isEmpty());

    List<WebElement> items = driver.findElement(By.id("todos")).findElements(By.className("list-group-item"));
    Assertions.assertEquals(2, items.size());
  }

  @Test
  void deleteItemFromList() {
    goToHtmlPage();

    final WebElement todoInput = driver.findElement(By.id("todo"));
    final WebElement addButton = driver.findElement(By.id("add"));

    todoInput.sendKeys("Element 1");
    addButton.click();
    todoInput.sendKeys("Element 2");
    addButton.click();
    todoInput.sendKeys("Element 3");
    addButton.click();

    List<WebElement> buttons = driver.findElements(By.className("btn-danger"));
    buttons.get(2).click();

    List<WebElement> items = driver.findElement(By.id("todos")).findElements(By.className("list-group-item"));
    Assertions.assertEquals(2, items.size());
  }

  void goToHtmlPage() {
    driver.get(
        "file://" +
            Paths.get(System.getProperty("user.dir"))
                .resolve("src")
                .resolve("main")
                .resolve("resources")
                .resolve("todo.html")
                .toAbsolutePath()
    );
  }
}
