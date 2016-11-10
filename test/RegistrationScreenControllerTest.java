import model.DatabaseInterface;
import model.UserType;
import controller.RegistrationScreenController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ChrisPolack on 11/9/16.
 */
public class RegistrationScreenControllerTest {

    RegistrationScreenController ctrl;
    DatabaseInterface db;

    @Before
    public void setUp() throws Exception {
        ctrl = new RegistrationScreenController();
        db = new DatabaseInterface();
    }

    @Test
    public void checkNullType() throws Exception {
        Assert.assertFalse(ctrl.isRegistrationInfoAcceptable("user", "pass", null, db));

    }

    @Test
    public void checkEmptyUser() throws Exception {
        Assert.assertFalse(ctrl.isRegistrationInfoAcceptable("", "pass", UserType.User, db));

    }

    @Test
    public void checkEmptyPass() throws Exception {
        Assert.assertFalse(ctrl.isRegistrationInfoAcceptable("user", "", UserType.User, db));

    }

    @Test
    public void checkInvalidUsernameChar() throws Exception {
        Assert.assertFalse(ctrl.isRegistrationInfoAcceptable("?", "pass", UserType.User, db));
    }

    @Test
    public void checkInvalidPasswordChar() throws Exception {
        Assert.assertFalse(ctrl.isRegistrationInfoAcceptable("user", "'", UserType.User, db));
    }

    @Test
    public void checkUsernameExists() throws Exception {
        Assert.assertFalse(ctrl.isRegistrationInfoAcceptable("s", "s", UserType.User, db));
    }

    @Test
    public void checkValid() throws Exception {
        Assert.assertTrue(ctrl.isRegistrationInfoAcceptable("anewuser", "pass", UserType.User, db));
    }

}