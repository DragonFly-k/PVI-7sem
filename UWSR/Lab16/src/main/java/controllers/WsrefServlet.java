package controllers;

import bean.Wsref;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.WsrefDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.WsrefService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class WsrefServlet extends HttpServlet {
    private final WsrefService wsrefService = new WsrefService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        try {
            String filter = req.getParameter("filter");
            List<Wsref> wsrefList = filter != null ? wsrefService.findByFilter(filter) : wsrefService.findAll();
            objectMapper.writeValue(resp.getOutputStream(), wsrefList);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getOutputStream(), new ErrorResponse(e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            WsrefDto wsrefDto = parseBody(req);
            wsrefService.insertWsref(wsrefDto);
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
            if (pathParts.length == 3) {
                String field = pathParts[2];
                switch (field) {
                    case "plus":
                        wsrefService.incrementWsref(id);
                        break;
                    case "minus":
                        wsrefService.decrementWsref(id);
                        break;
                }
            } else {
                WsrefDto wsrefDto = parseBody(req);
                wsrefService.updateWsref(id, wsrefDto);
            }
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getOutputStream(), new ErrorResponse(e.getMessage()));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] pathParts = req.getPathInfo().split("/");
            int id = Integer.parseInt(pathParts[1]);
            wsrefService.deleteWsref(id);
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(resp.getOutputStream(), new ErrorResponse(e.getMessage()));
        }
    }

    private WsrefDto parseBody(HttpServletRequest req) throws IOException {
        StringBuilder jsonBody = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBody.append(line);
        }
        return  objectMapper.readValue(jsonBody.toString(), WsrefDto.class);
    }
}