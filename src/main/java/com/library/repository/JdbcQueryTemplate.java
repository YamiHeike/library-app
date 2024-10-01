package com.library.repository;

import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public abstract class JdbcQueryTemplate<T> extends AbstractDao {
    public List<T> queryForList(String sql) {
        List<T> items = new ArrayList<>();
        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                ) {
            while(resultSet.next()) {
                items.add(mapItem(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public abstract T mapItem(ResultSet resultSet) throws SQLException;
}
