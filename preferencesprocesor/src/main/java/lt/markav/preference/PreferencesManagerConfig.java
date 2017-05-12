package lt.markav.preference;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;

import lt.markav.preference.util.Logging;

public class PreferencesManagerConfig implements Logging {

    private final String packageName;
    private final String className;
    private final String preferencesName;
    private final TypeElement enclosedElement;
    private final List<VariableElement> fields;

    public PreferencesManagerConfig(Element element) {
        enclosedElement = (TypeElement) element;
        className = enclosedElement.getSimpleName().toString() + "Manager";
        packageName = enclosedElement.getEnclosingElement().toString();
        preferencesName = choosePreferencesName(enclosedElement.getAnnotation(Preferences.class));
        fields = ElementFilter.fieldsIn(enclosedElement.getEnclosedElements());
    }

    private String choosePreferencesName(Preferences annotation) {
        String name = annotation.preferencesName();
        if (name.isEmpty()) {
            name = annotation.value();
        }
        if (name.isEmpty()) {
            name = enclosedElement.getSimpleName().toString();
        }
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public String getPreferencesName() {
        return preferencesName;
    }

    public TypeElement getEnclosedElement() {
        return enclosedElement;
    }

    public List<VariableElement> getFields() {
        return fields;
    }
}
