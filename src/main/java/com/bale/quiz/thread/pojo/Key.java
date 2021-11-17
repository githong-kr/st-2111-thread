package com.bale.quiz.thread.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Key {
    private String key;
    private Boolean isKeyValid;

    public Key() {
        key = "cocoa";
        isKeyValid = true;
    }

    /*
    * 키관리서버와 통신을 대체한다.
    * 1초 후 새로운 키값을 받아오고, 키가 유효하다는 플래그를 세운다.
    * */
    public String getNewKey() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.key = "cocoa" + (System.currentTimeMillis() % 10000) / 1000;
        this.isKeyValid = true;

        return this.key;
    }

    /*
    * 현재 키가 유효한지 체크하는 기능을 대체한다.
    * 3초마다 키가 유효하지 않다는 플래그를 세운다.
    * */
    public Boolean getIsKeyValid() {

        long seconds = (System.currentTimeMillis() % 10000) / 1000;

        if(seconds % 3 == 0) this.isKeyValid = false;

        return this.isKeyValid;
    }
}
