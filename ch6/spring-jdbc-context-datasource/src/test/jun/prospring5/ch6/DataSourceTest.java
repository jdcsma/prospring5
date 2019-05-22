package jun.prospring5.ch6;

import jun.prospring5.ch6.configuration.DataSourceConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSourceTest {

    private static Logger logger =
            LoggerFactory.getLogger(DataSourceTest.class);

    @Test
    public void dataSourceXml() {
        GenericXmlApplicationContext appContext =
                new GenericXmlApplicationContext();
        appContext.load("classpath:app-context-xml.xml");
        appContext.refresh();

        dataSourceTest(appContext);
    }

    @Test
    public void dataSourceAnnotation() {

        AnnotationConfigApplicationContext appContext =
                new AnnotationConfigApplicationContext(
                        DataSourceConfiguration.class);

        dataSourceTest(appContext);
    }

    private void dataSourceTest(ApplicationContext appContext) {

        DataSource dataSource = appContext.getBean(
                "dataSource", DataSource.class);

        Assert.assertNotNull(dataSource);

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT 1");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int mockVal = resultSet.getInt("1");
                Assert.assertTrue(mockVal == 1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
