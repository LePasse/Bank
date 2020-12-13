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

@WebServlet(name = "controller.TransactionController")
public class TransactionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/transaction.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("number");
        String amount = req.getParameter("amount");

        String string_id = req.getParameter("card");

        int id = Integer.parseInt(string_id);
        try {
            Card from = new CardDAOSQL().getCardByID(id);
            Card to = new CardDAOSQL().getCardByNumber(number);
            CardService.transaction(from,to,amount);
        } catch (Exception e) {
            e.printStackTrace();
        }


        resp.sendRedirect("/");
    }
}