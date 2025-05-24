package Test;

import com.iotbay.controller.DeviceEditServlet;
import com.iotbay.controller.DeviceDeleteServlet;
import com.iotbay.model.Devices;
import com.iotbay.controller.DeviceAddServlet;
import com.iotbay.model.dao.DAO;
import com.iotbay.model.dao.DevicesDBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class DevicesTests
{
    @Test
    public void testValidDeviceAdd() throws ServletException, IOException, SQLException
    {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        DevicesDBManager db = mock(DevicesDBManager.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("db")).thenReturn(dao);
        when(dao.Devices()).thenReturn(db);

        when(request.getParameter("deviceName")).thenReturn("Sensor A");
        when(request.getParameter("deviceType")).thenReturn("Temperature");
        when(request.getParameter("devicePrice")).thenReturn("25.0");
        when(request.getParameter("deviceQuantity")).thenReturn("100");

        DeviceAddServlet servlet = new DeviceAddServlet();
        servlet.doPost(request, response);

        verify(db).add(any());
        verify(response).sendRedirect(anyString());
    }

    @Test
    public void testInvalidDeviceAddMissingFields() throws ServletException, IOException, SQLException
    {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        DevicesDBManager db = mock(DevicesDBManager.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("db")).thenReturn(dao);
        when(dao.Devices()).thenReturn(db);

        when(request.getParameter("deviceName")).thenReturn(null);  // invalid input

        DeviceAddServlet servlet = new DeviceAddServlet();
        try {
            servlet.doPost(request, response);
        } catch (ServletException ignored) {}

        verify(db, never()).add(any());
    }

    @Test
    public void testDeviceEditValidUpdate() throws ServletException, IOException, SQLException
    {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        DevicesDBManager db = mock(DevicesDBManager.class);

        Devices existing = new Devices(1, "Old", "Type1", 10.0, 5, null);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("db")).thenReturn(dao);
        when(dao.Devices()).thenReturn(db);
        when(request.getParameter("deviceId")).thenReturn("1");
        when(request.getParameter("deviceName")).thenReturn("Updated");
        when(request.getParameter("deviceType")).thenReturn("UpdatedType");
        when(request.getParameter("devicePrice")).thenReturn("50.0");
        when(request.getParameter("deviceQuantity")).thenReturn("25");
        when(db.get(any())).thenReturn(existing);

        DeviceEditServlet servlet = new DeviceEditServlet();
        servlet.doPost(request, response);

        verify(db).update(eq(existing), any());
        verify(response).sendRedirect(contains("DevicesServlet"));
    }

    @Test
    public void testDeleteDeviceSuccess() throws ServletException, IOException, SQLException {
        //mocks
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        DevicesDBManager db = mock(DevicesDBManager.class);
        Devices expectedDevice = new Devices(2, "DeleteMe", "TypeX", 99.0, 10, null);

        //example data
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("db")).thenReturn(dao);
        when(request.getParameter("selectedDeviceId")).thenReturn("2");
        when(dao.Devices()).thenReturn(db);
        when(db.get(any())).thenReturn(expectedDevice); // if needed for your implementation

        //running servlet
        DeviceDeleteServlet servlet = new DeviceDeleteServlet();
        servlet.doPost(request, response);

        //verify deletion
        verify(db).delete(argThat(device -> device.getDeviceId() == 2));
        verify(response).sendRedirect(contains("DevicesServlet"));
    }





}
