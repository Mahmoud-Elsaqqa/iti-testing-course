package gov.iti.jets.testing.day2.shopping.presentation;

import gov.iti.jets.testing.day2.shopping.domain.User;
import gov.iti.jets.testing.day2.shopping.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/create-user")
public class CreateUserServlet extends HttpServlet {
    public CreateUserServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");

        User newUser = new User(userName, phone, password);

        newUser = UserService.getInstance().createUser(newUser);

        RequestAttributes.CREATED_USER.set(req, newUser);

        Jsps.VIEW_CREATED_USER.forward(req, resp);


    }

}
