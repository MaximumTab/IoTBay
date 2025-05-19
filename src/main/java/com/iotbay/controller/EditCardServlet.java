package com.iotbay.controller;

import com.iotbay.model.CreditCards;
import com.iotbay.model.dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EditCardServlet")
public class EditCardServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        DAO db = (DAO)session.getAttribute("db");

        String cardNumber = req.getParameter("card_number");
        String ccv = req.getParameter("ccv");
        String bsb = req.getParameter("bsb");

        CreditCards currentCard = (CreditCards) session.getAttribute("card");
        CreditCards newCard = new CreditCards(cardNumber, ccv, bsb);

        try {
            db.CreditCards().update(currentCard, newCard);
            session.setAttribute("card", newCard);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("CheckOutPage.jsp");
    }
}