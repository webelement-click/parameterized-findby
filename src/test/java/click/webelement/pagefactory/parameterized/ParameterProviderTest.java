package click.webelement.pagefactory.parameterized;

import click.webelement.pagefactory.parameterized.model.ParameterizedThread;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

/**
 * @author Alexey Razgulyaev
 */
public class ParameterProviderTest extends MultiThreadedTest {

    @Test
    public void testSingleThreadParameterization(){
        DefaultParameterProvider.properties.set(Map.of(TEST_ME, OK_T_1));
        Assert.assertEquals(new DefaultParameterProvider().getParameter(TEST_ME), OK_T_1);
    }

    @Test
    public void testMultipleThreadsParameterization() throws Throwable {

        ParameterizedThread t1 = new ParameterizedThread(this) {
            @Override
            protected void doBeforePause() {
                DefaultParameterProvider.properties.set(Map.of(TEST_ME, OK_T_1));
            }
            @Override
            protected void doAfterPause() {
                Assert.assertEquals(new DefaultParameterProvider().getParameter(TEST_ME), OK_T_1);
            }
        };

        ParameterizedThread t2 = new ParameterizedThread(this) {
            @Override
            protected void doBeforePause() {
                DefaultParameterProvider.properties.set(Map.of(TEST_ME, OK_T_2));
            }
            @Override
            protected void doAfterPause() {
                Assert.assertEquals(new DefaultParameterProvider().getParameter(TEST_ME), OK_T_2);
            }
        };

        doTesting(t1, t2);
    }

}

