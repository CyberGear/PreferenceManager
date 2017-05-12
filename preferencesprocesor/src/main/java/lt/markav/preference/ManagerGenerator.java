package lt.markav.preference;


import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;

import lt.markav.preference.util.Logging;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;

public class ManagerGenerator implements Logging {

    public JavaFile generate(PreferencesManagerConfig managerConfig) {
        TypeSpec.Builder managerClass = TypeSpec.classBuilder(managerConfig.getClassName())
                .superclass(TypeName.get(managerConfig.getEnclosedElement().asType()));

        createGettersAndSetters(managerConfig).forEach(managerClass::addMethod);

        return JavaFile.builder(managerConfig.getPackageName(), managerClass.build()).build();
    }

    private List<MethodSpec> createGettersAndSetters(PreferencesManagerConfig managerConfig) {
        List<MethodSpec> methodSpecs = new ArrayList<>();
        managerConfig.getFields().forEach(field -> {
            String methodNameSurfix = createMethodName(field);
            methodSpecs.add(createSetter(methodNameSurfix, field));
            methodSpecs.add(createGetter(methodNameSurfix, field));
        });
        return methodSpecs;
    }

    private String createMethodName(Element field) {
        String name = field.getSimpleName().toString();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private MethodSpec createSetter(String methodNameSurfix, Element field) {
        return MethodSpec.methodBuilder("set" + methodNameSurfix)
                .addModifiers(PUBLIC)
                .returns(void.class)
                .addParameter(
                        TypeName.get(field.asType()),
                        field.getSimpleName().toString(),
                        FINAL
                ).addStatement("this.$L = $L", field, field)
                .build();
    }

    private MethodSpec createGetter(String methodNameSurfix, Element field) {
        return MethodSpec.methodBuilder("get" + methodNameSurfix)
                .addModifiers(PUBLIC)
                .returns(TypeName.get(field.asType()))
                .addStatement("return " + field)
                .build();
    }

}
