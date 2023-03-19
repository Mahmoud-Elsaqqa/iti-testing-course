import gov.iti.jets.testing.lab01.User;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

public class UserTest {

    private static User createUser() {
        User user = new User("user1", "password");
        return user;
    }

    @Test
    void Current_password_is_latest_added() {
        // Arrange
        User user = createUser();

        // Act
        String currentPassword = user.getPassword();

        // Assert
        Assertions.assertThat(currentPassword).isEqualTo("password");
    }

    @Test
    void Duplicate_passwords_arent_allowed() {
        // Arrange
        User user = createUser();
        String newPassword = "passwrd";

        // Act
        ThrowableAssert.ThrowingCallable updatePasswordCall = () -> user.updatePassword(newPassword); ;

        // Assert
        Assertions.assertThatThrownBy( updatePasswordCall )
                .isInstanceOf( IllegalArgumentException.class );
    }
}
