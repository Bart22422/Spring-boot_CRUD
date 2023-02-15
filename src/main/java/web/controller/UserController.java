package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.repo.UserRep;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    UserRep userRep;
    @GetMapping("/")
    public String index (ModelMap map) {

        Iterable <User> users = userRep.findAll();
        map.addAttribute("users", users);

     return"index";
    }
    @PostMapping("/form")
    public String postUser (@RequestParam(value = "name") String name, @RequestParam(value = "lastName") String lastName, @RequestParam(value = "date") String date) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd");
        Date date1 = simpleDateFormat.parse(date);

        User user = new User();
        user.setName(name);
        user.setAge(date1);
        user.setLastName(lastName);

        userRep.save(user);
        return "redirect:/";
    }
    @PostMapping("/delete")
    public String postUser (@RequestParam(value = "id") long id)  {
       userRep.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/update")
    public String updateUser (@RequestParam(value = "id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "lastName") String lastName, @RequestParam(value = "date") String date) throws ParseException {
        User user = userRep.findAllById(id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd");
        Date date1 = simpleDateFormat.parse(date);
        user.setName(name);
        user.setAge(date1);
        user.setLastName(lastName);
        userRep.save(user);


        return "redirect:/";
    }
    @PostMapping("/clean")
    public String cleanTable(){
        userRep.deleteAll();
        return "redirect:/";
    }
}
