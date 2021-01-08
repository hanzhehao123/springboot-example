package com.fannuo.stusys.Controller;

import com.fannuo.stusys.Entity.Admin;
import com.fannuo.stusys.Entity.Team;
import com.fannuo.stusys.Mapper.TeamMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("team")
public class TeamController {

    @Autowired
    private TeamMapper teamMapper;

    @GetMapping("/homepage")
    public String get_home_page(HttpSession session){
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }
        return "admin_homepage";
    }


    @GetMapping("/team_add")
    private String team_add_Page(HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_team()){
            return "admin_team_add";
        }
        return "admin_homepage";
    }

    @GetMapping("/team_info")
    public String team_information_Page(@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "6") Integer pageSize, HttpSession session, Model model){
        Admin admin = (Admin) session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_team()){
            PageHelper.startPage(pageNum,pageSize);
            List<Team> lists = teamMapper.findAllTeam();
            PageInfo<Team> pageInfo=new PageInfo<Team>(lists);
            model.addAttribute("lists",pageInfo);
            return "admin_team_information";
        }
        return "admin_homepage";
    }

    @GetMapping("/delTeam/{id}")
    public String deleteByID(@PathVariable("id") BigInteger team_id,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_team()){
            Integer i = teamMapper.deleteByID(team_id);

            return "redirect:/team/team_info";
        }
        return "admin_homepage";
    }



    @GetMapping("/update/{id}")
    public String updateByID(@PathVariable("id") BigInteger team_id, Model model,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_team()){
            Team team = teamMapper.getTeamByID(team_id);
            model.addAttribute("team",team);

            return "admin_team_change";
        }
        return "admin_homepage";
    }

    @PostMapping("/update")
    public  String updateTeam(Team team,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_team()){
            Integer i =teamMapper.updateByID(team);
            return "redirect:/team/team_info";
        }
        return "admin_homepage";
    }

    @PostMapping("/team_add")
    public String add_team_info(Team team,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_team()){
            Integer i = teamMapper.addTeam(team);
            return "redirect:/team/team_info";
        }
        return "admin_homepage";
    }














    @GetMapping("/blank")
    public String blankPage(Model model,HttpSession session){
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }
        return "blank";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "admin_login";
    }

    /*@GetMapping("/register")
    public String admin_register_Page(){
        return "admin_register";
    }*/

    @GetMapping("/logout")
    public String logoutPage(HttpSession session){
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }
        return "admin_logout";
    }

    @GetMapping("/settings")
    public String admin_settings_Page(HttpSession session){
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }
        return "admin_settings";
    }

    @GetMapping("/404")
    public String admin_404_Page(){
        return "admin_404";
    }

    @GetMapping("/500")
    public String admin_500_Page(){
        return "admin_500";
    }




}
