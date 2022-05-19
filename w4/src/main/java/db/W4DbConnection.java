package db;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class W4DbConnection implements Closeable {
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private final Connection connection;

    public W4DbConnection(String url) throws SQLException {
        connection = DriverManager.getConnection(url);
    }

    public static W4DbConnection getConnection(String url) throws SQLException {
        return new W4DbConnection(url);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Close");
    }

    public void execute(String sql) {
        System.out.println("execute:");
        System.out.println(sql);
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> select(String sql, Mapper<T> mapper) {
        System.out.println("execute:");
        System.out.println(sql);
        ArrayList<T> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                list.add(mapper.map(rs));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void executeMany(String sql) {
        String[] split = sql.split(";\n");
        for (String s: split) {
            execute(s.endsWith(";") ? s : s + ";");
        }
    }
}
