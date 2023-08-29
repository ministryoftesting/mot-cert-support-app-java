package com.ministryoftesting.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class BaseDB {

    private static JdbcDataSource ds = new JdbcDataSource();

    private static Connection connection;

    static {
        ds.setURL("jdbc:h2:mem:timesheet;MODE=MySQL");
        ds.setUser("user");
        ds.setPassword("password");
        try {
            connection = ds.getConnection();
            executeSqlFile("db.sql");
            executeSqlFile("seed.sql");
            Server.createTcpServer("-tcpPort", "9090", "-tcpAllowOthers").start();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private static void executeSqlFile(String filename) throws IOException, SQLException {
        Reader reader = new InputStreamReader( new ClassPathResource(filename).getInputStream());
        Scanner sc = new Scanner(reader);

        StringBuilder sb = new StringBuilder();
        while(sc.hasNext()){
            sb.append(sc.nextLine());
        }

        connection.prepareStatement(sb.toString()).executeUpdate();
    }
}
