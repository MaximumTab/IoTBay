package com.iotbay.model.dao;

import com.iotbay.model.CreditCards;
import com.iotbay.model.Payment;
import com.iotbay.model.PaymentHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentHistoryDBManager extends DBManager<PaymentHistory> {
    public int getCardCount() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM PaymentHistory");
        resultSet.next();
        return resultSet.getInt(1);
    }

    public PaymentHistoryDBManager(Connection connection) throws SQLException {
        super(connection);
    }

    public List<PaymentHistory> getAllReceipts() {
        List<PaymentHistory> receipts = new ArrayList<>();
        String query = "SELECT * FROM PaymentHistory";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PaymentHistory receipt = new PaymentHistory("0","0","0");
                receipt.setPaymentId(rs.getInt("payment_id"));
                receipt.setPaymentCardNumber(rs.getString("payment_card_number"));
                receipt.setPaymentAmount(rs.getString("payment_amount"));
                receipt.setPaymentDate(rs.getString("payment_date"));
                receipts.add(receipt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return receipts;
    }

    //CREATE
    public PaymentHistory add(PaymentHistory receipt) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PaymentHistory (payment_card_number, payment_amount,payment_date) VALUES (?, ?, ?)");
        preparedStatement.setString(1, receipt.getPaymentCardNumber());
        preparedStatement.setString(2, receipt.getPaymentAmount());
        preparedStatement.setString(3, receipt.getPaymentDate());
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("SELECT MAX(payment_id) FROM PaymentHistory");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int paymentId = resultSet.getInt(1);
        receipt.setPaymentId(paymentId);
        return receipt;
    }

    public PaymentHistory get(PaymentHistory receipt) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PaymentHistory WHERE payment_id = ?");
        preparedStatement.setInt(1, receipt.getPaymentId());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new PaymentHistory(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
    }

    //UPDATE
    public void update(PaymentHistory oldReceipt, PaymentHistory newReceipt) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PaymentHistory SET payment_card_number = ?, payment_amount = ?, payment_date = ? WHERE payment_id = ?");
        preparedStatement.setString(1, newReceipt.getPaymentCardNumber());
        preparedStatement.setString(2, newReceipt.getPaymentAmount());
        preparedStatement.setString(3, newReceipt.getPaymentDate());
        preparedStatement.setInt(4, oldReceipt.getPaymentId());
        preparedStatement.executeUpdate();
    }

    //DELETE
    public void delete(PaymentHistory receipt) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PaymentHistory WHERE payment_id = ?");
        preparedStatement.setInt(1, receipt.getPaymentId());
        preparedStatement.executeUpdate();
    }

}