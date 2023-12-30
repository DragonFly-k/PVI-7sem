package services;

import bean.Wsref;
import dto.WsrefDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WsrefService {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public List<Wsref> findAll() {
        List<Wsref> wsrefList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from WSREF order by id desc");
            while (resultSet.next()) {
                Wsref wsref = new Wsref();
                wsref.setId(resultSet.getInt("id"));
                wsref.setUrl(resultSet.getString("url"));
                wsref.setDescription(resultSet.getString("description"));
                wsref.setMinus(resultSet.getInt("minus"));
                wsref.setPlus(resultSet.getInt("plus"));
                wsrefList.add(wsref);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connectionPool.returnConnection(connection);
        }
        return wsrefList;
    }

    public List<Wsref> findByFilter(String filter) {
        List<Wsref> wsrefList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from WSREF where description like ? order by id desc");
            preparedStatement.setString(1, "%" + filter + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Wsref wsref = new Wsref();
                wsref.setId(resultSet.getInt("id"));
                wsref.setUrl(resultSet.getString("url"));
                wsref.setDescription(resultSet.getString("description"));
                wsref.setMinus(resultSet.getInt("minus"));
                wsref.setPlus(resultSet.getInt("plus"));
                wsrefList.add(wsref);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connectionPool.returnConnection(connection);
        }
        return wsrefList;
    }

    public void insertWsref(WsrefDto wsrefDto) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into WSREF(url, description,minus, plus) values (?, ?,0,0)");
            preparedStatement.setString(1, wsrefDto.getUrl());
            preparedStatement.setString(2, wsrefDto.getDescription());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connectionPool.returnConnection(connection);
        }
    }

    public void updateWsref(int id, WsrefDto wsrefDto) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update WSREF set url = ?, description = ? where id = ?");
            preparedStatement.setString(1, wsrefDto.getUrl());
            preparedStatement.setString(2, wsrefDto.getDescription());
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connectionPool.returnConnection(connection);
        }
    }

    public void incrementWsref(int id) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update WSREF set plus = plus + 1 where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connectionPool.returnConnection(connection);
        }
    }

    public void decrementWsref(int id) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update WSREF set minus = minus + 1 where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connectionPool.returnConnection(connection);
        }
    }

    public void deleteWsref(int id) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from WSREF where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connectionPool.returnConnection(connection);
        }
    }
}