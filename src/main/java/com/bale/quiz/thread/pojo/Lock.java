package com.bale.quiz.thread.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Lock {
    private Boolean isLock;

    public Lock() {
        this.isLock = false;
    }

    public synchronized void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }
}
