package com;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        boolean isError = false;
        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession != null) {
            Object userIdObj = httpSession.getAttribute("userId");
            if (userIdObj != null) {
                isError = true;
            }
        }
        if (isError) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
        }
        else {
            httpServletRequest.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String errmsg = null;

        String email = httpServletRequest.getParameter("email");
        String pass = httpServletRequest.getParameter("pass");
        String passHash = Utils.hashString(pass);

        ClientDao clientDao = new ClientDao();
        Client client = clientDao.selectOneByEmail(email);
        if (client == null) {
            errmsg = "Email '" + email + "' not found!";
        }
        else {
            if (!passHash.equals(client.getPasswordHash())) {
                errmsg = "Password is incorrect!";
            }
            else {
                HttpSession httpSession = httpServletRequest.getSession();
                httpSession.setAttribute("clientId", client.getId());
                httpSession.setAttribute("client", client);
            }
        }
        httpServletRequest.setAttribute("errmsg", errmsg);
        httpServletRequest.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
