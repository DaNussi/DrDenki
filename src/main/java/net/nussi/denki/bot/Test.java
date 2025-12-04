package net.nussi.denki.bot;

import jakarta.annotation.PostConstruct;
import net.nussi.denki.Credentials;
import net.nussi.denki.bot.selenium.SeleniumService;
import net.nussi.denki.bot.session.Session;
import net.nussi.denki.bot.workflow.workflow.Workflow;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Test {

    @Autowired
    private SeleniumService seleniumService;

    @PostConstruct
    public void init() {

        WebDriver driver = seleniumService.getDriver();
        Credentials credentials = new Credentials("", "");

        Session session = new Session(driver, credentials);

        Workflow<Session> workflow = Workflow
                .builder("Quiz Automation", session)
                .stage("Login",s -> s
                        .addStep(null)
                )
                .build();


    }

}
