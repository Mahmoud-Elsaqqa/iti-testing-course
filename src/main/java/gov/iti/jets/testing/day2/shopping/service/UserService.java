package gov.iti.jets.testing.day2.shopping.service;

import gov.iti.jets.testing.day2.shopping.domain.User;
import gov.iti.jets.testing.day2.shopping.infrastructure.gateway.SmsGateway;
import gov.iti.jets.testing.day2.shopping.infrastructure.persistence.Database;
import gov.iti.jets.testing.day2.shopping.infrastructure.persistence.UserDao;

public class UserService {
    private static final UserService INSTANCE =
            new UserService(SmsGateway.getInstance());

    private final SmsGateway smsGateway;

    public UserService(SmsGateway smsGateway) {
        this.smsGateway = smsGateway;
    }

    public User createUser(User newUser) {
        // Business logic - unit test
//        User user = new User();

        Database.doInTransactionWithoutResult(em -> {
            // Data wrangling - integration test
            String password = newUser.getPassword();
            if (!(password.matches(".{8}")))
                throw new IllegalArgumentException("password must be 8 characters!");

            // Business logic - unit test
            UserDao.save(newUser, em);

            // Data wrangling - integration test
            smsGateway.sendSms(newUser.getPhoneNumber(), "User " + newUser.getName() + " created");
        });

        return newUser;
    }
    public static UserService getInstance() {
        return INSTANCE;
    }


}
