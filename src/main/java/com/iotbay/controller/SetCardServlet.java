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


@WebServlet("/SetCardServlet")
public class SetCardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SetCardServlet: POST request received");
        HttpSession session = req.getSession();
        DAO db = (DAO)session.getAttribute("db");

        String cardIdStr = req.getParameter("card_id");
        System.out.println("Received card_id value for set card servlet: " + cardIdStr);
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
        session.setAttribute("card", card);


        resp.sendRedirect("EditCardPage.jsp");
    }
}
