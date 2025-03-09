import org.example.daoInterface.UserDAO;
import org.example.model.*;
import org.example.service.AuthenticationService;
import org.example.utils.PasswordUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestUsersService {

    private UserDAO mockUserDAO;
    private AuthenticationService mockAuthenticationService;

    @BeforeEach
    void setup() {
        mockUserDAO = mock(UserDAO.class);
        mockAuthenticationService = new AuthenticationService(mockUserDAO);
    }

    @Test
    public void loginUser() {
        // Arrange
        Users testUser = new Users();
        String email = "test@gmail.com";
        String rawPassword = "testpassword";
        String hashedPassword = PasswordUtil.getHashPassword(rawPassword); // Hash the password

        testUser.setEmail(email);
        testUser.setPasswords(hashedPassword); // Store the hashed password in the database
        testUser.setRoles("USER"); // Ensure role is "USER"

        Users userss = new Users();
        userss.setEmail(email);
        userss.setPasswords(rawPassword);
        userss.setRoles("USER");

        when(mockUserDAO.findByEmail(email)).thenReturn(testUser); // Mock DAO response

        // Mock static method using MockedStatic
        try (MockedStatic<PasswordUtil> mockedPasswordUtil = Mockito.mockStatic(PasswordUtil.class)) {
            mockedPasswordUtil.when(() -> PasswordUtil.verifyPassword(rawPassword, hashedPassword))
                    .thenReturn(true); // Mock password verification

            // Act
            Users user = mockAuthenticationService.loginService(userss);

            // Assert
            assertNotNull(user);
            assertEquals(email, user.getEmail());
        }
    }

}
