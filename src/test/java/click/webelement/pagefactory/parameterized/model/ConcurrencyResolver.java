package click.webelement.pagefactory.parameterized.model;

/**
 * @author Alexey Razgulyaev
 */
public interface ConcurrencyResolver {
    void addThreadsDone();

    boolean canResume();
}
