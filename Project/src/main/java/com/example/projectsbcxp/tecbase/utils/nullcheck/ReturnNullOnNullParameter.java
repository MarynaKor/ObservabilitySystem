package com.example.projectsbcxp.tecbase.utils.nullcheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * english umschreiben
 * Markiert eine Methode, deren Parameter 'null' sein dürfen und wrappt den Methodenrumpf um eine null Pruefung. Die Methode returned dann null,
 * sofern einer der Parameter null ist.
 * <p>
 * {@link ReturnNullOnNullParameterAspect}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReturnNullOnNullParameter {
}

