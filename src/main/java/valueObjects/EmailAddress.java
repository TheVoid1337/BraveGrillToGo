package valueObjects;

import exceptions.valueObjects.InvalidEmailAddressException;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor
public class EmailAddress {

    @Getter
    private String emailAddress;

    private final String EMAIL_REGEX =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private boolean matchesPattern(String email){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public EmailAddress(String emailAddress) {
        if (emailAddress == null)
            throw new InvalidEmailAddressException("Email address can not be null!");
        if (emailAddress.isBlank())
            throw new InvalidEmailAddressException("Email address be empty!");
        if (!matchesPattern(emailAddress))
            throw new InvalidEmailAddressException("Email address does not match correct pattern");

        this.emailAddress = emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        if (!matchesPattern(emailAddress))
            throw new InvalidEmailAddressException("Email address does not match correct pattern");
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(EmailAddress.class)){
            EmailAddress emailAddress = (EmailAddress) obj;
            return emailAddress.getEmailAddress().equals(this.emailAddress);
        }
        return false;
    }
}
