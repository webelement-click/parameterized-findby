package click.webelement.pagefactory.parameterized;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.Annotations;
import java.lang.reflect.Field;

/**
 * @author Alexey Razgulyaev
 */
public class WecAnnotations extends Annotations {

    Field wecField;

    /**
     * @param field expected to be an element in a Page Object
     */
    public WecAnnotations(Field field) {
        super(field);
        wecField = field;
    }

    @Override
    protected void assertValidAnnotations() {
        super.assertValidAnnotations();
        FindBy findBy = wecField.getAnnotation(FindBy.class);
        FindBys findBys = wecField.getAnnotation(FindBys.class);
        FindAll findAll = wecField.getAnnotation(FindAll.class);
        if(wecField.getAnnotation(FindByParameterized.class) != null){
            if(findBy != null || findBys != null || findAll != null){
                throw new IllegalArgumentException("You cannot used standard Selenium annotations along with ParameterizedBy");
            }
        }
    }
}
