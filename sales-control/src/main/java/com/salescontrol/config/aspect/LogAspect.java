package com.salescontrol.config.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private static final DateTimeFormatter BR_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerMethods() {
    }

    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String dateHour = LocalDateTime.now().format(BR_FORMAT);
        String methodHttp = request.getMethod();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        log.info("[INIT] {} {} {} {} Args: {}",
                dateHour, methodHttp, methodName, className, Arrays.toString(args));

        long countInit = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - countInit;

            log.info("[END] {} {} {} Status: {} Time: {} ms",
                    methodHttp, className, methodName, response.getStatus(), duration);

            return result;
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - countInit;

            log.error("[ERROR] {} {} {} Time: {} ms - Message: {}",
                    methodHttp, className, methodHttp, duration, e.getMessage(), e);

            throw e; // mantém a exceção para o fluxo normal de erro
        }


    }

}
