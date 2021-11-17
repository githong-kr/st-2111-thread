package com.bale.quiz.thread.initial;

import com.bale.quiz.thread.pojo.Key;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialRunner implements ApplicationRunner {

    private final Key key;

    public InitialRunner(Key key) {
        this.key = key;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Spring Boot Instance Start!!!");

        System.out.println("key : " + key.getKey());
    }
}
