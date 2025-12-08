package net.nussi.denki.login;

import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.common.CommonService;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private LoginConfig config;

    @Autowired
    private CommonService common;


    public void login(WebDriver driver, String username, String password) {

        log.info("Logging in with user {}", username);

        common.navigateToUrl(driver, config.getUrl());

        common.writeElement(driver, config.getUsername(), username);
        common.writeElement(driver, config.getPassword(), password);
        common.clickElement(driver, config.getSubmit());

    }

}
