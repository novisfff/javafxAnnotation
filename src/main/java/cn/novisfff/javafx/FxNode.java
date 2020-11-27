package cn.novisfff.javafx;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author novisfff
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FxNode {

    /**
     * the parameter of this node
     */
    public String[] parameter() default {};

    /**
     * the index of this node
     */
    public int index();

    /**
     * the number of this node
     */
    public int number() default 1;

}
