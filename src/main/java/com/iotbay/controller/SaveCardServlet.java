package com.iotbay.controller;

import com.iotbay.model.CreditCards;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.iotbay.model.dao.DAO;


import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/SaveCardServlet")
public class SaveCardServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SaveCardServletCheck: POST request received");
        HttpSession session = req.getSession();

        DAO db = (DAO)session.getAttribute("db");
        if (req.getParameter("save_payment") != null) {
            String cardNumber = req.getParameter("card_number");
            String ccv = req.getParameter("ccv");
            String bsb = req.getParameter("bsb");
            CreditCards newCard = new CreditCards(cardNumber,ccv,bsb);
            try {
                db.CreditCards().add(newCard);
                System.out.println("SaveCardServlet: Card added");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        resp.sendRedirect("MainPage.jsp");
    }
}


