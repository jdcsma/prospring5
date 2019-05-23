package jun.prospring5.ch6;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSourceTest {

    @Test
    public void dataSourceXml_01() {
        dataSourceXml("classpath:app-context-xml-01.xml");
    }

    @Test
    public void dataSourceXml_02() {
        dataSourceXml("classpath:app-context-xml-02.xml");
    }

    public void dataSourceXml(String fileXml) {
        GenericXmlApplicationContext appContext =
                new GenericXmlApplicationContext();
        appContext.load(fileXml);
        appContext.refresh();

        dataSourceTest(appContext);

        appContext.close();
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
