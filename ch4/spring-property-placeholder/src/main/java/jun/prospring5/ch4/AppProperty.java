package jun.prospring5.ch4;

public class AppProperty {

    private String applicationName;
    private String userHome;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getUserHome() {
        return userHome;
    }

    public void setUserHome(String userHome) {
        this.userHome = userHome;
    }

    @Override
    public String toString() {
        return "AppProperty{" +
                "applicationName='" + applicationName + '\'' +
                ", userHome='" + userHome + '\'' +
                '}';
    }
}
