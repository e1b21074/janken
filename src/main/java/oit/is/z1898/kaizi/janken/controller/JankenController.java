package oit.is.z1898.kaizi.janken.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z1898.kaizi.janken.model.*;
import oit.is.z1898.kaizi.janken.service.AsyncKekka;


@Controller
public class JankenController {

  private String loginUser;

  @Autowired
  private UserMapper usermapper;

  @Autowired
  private MatchMapper matchmapper;

  @Autowired
  private MatchInfoMapper matchinfomapper;

  @Autowired
  private AsyncKekka asyncKekka;

  @GetMapping("/janken")
  public String jankengame(Principal prin, ModelMap model){
    ArrayList<User> users = usermapper.selectAllUsers();
    ArrayList<Match> matches = matchmapper.selectByMatches();
    ArrayList<MatchInfo> matchinfos = matchinfomapper.selectActiveMatchinfo();
    this.loginUser = prin.getName();

    model.addAttribute("loginUser", this.loginUser);
    model.addAttribute("users", users);
    model.addAttribute("matchinfos", matchinfos);
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

  @GetMapping("/wait")
  public String wait(@RequestParam int id, @RequestParam String Player1, ModelMap model) {
    Match match = new Match();
    MatchInfo matchinfo = new MatchInfo();
    MatchInfo game = matchinfomapper.selectByActiveUser(usermapper.selectByName(this.loginUser).getId());

    if(game == null){
      matchinfo.setUser1(usermapper.selectByName(this.loginUser).getId());
      matchinfo.setUser2(id);
      matchinfo.setUser1Hand(Player1);
      matchinfo.setActive(true);
      matchinfomapper.insertMatchinfo(matchinfo);
    }else{
      match.setUser1(usermapper.selectByName(this.loginUser).getId());
      match.setUser2(id);
      match.setUser1Hand(Player1);
      match.setUser2Hand(game.getUser1Hand());
      match.setisActive(true);
      this.asyncKekka.insertMatchTable(match);
    }

    model.addAttribute("loginUser", this.loginUser);
    return "wait.html";
  }

  @GetMapping("/fight")
  public String game(@RequestParam int id, @RequestParam String Player1, ModelMap model) {
    Match match = new Match();
    Janken janken = new Janken(Player1);

    match.setUser1(usermapper.selectByName(this.loginUser).getId());
    match.setUser2(id);
    match.setUser1Hand(janken.getPlayer1());
    match.setUser2Hand(janken.getPlayer2());
    match.setisActive(false);
    matchmapper.insertMatch(match);

    model.addAttribute("loginUser", this.loginUser);
    model.addAttribute("id", match.getId());
    model.addAttribute("user", usermapper.selectbyId(id));
    model.addAttribute("Player1", janken.getPlayer1());
    model.addAttribute("Player2", janken.getPlayer2());
    model.addAttribute("Result", janken.getResult());
    return "match.html";
  }

  @GetMapping("/sse")
  public SseEmitter sse() throws IOException{
    final SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
    this.asyncKekka.gameresult(emitter);
    return emitter;
  }

}
