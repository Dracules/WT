package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        boolean isError = false;
        HttpSession httpSession = req.getSession(false);
        if (httpSession != null) {
            Object clientId = httpSession.getAttribute("clientId");
            if (clientId != null) {
                isError = true;
            }
        }
        if (isError) {
            resp.sendRedirect(req.getContextPath());
        }
        else {
            req.setAttribute("regRes", 0);
            req.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String errmsg = null;

        ClientDao clientDao = new ClientDao();
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pass = req.getParameter("passwordHash");
        String passHash = Utils.hashString(pass);
        Client client = new Client(surname, name, email, passHash);
        int clientId = clientDao.insert(client);
        if (clientId == 0) {
            errmsg = "Please, fill your data correctly (client with this username or email is exists)";
        }
        else {
        }
        req.setAttribute("regRes", clientId);
        req.setAttribute("errmsg", errmsg);
        req.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(req, resp);
    }

}
