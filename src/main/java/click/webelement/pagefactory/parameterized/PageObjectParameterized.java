package click.webelement.pagefactory.parameterized;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;

/**
 * @author Alexey Razgulyaev
 */
public class PageObjectParameterized {

    public PageObjectParameterized(SearchContext searchContext){
        PageFactory
                .initElements(
                        new ParameterizedFieldDecorator(
                                field -> new DefaultElementLocator(searchContext, new WecAnnotations(field)
                                )
                        ), this);
    }

}
