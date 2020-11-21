package cn.novisfff.javafx;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author novisfff
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FxPane {

    /**
     * the name of this scene
     */
    public String name();

    /**
     * the name of its parent scene
     */
    public String parentName() default "root";

    /**
     * the number of this scene
     */
    public int number() default 1;

}
