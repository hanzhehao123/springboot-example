package com.fannuo.stusys.Controller;

import com.fannuo.stusys.Entity.Admin;
import com.fannuo.stusys.Entity.Project;
import com.fannuo.stusys.Mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("proj")
public class ProjectController {

    @Autowired
    private ProjectMapper projectMapper;

    @GetMapping("/homepage")
    public String get_home_page(HttpSession session){
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }
        return "admin_homepage";
    }

    @GetMapping("/project_add")
    private String project_add_Page(HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_project()){
            return "admin_project_add";
        }
        return "admin_homepage";
    }

    @GetMapping("/project_info")
    public String project_information_Page(Model model,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_project()){
            List<Project> lists = projectMapper.findAllProj();
            model.addAttribute("lists",lists);
            return "admin_project_information";
        }
        return "admin_homepage";
    }

    @GetMapping("/delProj/{id}")
    public String deleteByID(@PathVariable("id") BigInteger project_id,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_project()){
            Integer i = projectMapper.deleteByID(project_id);

            return "redirect:/proj/project_info";
        }
        return "admin_homepage";
    }



    @GetMapping("/update/{id}")
    public String updateByID(@PathVariable("id") BigInteger project_id, Model model,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_project()){
            Project project = projectMapper.getProjByID(project_id);
            model.addAttribute("proj",project);

            return "admin_project_change";
        }
        return "admin_homepage";
    }

    @PostMapping("/update")
    public  String updateProject(Project project,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_project()){
            Integer i =projectMapper.updateByID(project);
            return "redirect:/proj/project_info";
        }
        return "admin_homepage";
    }

    @PostMapping("/project_add")
    public String add_project_info(Project project,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_project()){
            Integer i = projectMapper.addProject(project);
            return "redirect:/proj/project_info";
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
