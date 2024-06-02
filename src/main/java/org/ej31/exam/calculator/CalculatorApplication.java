package org.ej31.exam.books;

import org.ej31.CalculatorConfig;
import org.ej31.Calculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CalculatorApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CalculatorConfig.class);
        Calculator calculator = (Calculator) context.getBean("calculatorService");

        System.out.println("Addition: " + calculator.add(10, 5));
        System.out.println("Subtraction: " + calculator.subtract(10, 5));
        System.out.println("Multiplication: " + calculator.multiply(10, 5));
        System.out.println("Division: " + calculator.divide(10, 5));
    }
}