package com.example.organizer.Repositories;

import com.example.organizer.model.Reminder;
import com.example.organizer.model.User;

import java.sql.*;
import java.util.ArrayList;

public class ReminderRepo extends db_Settings {
    private static final String NAME_TABLE_REMINDER = "_reminders";

    public static ArrayList<Reminder> getRemindersEnable(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Reminder> lessonsList = new ArrayList<>();
        try {
            String nameTable = user.getId() + NAME_TABLE_REMINDER;
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM " + nameTable + " WHERE switch = 1");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lessonsList.add(new Reminder(
                        resultSet.getInt("id"),
                        resultSet.getString("lesson"),
                        resultSet.getString("quest"),
                        resultSet.getString("date"),
                        resultSet.getBoolean("switch"),
                        resultSet.getString("settingSwitch"),
                        resultSet.getString("time"),
                        resultSet.getString("dayOfWeek")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return lessonsList;
    }

    public static void deleteReminderById(User user, Reminder reminder) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            String nameTable = user.getId() + NAME_TABLE_REMINDER;
            preparedStatement = connection.prepareStatement("DELETE FROM " + nameTable + " WHERE id = ?");
            preparedStatement.setInt(1, reminder.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }

    public static void addReminderByIdUser(Reminder reminder, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            String nameTable = id + NAME_TABLE_REMINDER;
            preparedStatement = connection.prepareStatement("INSERT INTO " + nameTable + " (lesson,date,quest,switch,settingSwitch,time,dayOfWeek) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, reminder.getLessonName());
            preparedStatement.setString(2, reminder.getDate());
            preparedStatement.setString(3, reminder.getQuest());
            preparedStatement.setBoolean(4, reminder.isSwitchR());
            preparedStatement.setString(5, reminder.getSettingSwitch());
            preparedStatement.setString(6, reminder.getTime());
            preparedStatement.setString(7, reminder.getDatOfWeek());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }

    public static void updateRminderById(Reminder reminder, int idReminder, int idUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            String nameTable = idUser + NAME_TABLE_REMINDER;
            preparedStatement = connection.prepareStatement("UPDATE " + nameTable + " SET lesson = ?,date = ?,quest = ?,switch = ?,settingSwitch = ?,time = ?,dayOfWeek = ? WHERE id = ?");
            preparedStatement.setString(1, reminder.getLessonName());
            preparedStatement.setString(2, reminder.getDate());
            preparedStatement.setString(3, reminder.getQuest());
            preparedStatement.setBoolean(4, reminder.isSwitchR());
            preparedStatement.setString(5, reminder.getSettingSwitch());
            preparedStatement.setString(6, reminder.getTime());
            preparedStatement.setString(7, reminder.getDatOfWeek());
            preparedStatement.setInt(8, idReminder);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }

    public static ArrayList<Reminder> getAllRemindersByUser(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Reminder> lessonsList = new ArrayList<>();
        try {
            String nameTable = id + NAME_TABLE_REMINDER;
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM " + nameTable + "");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lessonsList.add(new Reminder(
                        resultSet.getInt("id"),
                        resultSet.getString("lesson"),
                        resultSet.getString("quest"),
                        resultSet.getString("date"),
                        resultSet.getBoolean("switch"),
                        resultSet.getString("settingSwitch"),
                        resultSet.getString("time"),
                        resultSet.getString("dayOfWeek")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return lessonsList;
    }

    public static boolean reminderTableIsExistsByUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("SHOW TABLES LIKE '%" + user.getId() + "_reminders%'");
            resultSet = preparedStatement.executeQuery();
            result = resultSet.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return !result;
    }

    public static void createReminderTableByUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            preparedStatement = connection.prepareStatement("CREATE TABLE `organizer_db`.`" + user.getId() + "_reminders` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `lesson` VARCHAR(45) NULL,\n" +
                    "  `date` VARCHAR(10) NULL,\n" +
                    "  `quest` VARCHAR(200) NULL,\n" +
                    "  `switch` TINYINT NULL,\n" +
                    "  `settingSwitch` VARCHAR(45) NULL,\n" +
                    "  `time` VARCHAR(5) NULL,\n" +
                    "  `dayOfWeek` VARCHAR(10) NULL,\n" +
                    "  PRIMARY KEY (`id`));");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(null, preparedStatement, connection);
        }
    }
}
