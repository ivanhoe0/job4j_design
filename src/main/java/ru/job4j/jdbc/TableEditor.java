package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("driver"));
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("login"),
                    properties.getProperty("password"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s(%s);", tableName, "id SERIAL PRIMARY KEY"
            );
            makeQuery(sql);
    }

    public void dropTable(String tableName) {
            String sql = String.format(
                    "DROP TABLE IF EXISTS %s;", tableName
            );
            makeQuery(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
            String sql = String.format(
                    "ALTER TABLE IF EXISTS %s ADD %s %s;", tableName, columnName, type
            );
        makeQuery(sql);
    }

    public void dropColumn(String tableName, String columnName) {
            String sql = String.format(
                    "ALTER TABLE IF EXISTS %s DROP COLUMN IF EXISTS %s;", tableName, columnName
            );
        makeQuery(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
            String sql = String.format(
                    "ALTER TABLE IF EXISTS %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName
            );
        makeQuery(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    private void makeQuery(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("sql.properties")) {
            config.load(in);
        }
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.createTable("example");
        System.out.println(tableEditor.getTableScheme("example"));
        tableEditor.addColumn("example", "name", "text");
        tableEditor.addColumn("example", "surname", "text");
        System.out.println(tableEditor.getTableScheme("example"));
        tableEditor.renameColumn("example", "surname", "second_name");
        System.out.println(tableEditor.getTableScheme("example"));
        tableEditor.dropColumn("example", "name");
        System.out.println(tableEditor.getTableScheme("example"));
        tableEditor.dropTable("example");
        System.out.println(tableEditor.getTableScheme("example"));
    }
}