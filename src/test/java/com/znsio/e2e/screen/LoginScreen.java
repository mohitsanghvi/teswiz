package com.znsio.e2e.screen;

import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.screen.android.HomeScreenAndroid;
import com.znsio.e2e.screen.android.LoginScreenAndroid;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.commons.lang3.NotImplementedException;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class LoginScreen {
    private static final String screenName = LoginScreen.class.getSimpleName();

    public static LoginScreen get () {
        Driver driver = fetchDriver(Thread.currentThread().getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread().getId());
        System.out.println(screenName + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread().getId());

        switch (platform) {
            case android:
                return new LoginScreenAndroid(driver, visually);
        }
        throw new NotImplementedException(screenName + " is not implemented in " + Runner.platform);
    }

    public abstract LoginScreen enterLoginDetails (String username, String password);

    public abstract LoginScreen login ();

    public abstract String getInvalidLoginError ();

    public abstract LoginScreen dismissAlert ();
}