package fr.ecole42.swingy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnectionTest {
    private String url;
    private String user;
    private String password;

    @Test
    @Before
    public void envFileTest() {
        Properties properties = new Properties();
        java.net.URL url = ClassLoader.getSystemResource("postgres.env");
        try {
            properties.load(url.openStream());
        } catch (Exception ignored) {}

        this.user = properties.getProperty("POSTGRES_USER");
        this.password = properties.getProperty("POSTGRES_PASSWORD");
        this.url = properties.getProperty("CONNECTION_URL");
        assertNotEquals("Can't find POSTGRES_USER in postgres.env", null, this.user);
        assertNotEquals("Can't find POSTGRES_PASSWORD in postgres.env",null, this.password);
        assertNotEquals("Can't find CONNECTION_URL in postgres.env",null, this.url);
    }
    @Test
    public void simpleConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (Exception ignored) {}

        assertNotEquals("Can't connect to db", null, con);
        try {
            con.close();
        } catch (Exception ignored) {}
    }
}
