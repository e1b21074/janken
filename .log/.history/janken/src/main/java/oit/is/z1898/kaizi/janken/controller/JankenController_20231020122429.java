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

@Controller
public class JankenController {

  @Autowired
  private Entry entry;

  private String loginUser;

  // @GetMapping("/janken")
  // public String janken() {
  //   return "janken.html";
  // }

  @PostMapping("/janken")
  public String janken(@RequestParam String name, ModelMap model) {
    model.addAttribute("name", name);
    return "janken.html";
  }

  @GetMapping("/janken")
  public String jankengame(Principal prin, ModelMap model){
    this.loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("loginUser", this.loginUser);
    model.addAttribute("users", this.entry.getUsers());
    return "janken.html";

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
    model.addAttribute("users", this.entry.getUsers());
    model.addAttribute("Playerhand", Playerhand);
    model.addAttribute("Cpuhand", Cpuhand);
    model.addAttribute("Result", Result);
    return "janken.html";
  }

}
