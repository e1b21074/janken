package oit.is.z1898.kaizi.janken.model;



public class Janken {
    private String Player1;
    private String Player2 = "gu";
    private String Result = "";

    public Janken (String Player1) {
        this.Player1 = Player1;
    }

    public Janken (Match match) {
        this.Player1 = match.getUser1Hand();
        this.Player2 = match.getUser2Hand();
    }

    public String getPlayer1(){
        return this.Player1;
    }

    public String getPlayer2(){
        return this.Player2;
    }

    public String getResult(){
        jankenResult();
        return this.Result;
    }

    private void jankenResult (){
        switch (this.Player2) {
            case "gu":
                gu();
                return;
            case "choki":
                choki();
                return;
            case "pa":
                pa();
                return;
        }
    }

    private void gu(){
        switch (this.Player1) {
            case "gu":
            this.Result = "Draw";
            return;
            case "choki":
            this.Result = "You Lose";
            return;
            case "pa":
            this.Result = "You Win";
            return;
        }
    }

    private void choki(){
        switch (this.Player1) {
            case "gu":
            this.Result = "You Win";
            return;
            case "choki":
            this.Result = "Draw";
            return;
            case "pa":
            this.Result = "You Lose";
            return;
        }
    }

    private void pa(){
        switch (this.Player1) {
            case "gu":
            this.Result = "You Lose";
            return;
            case "choki":
            this.Result = "You Win";
            return;
            case "pa":
            this.Result = "Draw";
            return;
        }
    }
}
