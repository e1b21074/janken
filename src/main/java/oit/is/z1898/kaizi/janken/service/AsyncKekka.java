package oit.is.z1898.kaizi.janken.service;

import oit.is.z1898.kaizi.janken.model.*;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class AsyncKekka {
    private boolean dbUpdate = false;

    @Autowired
    private MatchMapper matchmapper;
    @Autowired
    private MatchInfoMapper matchinfomapper;

    public void insertMatchTable(Match match) {
        matchmapper.insertMatch(match);
        this.dbUpdate = true;
    }

    @Async
    public void gameresult(SseEmitter emitter) {
        try {
            while (true) {
                if (!this.dbUpdate) {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    continue;
                }
                Match match = matchmapper.selectActiveMatch();
                Janken janken = new Janken(match);
                emitter.send(janken);

                MatchInfo activeMatch = matchinfomapper.selectByActiveUser(match.getUser1());
                activeMatch.setActive(false);
                matchinfomapper.updateMatchinfo(activeMatch);

                TimeUnit.MILLISECONDS.sleep(1000);
                this.dbUpdate = false;
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getClass().getName() + ":" + e.getMessage());
        } finally {
            emitter.complete();
        }
    }
}
