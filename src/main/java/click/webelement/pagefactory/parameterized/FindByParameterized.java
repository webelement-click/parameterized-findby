package click.webelement.pagefactory.parameterized;

import org.openqa.selenium.support.PageFactoryFinder;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@PageFactoryFinder(ParameterizedByBuilder.class)

/**
 * @author Alexey Razgulyaev
 */
public @interface FindByParameterized {

    String xpath() default "";

    String id() default "";

    String name() default "";

    String className() default "";

    String css() default "";

    String tagName() default "";

    String linkText() default "";

    String partialLinkText() default "";

    Class<? extends IParameterProvider> parameterProvider() default DefaultParameterProvider.class;
}
