package com.dao;

import javax.naming.*;
import javax.sql.DataSource;

public class BaseDao {
    DataSource dataSource;

    public BaseDao() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/chat");
        } catch (NamingException ne) {
            System.out.println("Exception:" + ne);
        }
    }
}
