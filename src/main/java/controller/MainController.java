package controller;

import DAO.CardDAO;
import DAO.sql.CardDAOSQL;
import DAO.sql.AccountDAOSQL;
import DAO.sql.UserDAOSQL;
import entity.Account;
import entity.User;
import entity.Card;
import service.AccountService;
import service.CardService;
import service.UserService;
import utils.DBConnection;
import utils.Hash;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "controller.MainController")
public class MainController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String)req.getSession().getAttribute("email");
        String phash = (String)req.getSession().getAttribute("phash");

        if (email != null && phash != null) {
            User user = UserService.auth(email, phash);
            if (user == null) {
                req.getSession().invalidate();
            }
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/main.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "logout": {
                req.getSession().invalidate();
                break;
            }
            case "unblock": {
                String number = req.getParameter("number");
                try {
                    Card card = new CardDAOSQL().getCardByNumber(number);
                    new CardDAOSQL().unblock(card.id);
                    User user = (User) req.getSession().getAttribute("user");
                    user.cards = new UserDAOSQL().getCards(user.id);
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
            case "refill": {
                String card = req.getParameter("card");
                String amount = req.getParameter("amount");
                try {
                    boolean result = CardService.increaseBalance(Integer.parseInt(card),Double.parseDouble(amount));

                    User user = (User) req.getSession().getAttribute("user");
                    user.cards = new UserDAOSQL().getCards(user.id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "block": {
                String card = req.getParameter("card");
                int id = Integer.parseInt(card);
                try {
                    new CardDAOSQL().block(id);
                    User user = (User) req.getSession().getAttribute("user");
                    user.cards = new UserDAOSQL().getCards(user.id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            default: {
                break;
            }
        }
        resp.sendRedirect("/");
    }
}
