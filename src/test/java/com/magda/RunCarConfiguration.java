package com.magda;

import com.magda.config.CarConfiguration;
import com.magda.core.Car;
import com.magda.core.ColorOptions;
import com.magda.core.Test;
import com.magda.core.TesterInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunCarConfiguration {
    public static void main(String[] args) throws Exception {

        System.out.println("Testing grouped car ...");
        Class<CarConfiguration> obj = CarConfiguration.class;

        // Process @ColorOption
        if (obj.isAnnotationPresent(ColorOptions.class)) {
            Annotation annotation = obj.getAnnotation(ColorOptions.class);
            ColorOptions colorOptions = (ColorOptions) annotation;

            System.out.printf("%nColor :%s", colorOptions.color());
            System.out.printf("%nLastModified :%s%n%n",
                    colorOptions.lastModified());

        }
        Map <ColorOptions.Color, Car> groupedCar = new HashMap<ColorOptions.Color, Car>();
        // Process @Test
        for (Method method : obj.getDeclaredMethods()) {

            // if method is annotated with @Test
            if (method.isAnnotationPresent(ColorOptions.class)) {
                Annotation annotation = obj.getAnnotation(ColorOptions.class);
                ColorOptions colorOptions = (ColorOptions) annotation;

                try {
                    method.invoke(obj.newInstance());
                    if (method.isAnnotationPresent(ColorOptions.class)) {
                        System.out.printf("Test '%s' - has method annnotation %s %n" ,
                                method.getReturnType().getName(),
                                method.getAnnotation(ColorOptions.class).color());
                        groupedCar.put( method.getAnnotation(ColorOptions.class).color(),
                                (Car)  method.invoke(obj.newInstance()));
                    }


                } catch (Throwable ex) {
                    System.out.printf("Test '%s' - failed: %s %n" ,
                            method.getName(), ex.getCause());
                    ex.printStackTrace();
                }
            }
        }

        System.out.println("Grouped Car");
        for (ColorOptions.Color key: groupedCar.keySet()) {
            System.out.println(key + "/" + groupedCar.get(key));
        }
    }
}
