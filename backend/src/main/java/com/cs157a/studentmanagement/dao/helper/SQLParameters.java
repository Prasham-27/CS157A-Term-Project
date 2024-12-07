package com.cs157a.studentmanagement.dao.helper;

import java.sql.SQLException;

public interface SQLParameters<T> {
   void accept(T t) throws SQLException;
}
