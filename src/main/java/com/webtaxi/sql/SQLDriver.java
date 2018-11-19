package com.webtaxi.sql;

/**
 * @author Vitalii Usatyi
 */
class SQLDriver {
    static void addDriverSQLToClassPath() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
