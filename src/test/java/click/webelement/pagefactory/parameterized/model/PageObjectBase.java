package click.webelement.pagefactory.parameterized.model;

import click.webelement.pagefactory.parameterized.ParameterizedFieldDecorator;
import click.webelement.pagefactory.parameterized.WecAnnotations;
import org.easymock.EasyMock;
import org.easymock.MockType;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alexey Razgulyaev
 */
public class PageObjectBase {

    public PageObjectBase() {

        PageFactory.initElements(new ParameterizedFieldDecorator(field -> new DefaultElementLocator(new SearchContext() {
            @Override
            public List<WebElement> findElements(By by) {
                WebElement mocked = EasyMock.createMock(MockType.DEFAULT, WebElement.class);
                EasyMock.expect(mocked.getText()).andReturn(by.toString());
                EasyMock.replay(mocked);
                return Arrays.asList(mocked);
            }

            @Override
            public WebElement findElement(By by) {
                return findElements(by).get(0);
            }
        }, new WecAnnotations(field))), this);
    }

}
