package lt.markav.preference.util;

import com.squareup.javapoet.ClassName;

public class ClassNameUtil {

    public static ClassName classNameForType(String type) {
        if (type.contains(".")) {
            String[] parts = type.split("\\.");
            String simpleName = parts[parts.length - 1];
            return ClassName.get(type.replace("." + simpleName, ""), simpleName);
        }
        ClassName className = Android.getClassName(type);



        return className;
    }

    public static String classNameToArgName(ClassName className) {
        String name = className.simpleName();
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    public static String classNameToArgName(String argIdentifier) {
        if(argIdentifier.contains("@")) {
            return "not_valid";
        }
        return argIdentifier.substring(0, 1).toLowerCase() + argIdentifier.substring(1);
    }

}
