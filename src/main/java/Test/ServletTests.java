package Test;

import com.iotbay.controller.DeleteCardServlet;
import com.iotbay.controller.EditCardServlet;
import com.iotbay.controller.SaveCardServlet;
import com.iotbay.model.CreditCards;
import com.iotbay.model.dao.CreditCardsDBManager;
import com.iotbay.model.dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class ServletTests {

    @Test
    public void testInvalidSaveDetails() throws ServletException, IOException, SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        CreditCardsDBManager db = mock(CreditCardsDBManager.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("db")).thenReturn(dao);
        when(dao.CreditCards()).thenReturn(db);

        when(request.getParameter("credit_card_number")).thenReturn("9999 8888 7777 5555");
        when(request.getParameter("ccv")).thenReturn("333");
        when(request.getParameter("bsb")).thenReturn("4444 9999");
        when(request.getParameter("save_payment")).thenReturn(null);

        SaveCardServlet servlet = new SaveCardServlet();
        servlet.doPost(request, response);

        verify(db, never()).add(any());
    }

    @Test
    public void testValidEditCard() throws ServletException, IOException, SQLException {
        // mocks
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        CreditCardsDBManager db = mock(CreditCardsDBManager.class);

        // already existing example card
        CreditCards existingCard = new CreditCards("1111 2222 3333 4444", "111", "1234 5678");

        // example data
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("db")).thenReturn(dao);
        when(dao.CreditCards()).thenReturn(db);
        when(session.getAttribute("card")).thenReturn(existingCard);
        when(request.getParameter("card_number")).thenReturn("5555 6666 7777 8888");
        when(request.getParameter("ccv")).thenReturn("999");
        when(request.getParameter("bsb")).thenReturn("4321 8765");

        // servlet stuff
        EditCardServlet servlet = new EditCardServlet();
        servlet.doPost(request, response);

        // expected new card
        CreditCards expectedNewCard = new CreditCards("5555 6666 7777 8888", "999", "4321 8765");

        // verifying update method called
        verify(db).update(eq(existingCard), argThat(newCard ->
                newCard.getCreditCardNumber().equals(expectedNewCard.getCreditCardNumber()) &&
                        newCard.getCcv().equals(expectedNewCard.getCcv()) &&
                        newCard.getBsb().equals(expectedNewCard.getBsb())
        ));

        // verifying that the redirect happened
        verify(response).sendRedirect("CheckOutPage.jsp");
    }

    @Test
    public void testDeleteCardSuccess() throws ServletException, IOException, SQLException {
        // mocks
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        CreditCardsDBManager db = mock(CreditCardsDBManager.class);
        CreditCards card = new CreditCards("1234 5678 9012 3456", "123", "1111 2222");
        card.setCardId(1);

        // example data
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("db")).thenReturn(dao);
        when(request.getParameter("card_id")).thenReturn("1");
        when(dao.CreditCards()).thenReturn(db);
        when(db.getCard(1)).thenReturn(card);

        // running servlet
        DeleteCardServlet servlet = new DeleteCardServlet();
        servlet.doPost(request, response);

        // verifying deletion and redirect
        verify(db).delete(card);
        verify(response).sendRedirect("CheckOutPage.jsp");
    }

}