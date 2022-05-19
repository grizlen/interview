package main;

import db.Mapper;
import db.W4DbConnection;
import entities.Film;
import entities.Session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final String DB_URL = "jdbc:sqlite:.work4.db";

    private final Mapper<Film> filmMapper = (rs -> {
        Film result = new Film();
        try {
            result.setId(rs.getLong("id"));
            result.setName(rs.getString("name"));
            result.setLength(rs.getInt("length"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    });

    private final Mapper<Session> sessionMapper = (rs -> {
        Session result = new Session();
        try {
            result.setId(rs.getLong("id"));
            result.setStart(rs.getTimestamp("start"));
            result.setFilmId(rs.getLong("film_id"));
            result.setPriceId(rs.getLong("price_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    });

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        try (W4DbConnection connection = W4DbConnection.getConnection(DB_URL)){
            initDB(connection);

            printTable(
                    "Фильмы",
                    "Film",
                    connection.select("SELECT * FROM films;", filmMapper)
                            .stream().map(line -> line.toString())
                            .toList());

            printTable(
                    "Расписание",
                    "Sessions",
                    connection.select("SELECT id, datetime(start, 'unixepoch') as start, film_id, price_id FROM sessions;", sessionMapper)
                            .stream().map(line -> line.toString())
                            .toList());

            printTable(
                    "Ошибки в расписании",
                    "Фильм 1    Начало  Прод.   Фильм 2 Начало  Прод.   Интервал(м)",
                    connection.select(
                            loadText("/query1.sql"),
                            rs -> {
                                try {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(rs.getString(1)).append("\t")
                                            .append(rs.getString(2)).append("\t")
                                            .append(rs.getString(3)).append("\t")
                                            .append(rs.getString(4)).append("\t")
                                            .append(rs.getString(5)).append("\t")
                                            .append(rs.getString(6)).append("\t")
                                            .append(rs.getInt(7));
                                    return sb.toString();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }));

            printTable(
                    "Перерывы",
                    "Фильм 1    Начало  Прод.   След.  Перерыв(м)",
                    connection.select(
                            loadText("/query2.sql"),
                            rs -> {
                                try {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(rs.getString(1)).append("\t")
                                            .append(rs.getString(2)).append("\t")
                                            .append(rs.getString(3)).append("\t")
                                            .append(rs.getString(4)).append("\t")
                                            .append(rs.getInt(5));
                                    return sb.toString();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }));

            printTable(
                    "Продажи",
                    "Фильм    Всего билетов  Средн. за сеанс   След.  Перерыв(м)    сумма",
                    connection.select(
                            loadText("/query3.sql"),
                            rs -> {
                                try {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(rs.getString(1)).append("\t")
                                            .append(rs.getString(2)).append("\t")
                                            .append(rs.getString(3)).append("\t")
                                            .append(rs.getString(4));
                                    return sb.toString();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void initDB(W4DbConnection connection) throws Exception {
        String sql = loadText("/schema.sql");
        connection.executeMany(sql);
        sql = loadText("/data.sql");
        connection.executeMany(sql);
    }

    private String loadText(String name) throws Exception {
        InputStream resource = getClass().getResourceAsStream(name);
        if (resource == null) {
            throw new Exception("Resource " + name + " not found.");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
        return reader.lines().filter(line -> !line.isBlank()).collect(Collectors.joining("\n"));
    }

    private void printTable(String title, String header, List<String> lines) {
        System.out.println(title);
        System.out.println(header);
        lines.forEach(System.out::println);
    }
}
