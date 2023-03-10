package ex01;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassFinder {

    public static List<Class<?>> findClasses(String packageName) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            String file = resource.getFile();
            findClassesInDirectory(packageName, file, classes);
        }

        return classes;
    }

    private static void findClassesInDirectory(String packageName, String directory, List<Class<?>> classes)
            throws ClassNotFoundException {
        File dir = new File(directory);

        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                findClassesInDirectory(packageName + "." + file.getName(),
                        file.getAbsolutePath(), classes);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0,
                        file.getName().length() - 6);
                classes.add(Class.forName(className));
            }
        }
    }
}
