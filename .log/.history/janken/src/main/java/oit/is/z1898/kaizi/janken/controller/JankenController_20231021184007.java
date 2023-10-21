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
import oit.is.z1898.kaizi.janken.model.Janken;

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

  @GetMapping("/fight")
  public String game(@RequestParam int id, @RequestParam String Player1, ModelMap model) {
    Match match = new Match();
    User user = usermapper.selectbyId(id);
    Janken janken = new Janken(Player1);
    janken.game();
    model.addAttribute("loginUser", this.loginUser);
    model.addAttribute("user", user);
    model.addAttribute("Player1", janken.Player1);
    model.addAttribute("Player2", janken.Player2);
    model.addAttribute("Result", janken.Result);
    return "match.html";
  }

}
