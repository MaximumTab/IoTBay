import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;
import org.mockito.Mockito;
import uts.isd.model.dao.DAO;
import uts.isd.model.dao.DBConnector;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class ServletTests {

    @Test
    public void testInvalidSaveDetails() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        CreditCardsDBManager db = mock(CreditCardsDBManager.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("db")).thenReturn(dao);
        when(dao.CreditCards()).thenReturn(creditCardsDBManager);

        when(request.getParameter("credit_card_number")).thenReturn("9999 8888 7777 5555");
        when(request.getParameter("ccv")).thenReturn("333");
        when(request.getParameter("bsb")).thenReturn("4444 9999");
        when(request.getParameter("save_payment")).thenReturn(null);

        SaveCardServlet servlet = new SaveCardServlet();
        servlet.doPost(request, response);

        verify(creditCardsDBManager, never()).add(any());
    }

}