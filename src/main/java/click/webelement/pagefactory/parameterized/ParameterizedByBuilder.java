package click.webelement.pagefactory.parameterized;

import org.openqa.selenium.By;
import org.openqa.selenium.support.AbstractFindByBuilder;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alexey Razgulyaev
 */
public class ParameterizedByBuilder extends AbstractFindByBuilder {

    IParameterProvider parameterProvider;

    protected By buildParameterizedBy(FindByParameterized findBy) {
        if (!"".equals(findBy.className())) {
            return By.className(processParameter(findBy.className()));
        }

        if (!"".equals(findBy.css())) {
            return By.cssSelector(processParameter(findBy.css()));
        }

        if (!"".equals(findBy.id())) {
            return By.id(processParameter(findBy.id()));
        }

        if (!"".equals(findBy.linkText())) {
            return By.linkText(processParameter(findBy.linkText()));
        }

        if (!"".equals(findBy.name())) {
            return By.name(processParameter(findBy.name()));
        }

        if (!"".equals(findBy.partialLinkText())) {
            return By.partialLinkText(processParameter(findBy.partialLinkText()));
        }

        if (!"".equals(findBy.tagName())) {
            return By.tagName(processParameter(findBy.tagName()));
        }

        if (!"".equals(findBy.xpath())) {
            return By.xpath(processParameter(findBy.xpath()));
        }

        return null;
    }

    @Override
    public By buildIt(Object annotation, Field field) {
        FindByParameterized parameterizedBy = (FindByParameterized)annotation;
        if(parameterProvider == null){
            try {
                parameterProvider = parameterizedBy.parameterProvider().getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return buildParameterizedBy(parameterizedBy);
    }

    private String processParameter(String input) {
        Pattern p = Pattern.compile("\\{wec:(.+?)}");
        Matcher m = p.matcher(input);
        String result = input;
        while (m.find()) {
            String fullMatch = m.group();
            String propName = m.group(1);
            String propValue = parameterProvider.getParameter(propName);
            if (propValue == null) {
                throw new IllegalArgumentException("Cannot find property: " + propName);
            }
            result = result.replace(fullMatch, propValue);
        }
        return result;
    }

}
