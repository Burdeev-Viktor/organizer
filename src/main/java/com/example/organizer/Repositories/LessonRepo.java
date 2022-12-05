package com.example.organizer.Repositories;

import com.example.organizer.model.Lesson;
import com.example.organizer.model.User;

import java.sql.*;
import java.util.ArrayList;

public class LessonRepo extends db_Settings {
    private static final String NAME_TABLE_TIMETABLE = "_timetable";

    public static void deleteLessonById(User user, Lesson lesson) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            String nameTable = user.getId() + NAME_TABLE_TIMETABLE;
            preparedStatement = connection.prepareStatement("DELETE FROM " + nameTable + " WHERE id = ?");
            preparedStatement.setInt(1, lesson.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }

    public static boolean timetableIsExistsByUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);

            preparedStatement = connection.prepareStatement("SHOW TABLES LIKE '%" + user.getId() + "_timetable%'");
            resultSet = preparedStatement.executeQuery();
            result = resultSet.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return !result;
    }

    public static void createTimetableByUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE `organizer_db`.`" + user.getId() + "_timetable` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `teacher` VARCHAR(45) NULL,\n" +
                    "  `room` VARCHAR(45) NULL,\n" +
                    "  `day_of_week` INT NULL,\n" +
                    "  `number_of_week` INT NULL,\n" +
                    "  `type` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`id`));");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }

    public static void addLessonByIdUser(Lesson lesson, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            String nameTable = id + NAME_TABLE_TIMETABLE;
            preparedStatement = connection.prepareStatement("INSERT INTO " + nameTable + " (name,teacher,room,day_of_week,number_of_week,type) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setString(2, lesson.getTeacher());
            preparedStatement.setString(3, lesson.getRoom());
            preparedStatement.setInt(4, lesson.getDayOfWeek());
            preparedStatement.setInt(5, lesson.getNumberOfWeek());
            preparedStatement.setString(6, lesson.getType());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }

    public static ArrayList<Lesson> getAllLessonsByUser(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Lesson> lessonsList = new ArrayList<>();
        try {
            String nameTable = id + NAME_TABLE_TIMETABLE;
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM " + nameTable + "");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lessonsList.add(new Lesson(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("teacher"),
                        resultSet.getString("room"),
                        resultSet.getString("type"),
                        resultSet.getInt("day_of_week"),
                        resultSet.getInt("number_of_week")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return lessonsList;
    }

    public static ArrayList<Lesson> getLessonsByUserByWeek(int id, int week) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Lesson> lessonsList = new ArrayList<>();
        try {
            String nameTable = id + NAME_TABLE_TIMETABLE;
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM " + nameTable + " WHERE number_of_week = ? OR number_of_week = 2");
            preparedStatement.setInt(1, week);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lessonsList.add(new Lesson(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("teacher"),
                        resultSet.getString("room"),
                        resultSet.getString("type"),
                        resultSet.getInt("day_of_week"),
                        resultSet.getInt("number_of_week")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return lessonsList;
    }

    public static void updateLessonById(Lesson lesson, int idLesson, int idUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            String nameTable = idUser + NAME_TABLE_TIMETABLE;
            preparedStatement = connection.prepareStatement("UPDATE " + nameTable + " SET name = ?,teacher = ?,room = ?,day_of_week = ?,number_of_week = ?,type = ? WHERE id = ?");
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setString(2, lesson.getTeacher());
            preparedStatement.setString(3, lesson.getRoom());
            preparedStatement.setInt(4, lesson.getDayOfWeek());
            preparedStatement.setInt(5, lesson.getNumberOfWeek());
            preparedStatement.setString(6, lesson.getType());
            preparedStatement.setInt(7, idLesson);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }
}

