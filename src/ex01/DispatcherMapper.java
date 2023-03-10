package ex01;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

public class DispatcherMapper {

    public static void mapping(String path, List<Object> components) throws Exception {
        for (Object object : components) {
            Method[] methods = object.getClass().getDeclaredMethods();
            for (Method method : methods) {
                Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);
                if (annotation == null) {
                    continue;
                }
                RequestMapping rm = (RequestMapping) annotation;
                if (path.equals(rm.uri())) {
                    method.invoke(object);
                }
            }
        }
    }
}
