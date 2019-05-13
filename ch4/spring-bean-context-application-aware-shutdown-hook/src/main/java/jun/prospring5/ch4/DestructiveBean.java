package jun.prospring5.ch4;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;

public class DestructiveBean {

    private File file;
    private String filePath;

    @PostConstruct
    public void init() throws Exception {
        System.out.println("Initializing Bean:");

        if (this.filePath == null) {
            throw new IllegalArgumentException(
                    "You must specify the filePath property of " +
                    DestructiveBean.class);
        }

        this.file = new File(this.filePath);
        this.file.createNewFile();

        System.out.println("File exists: " + this.file.exists());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroying Bean:");

        if (!this.file.delete()) {
            System.err.println("ERROR: failed to delete file.");
        }

        System.out.println("File exists: " + this.file.exists());
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
