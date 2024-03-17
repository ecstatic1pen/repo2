import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String JDBC_USER = "username";
    private static final String JDBC_PASSWORD = "password";

    // JDBC variables for opening, closing and managing connection
    private Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to MySQL database
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        // Close connection when servlet is destroyed
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.getWriter().println("Invalid action");
            return;
        }

        switch (action) {
            case "get":
                getUser(request, response);
                break;
            case "add":
                addUser(request, response);
                break;
            case "update":
                updateUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            default:
                response.getWriter().println("Invalid action");
        }
    }

    private void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Implement code to fetch user from database and return response
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Implement code to add user to database
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Implement code to update user in database
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Implement code to delete user from database
    }
}
