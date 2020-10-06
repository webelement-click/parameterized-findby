package click.webelement.pagefactory.parameterized;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * @author Alexey Razgulyaev
 */
public class WecAnnotationsTest {

    @FindByParameterized
    Object fieldOkay;

    @FindBy
    Object fieldOkay2;

    @FindAll(@FindBy)
    Object fieldOkay3;

    @FindBys(@FindBy)
    Object fieldOkay4;

    @FindBy
    @FindAll(@FindBy)
    Object fieldNotOkay;

    @FindBy
    @FindBys(@FindBy)
    Object fieldNotOkay2;

    @FindAll(@FindBy)
    @FindBys(@FindBy)
    Object fieldNotOkay3;

    @FindByParameterized
    @FindBy
    Object fieldNotOkay4;

    @FindByParameterized
    @FindBys(@FindBy)
    Object fieldNotOkay5;

    @FindByParameterized
    @FindAll(@FindBy)
    Object fieldNotOkay6;

    @DataProvider(name = "okays")
    Iterator<Object[]> getOkays(){
        return Arrays
                .asList(this.getClass().getDeclaredFields())
                .stream()
                .filter(field -> field.getName().startsWith("fieldOkay"))
                .map(field -> new Object[]{field})
                .collect(Collectors.toList()).iterator();
    }

    @DataProvider(name = "not okays")
    Iterator<Object[]> getNotOkays(){
        return Arrays
                .asList(this.getClass().getDeclaredFields())
                .stream()
                .filter(field -> field.getName().startsWith("fieldNotOkay"))
                .map(field -> new Object[]{field})
                .collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "okays")
    public void testValidationOk(Field field){
        new WecAnnotations(field).assertValidAnnotations();
    }

    @Test(dataProvider = "not okays", expectedExceptions = {IllegalArgumentException.class})
    public void testValidationNok(Field field){
        new WecAnnotations(field).assertValidAnnotations();
    }

}


