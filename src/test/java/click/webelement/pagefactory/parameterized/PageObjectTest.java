package click.webelement.pagefactory.parameterized;

import click.webelement.pagefactory.parameterized.model.ParameterizedThread;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

/**
 * @author Alexey Razgulyaev
 */
public class PageObjectTest extends MultiThreadedTest{

    PageObjectPositive p1;
    PageObjectPositive p2;

    @Test
    public void testFieldInitialization(){
        DefaultParameterProvider.properties.set(Map.of(TEST_ME, OK_T_1));
        PageObjectPositive pageObject = new PageObjectPositive();
        Assert.assertEquals(pageObject.fieldWec.getText(),
                "By.xpath: " +
                        PageObjectPositive.NAME_WEC_TEST_ME.replace("{wec:test.me}", OK_T_1));
        Assert.assertEquals(pageObject.fieldsWec.size(), 1);
        Assert.assertEquals(pageObject.fieldsWec.get(0).getText(),
                "By.xpath: " +
                        PageObjectPositive.NAME_WEC_TEST_ME.replace("{wec:test.me}", OK_T_1));
        Assert.assertNotNull(pageObject.fieldSelenium);
        Assert.assertNotNull(pageObject.fieldSelenium2);
    }

    @Test
    public void multiThreadedPageObjects() throws Throwable {
        ParameterizedThread t1 = new ParameterizedThread(this) {
            @Override
            protected void doBeforePause() {
                DefaultParameterProvider.properties.set(Map.of(TEST_ME, OK_T_1));
                p1 = new PageObjectPositive();
            }

            @Override
            protected void doAfterPause() {
                Assert.assertEquals(p1.fieldWec.getText(),
                        "By.xpath: " +
                                PageObjectPositive.NAME_WEC_TEST_ME.replace("{wec:test.me}", OK_T_1));
                Assert.assertEquals(p1.fieldsWec.size(), 1);
                Assert.assertEquals(p1.fieldsWec.get(0).getText(),
                        "By.xpath: " +
                                PageObjectPositive.NAME_WEC_TEST_ME.replace("{wec:test.me}", OK_T_1));

            }
        };
        ParameterizedThread t2 = new ParameterizedThread(this) {
            @Override
            protected void doBeforePause() {
                DefaultParameterProvider.properties.set(Map.of(TEST_ME, OK_T_2));
                p2 = new PageObjectPositive();
            }

            @Override
            protected void doAfterPause() {
                Assert.assertEquals(p2.fieldWec.getText(),
                        "By.xpath: " +
                                PageObjectPositive.NAME_WEC_TEST_ME.replace("{wec:test.me}", OK_T_2));
                Assert.assertEquals(p2.fieldsWec.size(), 1);
                Assert.assertEquals(p2.fieldsWec.get(0).getText(),
                        "By.xpath: " +
                                PageObjectPositive.NAME_WEC_TEST_ME.replace("{wec:test.me}", OK_T_2));

            }
        };
        doTesting(t1, t2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testPageInitializationNegative(){
        new PageObjectNegative();
    }

}

