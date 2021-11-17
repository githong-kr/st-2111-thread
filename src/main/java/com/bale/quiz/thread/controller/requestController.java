package com.bale.quiz.thread.controller;

import com.bale.quiz.thread.pojo.Key;
import com.bale.quiz.thread.pojo.Lock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class requestController {

    private Key key;
    private Lock lock;

    public requestController(Key key, Lock lock) {
        this.key = key;
        this.lock = lock;
    }

    @GetMapping("/")
    public String getResponse(HttpServletRequest request) {
        System.out.println("Request Start!!!");

        /*
        * 현재의 키가 유효하다면, 현재의 키로 REQUEST 의 IP 주소를 암호화한다.
        * IP 주소 뒤에 키를 붙임으로써 암호화가 되었다고 가정한다.
        * */
        if( key.getIsKeyValid() ) {
            System.out.println("cocoa test IP Address : " + request.getRemoteAddr() + " ( " + key.getKey() + " )");
        } else {
            /*
            * 현재의 키가 유효하지 않고 락이 잡혀있지 않다면,
            * 새로운 키를 얻기 위해 별도 쓰레드를 생성하여 키관리서버와 통신한다.
            * 새로운 키를 얻기 전후로 락을 잡는다.
            * */
            if(!lock.getIsLock()) {
                new Thread(() -> {
                    System.out.println("Thread Start!!!");
                    lock.setIsLock(true);
                    /*
                    * 쓰레드 바깥의 Request 의 IP 주소가 안 가져와지나..?
                    * 왜 잘 되지.. 내가 뭘 잘못 이해했나?
                    * */
                    System.out.println("cocoa test IP Address : " + request.getRemoteAddr() + " ( Thread )");
                    System.out.println("new Key : " + key.getNewKey());
                    lock.setIsLock(false);
                    System.out.println("Thread End!!!");
                }).start();
            }

            /*
            * 현재 키가 유효하지 않은 상황이기 때문에
            * IP 주소는 평문으로 로깅한다.
            * */
            System.out.println("cocoa test IP Address : " + request.getRemoteAddr());
        }

        System.out.println("Request End!!!");

        return "SUCCESS!!";
    }
}
