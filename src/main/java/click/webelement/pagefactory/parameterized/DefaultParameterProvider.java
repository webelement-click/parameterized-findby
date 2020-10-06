package click.webelement.pagefactory.parameterized;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexey Razgulyaev
 */
public class DefaultParameterProvider implements IParameterProvider{

    public static ThreadLocal<Map<String, String>> properties = ThreadLocal.withInitial(() -> new HashMap<>());

    @Override
    public String getParameter(String parameterName) {
        return properties.get().get(parameterName);
    }
}
