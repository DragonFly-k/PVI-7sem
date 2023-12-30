package services;

import bean.WsrefComment;
import dto.WsrefCommentDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WsrefCommentService {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public List<WsrefComment> findByWsref(int wsref) {
        List<WsrefComment> wsrefCommentList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from WSREFCOMMENT where wsref_id = ? order by id desc");
            preparedStatement.setInt(1, wsref);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                WsrefComment wsrefComment = new WsrefComment();
                wsrefComment.setId(resultSet.getInt("id"));
                wsrefComment.setWsrefId(resultSet.getInt("wsref_id"));
                wsrefComment.setSessionId(resultSet.getString("session_id"));
                wsrefComment.setStamp(resultSet.getTimestamp("stamp"));
                wsrefComment.setComtext(resultSet.getString("comtext"));
                wsrefCommentList.add(wsrefComment);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connectionPool.returnConnection(connection);
        }
        return wsrefCommentList;
    }

    public void insertWsrefComment(WsrefCommentDto wsrefCommentDto) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into WSREFCOMMENT(wsref_id, session_id, comtext, stamp) values (?, ?, ?,?)");
            preparedStatement.setString(1, wsrefCommentDto.getWsrefId());
            preparedStatement.setString(2, wsrefCommentDto.getSessionId());
            preparedStatement.setString(3, wsrefCommentDto.getComtext());
            preparedStatement.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connectionPool.returnConnection(connection);
        }
    }

    public void updateWsrefComment(int id, WsrefCommentDto wsrefCommentDto) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update WSREFCOMMENT set comtext = ? where id = ?");
            preparedStatement.setString(1, wsrefCommentDto.getComtext());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connectionPool.returnConnection(connection);
        }
    }

    public void deleteWsrefComment(int id) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from WSREFCOMMENT where id = ?");
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