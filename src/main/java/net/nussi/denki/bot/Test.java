package net.nussi.denki.bot;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.Credentials;
import net.nussi.denki.bot.selenium.SeleniumConfig;
import net.nussi.denki.bot.selenium.steps.*;
import net.nussi.denki.bot.session.SessionContext;
import net.nussi.denki.bot.workflow.workflow.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class Test {

    @Autowired
    private SeleniumConfig seleniumConfig;

    @PostConstruct
    public void init() {

        log.info("Init Workflow");

        Credentials credentials = new Credentials("", "");

        SessionContext context = new SessionContext();

//        String LOGIN_BUTTON_XPATH = "//button[text()=\"Mit TU Wien SSO anmelden\"]";
        String LOGIN_BUTTON_XPATH = "//button[./span[normalize-space()='Login']]";
        String LOGIN_USERNAME_INPUT_XPATH = "//input[@data-path='name']";
        String LOGIN_PASSWORD_INPUT_XPATH = "//input[@data-path='password']";

        Workflow<SessionContext> workflow = Workflow.builder("DrDenki", context)
//                .variable("LOGIN_BUTTON_XPATH", "//button[text()=\"Mit TU Wien SSO anmelden\"]")

                .stage("Quiz Solver", (stage) -> stage
                                .steps(steps -> steps
                                                .step(new SeleniumConnectStep<>(seleniumConfig))
//                                                .step(new NavigateStep<>("https://memory.iguw.tuwien.ac.at/login"))
                                                .step(new NavigateStep<>("https://dashboard.nussi.net"))
                                                .delay(Duration.of(3, ChronoUnit.SECONDS))
                                                .step(new ElementWaitStep<>(LOGIN_USERNAME_INPUT_XPATH, Duration.of(3, ChronoUnit.SECONDS)))
                                                .step(new ElementWriteStep<>(LOGIN_USERNAME_INPUT_XPATH, credentials.getUsername()))
                                                .step(new ElementWaitStep<>(LOGIN_PASSWORD_INPUT_XPATH, Duration.of(3, ChronoUnit.SECONDS)))
                                                .step(new ElementWriteStep<>(LOGIN_PASSWORD_INPUT_XPATH, credentials.getPassword()))
                                                .step(new ElementWaitStep<>(LOGIN_BUTTON_XPATH, Duration.of(3, ChronoUnit.SECONDS)))
                                                .step(new ElementClickStep<>(LOGIN_BUTTON_XPATH))
                                                .delay(Duration.of(3, ChronoUnit.SECONDS))
                                                .step(new SeleniumDisconnectStep<>())
                                )
                )
                .build();


        workflow.execute();


    }

}
