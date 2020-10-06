package click.webelement.pagefactory.parameterized.model;

/**
 * @author Alexey Razgulyaev
 */
public abstract class ParameterizedThread implements Runnable {

    ConcurrencyResolver concurrencyResolver;

    public ParameterizedThread(ConcurrencyResolver flowControl) {
        this.concurrencyResolver = flowControl;
    }

    abstract protected void doBeforePause();

    abstract protected void doAfterPause();

    @Override
    public void run() {
        doBeforePause();
        concurrencyResolver.addThreadsDone();
        if (concurrencyResolver.canResume()) {
            doAfterPause();
        }
    }

}
