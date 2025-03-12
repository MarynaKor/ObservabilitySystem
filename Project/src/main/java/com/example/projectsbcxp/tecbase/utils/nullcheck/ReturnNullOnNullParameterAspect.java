package com.example.projectsbcxp.tecbase.utils.nullcheck;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

/**
 * Liefert den Aspekt für mit {@link ReturnNullOnNullParameter} markierte Methoden.
 */
@Component
@Aspect
@Slf4j
public class ReturnNullOnNullParameterAspect {

    /**
     * Wrappt mithilfe des Spring AOP den Methodenrumpf um eine null Pruefung für mit {@link ReturnNullOnNullParameter} markierte Methoden.
     *
     * @param joinPoint Der aktuelle Einstiegspunkt für die Methode, die mit dem Aspekt annotiert wurde.
     *
     * @return Die Methode returned null, sofern einer der Parameter null ist und führt sonst die annotierte Methode weiter aus.
     *
     * @throws Throwable ggf. Fehler, die in der annotierten Methode auftreten können.
     */
    @Around("@annotation(com.example.projectsbcxp.tecbase.utils.nullcheck.ReturnNullOnNullParameter)")
    public Object checkAnyParameterIsNull(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.trace("check parameter is null for {}: {}", joinPoint.getSignature(), joinPoint.getArgs());

        var params = joinPoint.getArgs();

        if (Arrays.stream(params)
                .anyMatch(Objects::isNull)) {
            return null;
        } else {
            return joinPoint.proceed();
        }
    }
}

