package oit.is.z1898.kaizi.janken.model;

public class Janken {
    private String Player1;
    private String Player2 = "gu";
    private String Result = "";

    public Janken (String Player1) {
        this.Player1 = Player1;
    }

    public String getPlayer1(){
        return this.Player1;
    }

    public String getPlayer2(){
        return this.Player2;
    }

    public String getResult(){
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
        return this.Result;
    }
}
