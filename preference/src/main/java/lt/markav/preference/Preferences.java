package lt.markav.preference;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Preferences {

    /**
     * preferencesName has priority over value
     */
    @AliasFor("preferencesName")
    String value() default "";

    /**
     * preferencesName has priority over value
     */
    @AliasFor("value")
    String preferencesName() default "";

}
