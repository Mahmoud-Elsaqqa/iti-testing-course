package gov.iti.jets.testing.demo.day2.goodexample;

import gov.iti.jets.testing.day2.shopping.domain.User;
import gov.iti.jets.testing.day2.shopping.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateUserTest {
    @Test
    void User_password_must_be_8chars() {
        //Arrange
        User user = new User(
                "Mahmoud",
                "01005812889",
                "123");
        //Act //Assert
        Assertions.assertThatThrownBy(() ->
                UserService.getInstance().createUser(user)).
                isInstanceOf(IllegalArgumentException.class);

    }
}
