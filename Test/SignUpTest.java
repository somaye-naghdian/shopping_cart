import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import view.CustomerView;

public class SignUpTest {
    CustomerView customerView = new CustomerView();


    @DataProvider(name = "phoneDataProvider")
    public Object[] phoneDataProvider() {
        String phone1 = "123354452352";
        String phone2 = "1-852000";
        String phone3 = "-74185963";
        String phone4 = "12547885-";
        String phone5 = "74103.952";
        return new Object[]{phone1, phone2, phone3, phone4, phone5};
    }

    @Test(dataProvider = "phoneDataProvider")
    public void testPhone(String phone) {
        Assert.assertFalse(customerView.phoneValidation(phone));
    }

    @DataProvider(name = "emailDataProvider")
    public Object[] emailDataProvider() {
        String email1 = "asdfghj.fghjk";
        String email2 = "ghjk@fghjk";
        String email3 = "asdtvhgevhjeffghjk";
        String email4 = "@asdfghjfghjk";
        String email5 = ".asdfgfghjk";
        return new Object[]{email1, email2, email3, email4, email5};
    }

    @Test(dataProvider = "emailDataProvider")
    public void testEmail(String email) {
        Assert.assertFalse(customerView.checkEmail(email));
    }

    @DataProvider(name = "usernameDataProvider")
    public Object[] usernameDataProvider() {
        String username1 = "";
        String username2 = "likuk,mjnhbgfvdc.k,jmhgfvcjhgvfcdsmnb";
        String username3 = "dd";
        String username4 = "cdc";
        String username5 = "s1ckflkfmpok113215646305663fydgfdjh";

        return new Object[]{username1, username2, username3, username4, username5};
    }

    @Test(dataProvider = "usernameDataProvider")
    public void testUsername(String username) {
        Assert.assertFalse(customerView.usernameValidation(username));
    }

    @DataProvider(name = "passwordDataProvider")
    public Object[] passwordDataProvider() {
        String pass1 = "";
        String pass2 = "likujhgvfcdsmnb";
        String pass3 = "dd";
        String pass4 = "51ddcdvv232";

        return new Object[]{pass1, pass2, pass3, pass4};
    }

    @Test(dataProvider = "passwordDataProvider")
    public void testPassword(String password) {
        Assert.assertFalse(customerView.passwordValidation(password));
    }
}
