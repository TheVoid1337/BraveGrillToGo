package valueObjects;

import exceptions.InvalidEmailAddressException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = EmailAddress.class)
class EmailAddressTest {

    @Test
    void createEmailAddressIncorrectTest(){
        assertThrows(InvalidEmailAddressException.class, ()->new EmailAddress("invalid-email@domain"));
        assertThrows(InvalidEmailAddressException.class, ()->new EmailAddress("@example.com"));
        assertThrows(InvalidEmailAddressException.class, ()->new EmailAddress("user@invalid domain.com"));
        assertThrows(InvalidEmailAddressException.class, ()->new EmailAddress("user@domain_with_underscore.com"));
    }

    @Test
    void createEmailAddressCorrectTest(){
        String email = "john.doe12345@subdomain.example.org";
        EmailAddress emailAddress = new EmailAddress(email);
        assertEquals(emailAddress.getEmailAddress(),email);
    }
}