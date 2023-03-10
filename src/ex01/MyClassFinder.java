package ex01;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class MyClassFinder {
    private static List<String> findAnnotatedClasses(String packageName, Class<? extends Annotation> annotation)
            throws Exception {
        List<String> result = new ArrayList<>();

        // 패키지 내 클래스 리스트 가져오기
        List<Class<?>> classes = ClassFinder.findClasses(packageName);

        for (Class<?> clazz : classes) {
            // 어노테이션 체크
            if (clazz.isAnnotationPresent(annotation)) {
                // 클래스 이름 추가
                result.add(clazz.getName());
            }
        }

        return result;
    }

    public static List<Object> getInstanceList(String packageName, Class<? extends Annotation> annotation)
            throws Exception {
        List<String> classes = findAnnotatedClasses("ex01", Controller.class);
        List<Object> objectList = new ArrayList<>();

        for (String className : classes) {
            Class<?> class1 = Class.forName(className);
            System.out.println("테스트 : "+className);
            Object object = class1.getDeclaredConstructor().newInstance();
            objectList.add(object);
        }
        return objectList;
    }
}
