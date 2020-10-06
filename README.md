# parameterized-by

The library supports creating parameterized locators with annotations for Selenium page objects which are initialized with `PageFactory` class.

## Usage examples and conception

The conception is that you define your locators as usual but place `{wec:parameter.name}` that is parsed and replaced with the parameter name you provide.

You provide the parameter name using the implementation of `IParameterProvider`. There is default implementation `DefaultParameterProvider` supplied that is thread-safe which allows you to safely use it in your prallelized tests.

Add the following dependency to your project:

```xml
<dependency>
  <groupId>click.webelement</groupId>
  <artifactId>parameterized-findby</artifactId>
  <version>1.0.0</version>
</dependency>
```

The easiest way to create a page with parameterized locators is to derive your page from `PageObjectParameterized` like shown below:

```java
import click.webelement.pagefactory.parameterized.FindByParameterized;
import click.webelement.pagefactory.parameterized.PageObjectParameterized;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyPage extends PageObjectParameterized {

    @FindByParameterized(xpath = "//button[@name='{wec:btn.name}']")
    WebElement button;

    @FindByParameterized(xpath = "//label[text()='{wec:lbl.text}']")
    WebElement label;

    @FindBy(xpath = "//input")
    WebElement input;
    
    public MyPage(SearchContext searchContext) {
        super(searchContext);
    }
}
```

What we see here:

1. We define locator using annotation `@FindByParameterized`
1. We can add our custom impmenetation of `IParameterProvider` as one of the values for annotation
1. We can use standard Selenium locators along with parameterized

Now in your test you need to set pu property values before the page has been created. Let's look at some test example which uses TestNG to manage the test lyfe-cycle.

```java
@DataProvider(name = "languages")
Object[][] dataProvider(){
    return new Object[][]{
            {"en", "Press me", "Read me"},
            {"ru", "Нажми меня", "Прочти меня"}
    };
}

@Test(dataProvider = "languages")
public void testPage(String language, String btnLocator, String labelLocator){
    driver.get(URL + "/" + language);
    DefaultParameterProvider
            .properties
            .set(Map.of("btn.name", btnLocator, "lbl.text", labelLocator));
    MyPage myPage = new MyPage(driver);
    myPage.button.click();
    myPage.label.getText();
}
```

What we see here:

1. We use parameterization for testing localized application
1. We set the map of properties to parameter provider
1. We initialize the page using `WebDriver` however nothing stops you from building a page on top of `WebElement`
