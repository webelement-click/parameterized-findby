package click.webelement.pagefactory.parameterized;

import click.webelement.pagefactory.parameterized.model.ConcurrencyResolver;
import click.webelement.pagefactory.parameterized.model.ParameterizedThread;
import org.testng.annotations.BeforeMethod;

/**
 * @author Alexey Razgulyaev
 */
public class MultiThreadedTest implements ConcurrencyResolver {

    public static final String OK_T_1 = "OK_T1";
    public static final String OK_T_2 = "OK_T2";
    public static final String TEST_ME = "test.me";

    int threadsDone;
    Throwable interceptedException;

    @BeforeMethod
    public void setUp(){
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> interceptedException = throwable);
    }

    protected void doTesting(ParameterizedThread runnable1, ParameterizedThread runnable2) throws Throwable {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> interceptedException = throwable);
        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        if(interceptedException != null){
            throw interceptedException;
        }
    }

    @Override
    public synchronized void addThreadsDone(){
        threadsDone++;
        notifyAll();
    }

    @Override
    public synchronized boolean canResume(){
        while (threadsDone < 2){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
