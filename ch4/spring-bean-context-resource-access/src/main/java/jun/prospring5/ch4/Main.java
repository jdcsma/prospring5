package jun.prospring5.ch4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {

        ApplicationContext appContext =
                new ClassPathXmlApplicationContext();

        String fileName = "test.txt";
        String path = ClassLoader.
                getSystemResource("").
                getPath() + fileName;
        String location;

        File file = new File(path);
        try {

            file.createNewFile();

            location = "file://" + file.getAbsolutePath();
            Resource res1 = appContext.getResource(location);
            displayInfo(res1);

            location = "classpath:" + fileName;
            Resource res2 = appContext.getResource(location);
            displayInfo(res2);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            file.delete();
        }

        location = "http://www.baidu.com";
        Resource res3 = appContext.getResource(location);
        displayInfo(res3);
    }

    private static void displayInfo(Resource resource) {
        System.out.println("Class: " + resource.getClass());
        System.out.println("    Description: " + resource.getDescription());

        try (InputStream is = getInputStream(resource)) {
            System.out.println("    InputStream: " + is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static InputStream getInputStream(
            Resource resource) throws Exception {

        if (resource.isFile()) {
            return new FileInputStream(resource.getFile());
        } else {
            return resource.getInputStream();
        }
    }
}
