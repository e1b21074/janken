package oit.is.z1898.kaizi.janken.model;

import org.springframework.stereotype.Component;

@Component
public class Janken {
    String Player1;
    String Player2 = "gu";
    String Result = "";

    public void Janken (String Player1) {
        this.Player1 = Player1;
    }

    public void game(){
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
