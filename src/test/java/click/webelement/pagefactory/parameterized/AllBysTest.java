package click.webelement.pagefactory.parameterized;

import click.webelement.pagefactory.parameterized.model.PageObjectBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

/**
 * @author Alexey Razgulyaev
 */
public class AllBysTest extends PageObjectBase {

    public static final String TEST_ME_LOCATOR = "abc{wec:" + MultiThreadedTest.TEST_ME + "}xyz";
    public static final String TEST_ME_PROCESSED_LOCATOR = "abcSUCCESSxyz";
    public static final String TEST_ME_VALUE = "SUCCESS";

    @Test
    public void testAll(){
        DefaultParameterProvider.properties.set(Map.of(MultiThreadedTest.TEST_ME, TEST_ME_VALUE));
        Page page = new Page();
        Assert.assertEquals(page.byClassName.getText(), "By.className: " + TEST_ME_PROCESSED_LOCATOR);
        Assert.assertEquals(page.byCSS.getText(), "By.cssSelector: " + TEST_ME_PROCESSED_LOCATOR);
        Assert.assertEquals(page.byId.getText(), "By.id: " + TEST_ME_PROCESSED_LOCATOR);
        Assert.assertEquals(page.byLinkText.getText(), "By.linkText: " + TEST_ME_PROCESSED_LOCATOR);
        Assert.assertEquals(page.byPartialLinkText.getText(), "By.partialLinkText: " + TEST_ME_PROCESSED_LOCATOR);
        Assert.assertEquals(page.byName.getText(), "By.name: " + TEST_ME_PROCESSED_LOCATOR);
        Assert.assertEquals(page.byTagName.getText(), "By.tagName: " + TEST_ME_PROCESSED_LOCATOR);
        Assert.assertEquals(page.byXPath.getText(), "By.xpath: " + TEST_ME_PROCESSED_LOCATOR);
    }
}

/**
 * @author Alexey Razgulyaev
 */
class Page extends PageObjectBase{
    @FindByParameterized(css = AllBysTest.TEST_ME_LOCATOR)
    WebElement byCSS;

    @FindByParameterized(xpath = AllBysTest.TEST_ME_LOCATOR)
    WebElement byXPath;

    @FindByParameterized(name = AllBysTest.TEST_ME_LOCATOR)
    WebElement byName;

    @FindByParameterized(id = AllBysTest.TEST_ME_LOCATOR)
    WebElement byId;

    @FindByParameterized(className = AllBysTest.TEST_ME_LOCATOR)
    WebElement byClassName;

    @FindByParameterized(linkText = AllBysTest.TEST_ME_LOCATOR)
    WebElement byLinkText;

    @FindByParameterized(partialLinkText = AllBysTest.TEST_ME_LOCATOR)
    WebElement byPartialLinkText;

    @FindByParameterized(tagName = AllBysTest.TEST_ME_LOCATOR)
    WebElement byTagName;
}