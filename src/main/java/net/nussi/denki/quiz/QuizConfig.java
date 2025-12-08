package net.nussi.denki.quiz;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "net.nussi.denki.quiz")
@Getter
@Setter
public class QuizConfig {

    private List<Quiz> quizzes;


    @Getter
    @Setter
    @Configuration
    public static class Quiz {
        private String name;
        private String url;
    }

}
