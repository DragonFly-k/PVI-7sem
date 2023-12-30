package controllers;

import bean.WsrefComment;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.WsrefCommentDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.WsrefCommentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class WsrefCommentServlet extends HttpServlet {
    private final WsrefCommentService wsrefCommentService = new WsrefCommentService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("x-session-id", req.getSession().getId());
        try {
            int wsref = Integer.parseInt(req.getParameter("wsref"));
            List<WsrefComment> wsrefCommentList = wsrefCommentService.findByWsref(wsref);
            objectMapper.writeValue(resp.getOutputStream(), wsrefCommentList);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getOutputStream(), new ErrorResponse(e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            WsrefCommentDto wsrefCommentDto = parseBody(req);
            System.out.println(req.getSession().getId());
            wsrefCommentDto.setSessionId(req.getSession().getId());
            wsrefCommentService.insertWsrefComment(wsrefCommentDto);
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getOutputStream(), new ErrorResponse(e.getMessage()));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] pathParts = req.getPathInfo().split("/");
            int id = Integer.parseInt(pathParts[1]);
            WsrefCommentDto wsrefCommentDto = parseBody(req);
            wsrefCommentService.updateWsrefComment(id, wsrefCommentDto);
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getOutputStream(), new ErrorResponse(e.getMessage()));
        }
    }

    private WsrefCommentDto parseBody(HttpServletRequest req) throws IOException {
        StringBuilder jsonBody = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBody.append(line);
        }
        return objectMapper.readValue(jsonBody.toString(), WsrefCommentDto.class);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] pathParts = req.getPathInfo().split("/");
            int id = Integer.parseInt(pathParts[1]);
            wsrefCommentService.deleteWsrefComment(id);
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getOutputStream(), new ErrorResponse(e.getMessage()));
        }
    }
}