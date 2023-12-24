package ru.netology.data;

import lombok.*;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLAssistant {
    private static QueryRunner runner = new QueryRunner();

    public SQLAssistant() {
    }

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(System.getProperty("dbUrl"), "app", "pass");
    }

    @SneakyThrows
    public static String getStatusCreditRequestEntity() {
        val conn = getConnection();
        val countStmt = conn.createStatement();
        val sql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        val resultSet = countStmt.executeQuery(sql);
        if (resultSet.next()) {
            return resultSet.getString("status");
        }
        return null;
    }

    @SneakyThrows
    public static String getStatusPaymentEntity() {
        val conn = getConnection();
        val countStmt = conn.createStatement();
        val sql = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        val resultSet = countStmt.executeQuery(sql);
        if (resultSet.next()) {
            return resultSet.getString("status");
        }
        return null;
    }

    @SneakyThrows
    public static int getCountOrderEntity() {
        val conn = getConnection();
        val countStmt = conn.createStatement();
        val sql = "SELECT COUNT(*) AS total FROM order_entity;";
        val resultSet = countStmt.executeQuery(sql);
        if (resultSet.next()) {
            return resultSet.getInt("total");
        }
        return 0;

    }

    @SneakyThrows
    public static void cleanDB() {
        var connection = getConnection();
        runner.update(connection, "DELETE FROM credit_request_entity");
        runner.update(connection, "DELETE FROM order_entity");
        runner.update(connection, "DELETE FROM payment_entity");
    }


}
