package oit.is.z1898.kaizi.janken.model;

import org.springframework.stereotype.Component;

@Component
public class Janken {
    String Player1;
    String Player2;
    String Result = "";

    public void janken (String Player1) {
        this.Player1 = Player1;
    }

    public void game(){
        this.Player2 = "gu";
        switch (this.Player1) {
            case "gu":
            this.Result = "Draw";
            break;
            case "choki":
            this.Result = "You Lose";
            break;
            case "pa":
            this.Result = "You Win";
            break;
        }
    }
}
