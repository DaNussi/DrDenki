package net.nussi.denki.quiz;

import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.common.CommonService;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QuizService {


    @Autowired
    private CommonService common;

    public void solve(WebDriver driver, QuizConfig.Quiz quiz) {
        log.info("Solving " + quiz.getName());

        common.navigateToUrl(driver, quiz.getUrl());

    }
}
