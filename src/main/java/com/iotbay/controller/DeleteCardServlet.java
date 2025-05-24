package com.iotbay.controller;

import com.iotbay.model.CreditCards;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.iotbay.model.dao.DAO;
import com.iotbay.model.dao.DBConnector;
import com.iotbay.model.dao.DBManager;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/DeleteCardServlet")
public class DeleteCardServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DeleteCardServlet: POST request received");
        HttpSession session = req.getSession();
        DAO db = (DAO)session.getAttribute("db");

        String cardIdStr = req.getParameter("card_id");
        System.out.println("Received card_id value or whatever: " + cardIdStr);
        if (cardIdStr == null || cardIdStr.isEmpty()) {
            System.out.println("Card id is empty bro");
            return;
        }

        int cardId = Integer.parseInt(cardIdStr);
        CreditCards card = null;
        try {
            card = db.CreditCards().getCard(cardId);
            System.out.println("Set card is : " + card.getCardId());
        } catch (SQLException e) {
            System.out.println("Failed to set Card");
        }


        try {
            System.out.println("Deleting card: " + card.getCreditCardNumber());
            db.CreditCards().delete(card);
            System.out.println("Deleted Card");
        } catch (SQLException e) {
            System.out.format("Failed to remove %s from the database", card.getCardId());
        }
        resp.sendRedirect("CheckOutPage.jsp");
    }
}
