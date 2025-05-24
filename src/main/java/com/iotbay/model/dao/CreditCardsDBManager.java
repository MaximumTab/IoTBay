package com.iotbay.model.dao;

import com.iotbay.model.CreditCards;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardsDBManager extends DBManager<CreditCards> {
    public int getCardCount() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM CreditCards");
        resultSet.next();
        return resultSet.getInt(1);
    }

    public String getCardNumber(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CreditCards WHERE card_id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("credit_card_number");
    }

    public CreditCards getCard(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CreditCards WHERE card_id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            System.out.println("Card found in DB with ID: " + id);


             CreditCards card = new CreditCards(
                    resultSet.getString("credit_card_number"),
                    resultSet.getString("ccv"),
                    resultSet.getString("bsb")
            );
            card.setCardId(resultSet.getInt("card_id")); // <- Set the ID explicitly
            System.out.println("Card Details - ID: " + card.getCardId() +
                    ", Number: " + card.getCreditCardNumber() +
                    ", CCV: " + card.getCcv() +
                    ", BSB: " + card.getBsb());
            System.out.println("All details found less gooooo!");
            return card;
        } else {
            System.out.println("No card found with ID: " + id);
            return null;
        }
    }

    public List<CreditCards> getAllCards() {
        List<CreditCards> cards = new ArrayList<>();
        String query = "SELECT * FROM CreditCards";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CreditCards card = new CreditCards("0","0","0");
                card.setCardId(rs.getInt("card_id"));
                card.setCreditCardNumber(rs.getString("credit_card_number"));
                card.setBsb(rs.getString("bsb"));
                card.setCcv(rs.getString("ccv"));
                cards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cards;
    }

    public CreditCardsDBManager(Connection connection) throws SQLException {
        super(connection);
    }

    //CREATE
    public CreditCards add(CreditCards card) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CreditCards (credit_card_number, ccv,bsb) VALUES (?, ?, ?)");
        preparedStatement.setString(1, card.getCreditCardNumber());
        preparedStatement.setString(2, card.getCcv());
        preparedStatement.setString(3, card.getBsb());
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("SELECT MAX(card_id) FROM CreditCards");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int cardId = resultSet.getInt(1);
        card.setCardId(cardId);
        return card;
    }

    public CreditCards get(CreditCards card) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CreditCards WHERE card_id = ?");
        preparedStatement.setInt(1, card.getCardId());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new CreditCards(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
    }

    //UPDATE
    public void update(CreditCards oldCard, CreditCards newCard) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE CreditCards SET credit_card_number = ?, ccv = ?, bsb = ? WHERE card_id = ?");
        preparedStatement.setString(1, newCard.getCreditCardNumber());
        preparedStatement.setString(2, newCard.getCcv());
        preparedStatement.setString(3, newCard.getBsb());
        preparedStatement.setInt(4, oldCard.getCardId());
        preparedStatement.executeUpdate();
    }

    //DELETE
    public void delete(CreditCards card) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM CreditCards WHERE card_id = ?");
        preparedStatement.setInt(1, card.getCardId());
        preparedStatement.executeUpdate();
    }
}