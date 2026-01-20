package io.github.cursodsousa.locadora;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {
    static Connection connection;

    @BeforeAll
    static void setUpDataBase() throws Exception {
        connection = DriverManager.getConnection("jdbc:h2:mem:testedb", "sa", "");

        connection.createStatement().execute("CREATE TABLE users (id INT, name VARCHAR) ");
    }

    @BeforeEach
    void insertuserTest() throws Exception {
        connection.createStatement().execute("insert into users(id, name) values (1, 'José')");
    }

    @Test
    void testUserExists() throws Exception{
        var resultSet = connection.createStatement().executeQuery("select * from users where id = 1");

        Assertions.assertTrue(resultSet.next());
    }

    @AfterAll
    static void closeDatabase() throws Exception{
        connection.close();
    }
}
