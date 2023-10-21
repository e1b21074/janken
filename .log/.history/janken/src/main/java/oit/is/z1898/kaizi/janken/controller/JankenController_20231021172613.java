package oit.is.z1898.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1898.kaizi.janken.model.Entry;
import oit.is.z1898.kaizi.janken.model.User;
import oit.is.z1898.kaizi.janken.model.UserMapper;
import oit.is.z1898.kaizi.janken.model.Match;
import oit.is.z1898.kaizi.janken.model.MatchMapper;

@Controller
public class JankenController {

  @Autowired
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
  public String match(@RequestParam String id, ModelMap model){
    User opp = usermapper.selectById(id);
    model.addAttribute("opp", opp);
    return "match.html";
  }

  @GetMapping("/jankengame")
  public String gu(@RequestParam String Playerhand, ModelMap model) {
    String Result = "";
    String Cpuhand = "gu";
    switch (Playerhand) {
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
    model.addAttribute("Playerhand", Playerhand);
    model.addAttribute("Cpuhand", Cpuhand);
    model.addAttribute("Result", Result);
    return "janken.html";
  }

}
