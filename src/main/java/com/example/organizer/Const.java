package com.example.organizer;

public interface Const {
    String MESSAGE_ERROR_NOT_ALL_DATA = "Введите все данные!";
    String MESSAGE_ERROR_SIGN_IN = "Ошибка входа, проверьте Логин или Пороль!";
    String MESSAGE_ERROR_PASSWORD_NOT_SUPPRESS = "Пороли не совпадают!";
    String MESSAGE_ERROR_PASSWORD_NOT_CURE = "Пороль должен содержать не менее 2-х символов";
    String MESSAGE_ERROR_USERNAME_IS_EXIST = "Пользователь с таким логином уже существует!";
    String MESSAGE_ERROR_NOT_LESSON_NAME = "Введите название предмета!";
    String MESSAGE_ERROR_NOT_DATE = "Введите дату сдачи!";
    String MESSAGE_ERROR_NOT_DAY_OF_WEEK = "Введите день недели когда вам напоминать!";
    String[] CHOICE_BOX_NUMBER_OF_WEEK = {"Первая", "Вторая", "Каждую"};
    String DEFAULT_VALUE_CHOICE_BOX = "Выбрать";
    String[] CHOICE_BOX_SIX_DAYS_OF_WEEK = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
    String[] CHOICE_BOX_SEVEN_DAYS_OF_WEEK = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
    String[] TYPE_OF_LESSON = {"Лекция", "Лабораторная", "Практика", "Консультация"};
    String CHOICE_BOX_NO = "НЕТ";
    String CHOICE_BOX_YES = "ДА";
    String COLON = ":";
    String[] CHOICE_BOX_SETTING_SWITCH = {"Каждый день", "Каждую неделю"};
    String TITLE_SIGN_UP = "Регистрация";
    String TITLE_SIGN_IN = "Вход";
    String TITLE_TIMETABLE = "Расписание";
    String TITLE_EDIT_LESSON = "Пара";
    String TITLE_ADD_LESSON = "Новая пара";
    String TITLE_EDIT_REMINDER = "Задание";
    String TITLE_MAIN = "Студенческий органайзер";
    String EXIT = "Выход";
    String TINE_FORMAT = "hh:mm";
    int LENGTH_OF_PASSWORD = 3;
    int VBOX_SPACING = 5;
    long HOUR_IN_MILLISECONDS = 3600000;
    long MINUTE_IN_MILLISECONDS = 60000;

}