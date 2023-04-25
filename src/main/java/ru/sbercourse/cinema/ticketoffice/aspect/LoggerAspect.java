package ru.sbercourse.cinema.ticketoffice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggerAspect {

    @Pointcut("bean(*Controller)")
    public void controllers() {
    }

    @Around("controllers()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        String httpMethod = null;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Annotation[] annotations = method.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMappingAnnotation = annotation.annotationType().getAnnotation(RequestMapping.class);
                httpMethod = requestMappingAnnotation.method()[0].name();
            }
        }

        log.info("Вызов: {}.{}() {}с аргументами: {}",
                methodSignature.getDeclaringType().getSimpleName(),
                methodSignature.getName(),
                httpMethod !=null ? httpMethod + "-запрос " : "",
                Arrays.toString(joinPoint.getArgs())
        );
        Object result;
        try {
            result = joinPoint.proceed();
            log.info("Выход: {}.{}() {}с результатом = {}",
                    methodSignature.getDeclaringType().getSimpleName(),
                    methodSignature.getName(),
                    httpMethod !=null ? httpMethod + "-запрос " : "",
                    result
            );
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage());
            result = joinPoint.proceed();
        }
        return result;
    }
}
