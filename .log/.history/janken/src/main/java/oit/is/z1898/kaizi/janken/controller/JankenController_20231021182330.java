package oit.is.z1898.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1898.kaizi.janken.model.User;
import oit.is.z1898.kaizi.janken.model.UserMapper;
import oit.is.z1898.kaizi.janken.model.Match;
import oit.is.z1898.kaizi.janken.model.MatchMapper;

@Controller
public class JankenController {

  private String loginUser;

  @Autowired
  private UserMapper usermapper;

  @Autowired
  private MatchMapper matchmapper;

  @GetMapping("/janken")
  public String jankengame(Principal prin, ModelMap model){
    ArrayList<User> users = usermapper.selectAllUsers();
    ArrayList<Match> matches = matchmapper.selectByMatches();
    this.loginUser = prin.getName();
    model.addAttribute("loginUser", this.loginUser);
    model.addAttribute("users", users);
    model.addAttribute("matches", matches);
    return "janken.html";

  }

  @GetMapping("/match")
  public String match(@RequestParam int id, ModelMap model){
    User user = usermapper.selectbyId(id);
    model.addAttribute("loginUser", this.loginUser);
    model.addAttribute("user", user);
    return "match.html";
  }

  @GetMapping("/game")
  public String game(@RequestParam int id, @RequestParam String Player1, ModelMap model) {
    User user = usermapper.selectbyId(id);
    String Result = "";
    String Player2 = "gu";
    switch (Player1) {
      case "gu":
        Result = "Draw";
        break;
      case "choki":
        Result = "You Lose";
        break;
      case "pa":
        Result = "You Win";
        break;
    }
    model.addAttribute("loginUser", this.loginUser);
    model.addAttribute("user", user);
    model.addAttribute("Player1", Player1);
    model.addAttribute("Player2", Player2);
    model.addAttribute("Result", Result);
    return "match.html";
  }

}
