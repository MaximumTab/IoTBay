package com.iotbay.controller;

import com.iotbay.model.CreditCards;
import com.iotbay.model.PaymentHistory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.iotbay.model.dao.DAO;
import com.iotbay.model.dao.DBConnector;
import com.iotbay.model.dao.DBManager;
import java.time.LocalDate;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/ReceiptGeneratorServlet")
public class ReceiptGeneratorServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ReceiptGeneratorServlet: POST request received");
        HttpSession session = req.getSession();
        DAO db = (DAO)session.getAttribute("db");
        CreditCards card = (CreditCards)session.getAttribute("card");

        String cardIdStr = req.getParameter("card_id");
        System.out.println("Received card_id value or whatever: " + cardIdStr);
        if (cardIdStr == null || cardIdStr.isEmpty()) {
            System.out.println("Card id is empty bro trying to get it from session");
            System.out.println("Card from session is " + card.getCreditCardNumber());
        }else {

            int cardId = Integer.parseInt(cardIdStr);
            try {
                card = db.CreditCards().getCard(cardId);
                System.out.println("Set card is : " + card.getCardId());
            } catch (SQLException e) {
                System.out.println("Failed to set Card");
            }
        }

        LocalDate date = LocalDate.now();
        String dateStr = date.toString();
        String sqlDateStr = "'" + dateStr + "'";
        System.out.println("Received date: " + sqlDateStr);

        PaymentHistory receipt = new PaymentHistory(card.getCreditCardNumber(), "999",sqlDateStr);
        try {
            db.PaymentHistory().add(receipt);
            System.out.println("New Receipt Saved");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        resp.sendRedirect("MainPage.jsp");
    }
}
