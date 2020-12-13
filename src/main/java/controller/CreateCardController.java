package controller;

import DAO.sql.CardDAOSQL;
import DAO.sql.UserDAOSQL;
import entity.Card;
import entity.User;
import service.CardService;
import service.UserService;
import utils.Hash;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controller.CreateCardController")
public class CreateCardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/CreateCard.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //add card creation
        String cardname = req.getParameter("cardname");
        try {
            User user = (User) req.getSession().getAttribute("user");
            if (user != null) {
                boolean result = CardService.createCard(user.id, cardname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        resp.sendRedirect("/");
    }
}