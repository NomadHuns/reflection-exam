package ex01;

import java.util.List;
import java.util.Scanner;

public class MyRefApp {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();

        // 1. 컴포넌트 스캔
        List<Object> components = MyClassFinder.getInstanceList("ex01", Controller.class);

        // 2. Dispatcher 매핑
        DispatcherMapper.mapping(path, components);
        scanner.close();
    }
}
