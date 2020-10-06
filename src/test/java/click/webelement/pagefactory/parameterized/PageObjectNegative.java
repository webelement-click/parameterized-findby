package click.webelement.pagefactory.parameterized;

import click.webelement.pagefactory.parameterized.model.PageObjectBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Alexey Razgulyaev
 */
public class PageObjectNegative extends PageObjectBase {

    @FindByParameterized(xpath = "")
    @FindBy
    WebElement field;

}
