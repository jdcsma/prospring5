package jun.prospring5.ch4;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;

public class DestructiveBeanWithJSR250 {

    private File file;
    private String filePath;

    @PostConstruct
    private void init() throws Exception {
        System.out.println("Initializing Bean:");

        if (this.filePath == null) {
            throw new IllegalArgumentException("You must specify the filePath property of " +
                    DestructiveBeanWithJSR250.class);
        }

        this.file = new File(this.filePath);
        this.file.createNewFile();

        System.out.println("File exists: " + this.file.exists());
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Disposable Bean:");

        if (!this.file.delete()) {
            System.err.println("ERROR: failed to delete file.");
        }

        System.out.println("File exists: " + this.file.exists());
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
