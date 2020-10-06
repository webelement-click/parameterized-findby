package click.webelement.pagefactory.parameterized;

import click.webelement.pagefactory.parameterized.model.PageObjectBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

/**
 * @author Alexey Razgulyaev
 */
public class PageObjectPositive extends PageObjectBase {

    public static final String NAME_WEC_TEST_ME = "//*[@name='{wec:test.me}']";

    @FindByParameterized(xpath = NAME_WEC_TEST_ME)
    WebElement fieldWec;

    @FindByParameterized(xpath = NAME_WEC_TEST_ME)
    List<WebElement> fieldsWec;

    @FindBy(xpath = "")
    WebElement fieldSelenium;

    WebElement fieldSelenium2;

}
