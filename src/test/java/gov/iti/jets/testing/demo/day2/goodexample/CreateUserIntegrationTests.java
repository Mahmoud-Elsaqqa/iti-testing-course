package gov.iti.jets.testing.demo.day2.goodexample;

import gov.iti.jets.testing.day2.shopping.domain.User;
import gov.iti.jets.testing.day2.shopping.infrastructure.gateway.SmsGateway;
import gov.iti.jets.testing.day2.shopping.infrastructure.persistence.Database;
import gov.iti.jets.testing.day2.shopping.presentation.CreateUserServlet;
import gov.iti.jets.testing.day2.shopping.presentation.RequestAttributes;
import gov.iti.jets.testing.demo.day2.extensions.DatabaseTest;
import jakarta.servlet.ServletException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

@DatabaseTest
@Tag("integration")
class CreateUserIntegrationTests {

    @Test
    void User_is_created_servlet() throws ServletException, IOException {
        //Arrange
        SmsGateway smsGatewayMock = Mockito.mock();

//        UserService userService = UserService.getInstance();
//        User user = new User(
//                "Mahmoud",
//                "01005812889",
//                "12345678");
//        userService.createUser(user);
        String userName = "Mahmoud";
        String phone = "01005812889";
        String password = "12345678";

        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setMethod("GET");
        mockRequest.setParameter("userName", userName);
        mockRequest.setParameter("phone", phone);
        mockRequest.setParameter("password", password);
        var mockResponse = new MockHttpServletResponse();

        //Act
        CreateUserServlet createUserServlet = new CreateUserServlet();
        createUserServlet.service(mockRequest, mockResponse);
        User createdUser = RequestAttributes.CREATED_USER.get(mockRequest);

        //Assert
        User savedUser = Database.doInTransaction((entityManager) -> {
            return entityManager.createQuery("SELECT u FROM User u", User.class)
                    .getSingleResult();
        });

        Assertions.assertThat(createdUser).
                usingRecursiveComparison().isEqualTo(savedUser);

        // 3. Assert forward correct
        Assertions.assertThat(mockResponse.getForwardedUrl())
                .isEqualTo("/WEB-INF/view-created-user.jsp");
    }

}


