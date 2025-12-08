package net.nussi.denki.temp;

import jakarta.annotation.PostConstruct;
import net.nussi.denki.login.LoginService;
import net.nussi.denki.quiz.QuizConfig;
import net.nussi.denki.quiz.QuizService;
import net.nussi.denki.selenium.SeleniumService;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempService {

    @Autowired
    private TempConfig config;

    @Autowired
    private SeleniumService selenium;

    @Autowired
    private LoginService login;

    @Autowired
    private QuizService quiz;

    @Autowired
    private QuizConfig quizConfig;

    @PostConstruct
    public void init() throws InterruptedException {

        WebDriver driver = selenium.getDriver();

        login.login(driver, config.getUsername(), config.getPassword());

        quiz.solve(driver, quizConfig.getQuizzes().getFirst());

        Thread.sleep(10000);

        driver.quit();
    }

}
