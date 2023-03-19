package gov.iti.jets.testing.ignore;

import gov.iti.jets.testing.day2.shopping.domain.Order;
import gov.iti.jets.testing.day2.shopping.domain.Product;
import gov.iti.jets.testing.day2.shopping.domain.ShoppingCart;
import gov.iti.jets.testing.day2.shopping.domain.User;
import gov.iti.jets.testing.day2.shopping.infrastructure.persistence.Database;
import gov.iti.jets.testing.day2.shopping.infrastructure.persistence.ProductDao;
import gov.iti.jets.testing.day2.shopping.infrastructure.persistence.UserDao;
import gov.iti.jets.testing.demo.day2.extensions.DatabaseTest;
import org.junit.jupiter.api.Test;


@DatabaseTest
class IgnoredOrderTest {
    @Test
    void test() {
        // Arrange
        User user = new User("Mahmoud", "01117950444","9873498237489234");
        Database.doInTransactionWithoutResult(em -> UserDao.save(user, em));
        ShoppingCart shoppingCart = new ShoppingCart(user.getId());

        Product desk = new Product("123", 10_000);
        Database.doInTransactionWithoutResult(em -> ProductDao.save(desk, em));
        shoppingCart.addProduct(desk);

        // Act
        Order order = Order.createOrder(shoppingCart);
    }
}
