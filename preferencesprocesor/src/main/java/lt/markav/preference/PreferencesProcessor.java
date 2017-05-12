package lt.markav.preference;


import com.squareup.javapoet.JavaFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ElementKind;

public class PreferencesProcessor extends BaseProcessor {

    public PreferencesProcessor() {
        super(Preferences.class, SourceVersion.RELEASE_7);
    }

    @Override
    protected void process(RoundEnvironment roundEnv) throws Exception {
        List<PreferencesManagerConfig> annotations = getAnnotations(roundEnv);

        ManagerGenerator managerGenerator = new ManagerGenerator();

        annotations.stream().map(managerGenerator::generate)
                .map(this::printToOutputStream)
                .forEach(this::print);
    }

    private JavaFile printToOutputStream(JavaFile javaFile) {
        System.out.println("---------------------------------------------------------------------");
        try {
            javaFile.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return javaFile;
    }

    private void print(JavaFile javaFile) {
        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<PreferencesManagerConfig> getAnnotations(RoundEnvironment roundEnv) {
        return roundEnv
                .getElementsAnnotatedWith(Preferences.class)
                .stream()
                .map(PreferencesManagerConfig::new)
                .collect(Collectors.toList());
    }

}
