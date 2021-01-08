package com.fannuo.stusys.Controller;

import com.fannuo.stusys.Entity.Admin;
import com.fannuo.stusys.Entity.Report;
import com.fannuo.stusys.Entity.Team;
import com.fannuo.stusys.Mapper.ReportMapper;
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
@RequestMapping("rep")
public class ReportController {

    @Autowired
    private ReportMapper reportMapper;

    @GetMapping("/homepage")
    public String get_home_page(HttpSession session){
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }
        return "admin_homepage";
    }

    @GetMapping("/report_info")
    public String team_information_Page(@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize, HttpSession session, Model model){
        Admin admin = (Admin) session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_student()){
            PageHelper.startPage(pageNum,pageSize);
            List<Report> lists = reportMapper.findAllReport();
            PageInfo<Report> pageInfo=new PageInfo<Report>(lists);
            model.addAttribute("lists",pageInfo);
            return "admin_report_information";
        }
        return "admin_homepage";
    }

    /*@GetMapping("/delReport/{report_id}")
    public String deleteByID(@PathVariable("report_id") BigInteger report_id, HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_team()){
            Integer i = teamMapper.deleteByID(team_id);

            return "redirect:/rep/report_info";
        }
        return "admin_homepage";
    }*/



    @GetMapping("/update/{report_id}")
    public String updateByID(@PathVariable("report_id") BigInteger report_id, Model model,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_student()){
            Report report = reportMapper.findReportByReportID(report_id);
            model.addAttribute("report",report);
            return "admin_report_change";
        }
        return "admin_homepage";
    }

    @PostMapping("/update")
    public  String updateTeam(Report report,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_student()){
            Integer i =reportMapper.updateByID(report);
            return "redirect:/rep/report_info";
        }
        return "admin_homepage";
    }








    @GetMapping("/blank")
    public String blankPage(Model model, HttpSession session){
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
