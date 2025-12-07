package net.nussi.denki.bot.session;

import net.nussi.denki.bot.selenium.SeleniumSession;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

public class SessionContext extends SeleniumSession {
    private final Optional<WebDriver> driver = Optional.empty();

    public SessionContext() {}

    public static SessionContext empty() {
        return new SessionContext();
    }

}
