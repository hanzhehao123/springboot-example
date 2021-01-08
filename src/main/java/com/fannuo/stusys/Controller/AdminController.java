package com.fannuo.stusys.Controller;

import com.fannuo.stusys.Entity.Admin;
import com.fannuo.stusys.Entity.Login;
import com.fannuo.stusys.Mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("adm")
public class AdminController {

    @Autowired
    private AdminMapper adminMapper;

    @GetMapping("/homepage")
    public String get_home_page(HttpSession session){
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }
        return "admin_homepage";

    }

    @GetMapping("/admin_add")
    private String admin_add_Page(HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_root()){
            return "admin_admin_add";
        }
        return "admin_homepage";
    }

    @GetMapping("/admin_info")
    public String admin_information_Page(HttpSession session,Model model){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_root()){
            List<Admin> lists = adminMapper.findAllAdm();
            model.addAttribute("lists",lists);
            return "admin_admin_information";
        }
        return "admin_homepage";
    }

    @GetMapping("/delAdm/{id}")
    public String deleteByID(@PathVariable("id") BigInteger admin_id,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_root()){
            Integer i = adminMapper.deleteByID(admin_id);

            return "redirect:/adm/admin_info";
        }
        return "admin_homepage";
    }



    @GetMapping("/update/{id}")
    public String updateByID(@PathVariable("id") BigInteger admin_id, Model model,HttpSession session){
        Admin admin2 = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin2.isAdmin_authority_root()){
            Admin admin = adminMapper.getAdmByID(admin_id);
            model.addAttribute("adm",admin);

            return "admin_admin_change";
        }
        return "admin_homepage";
    }

    @PostMapping("/update")
    public  String updateAdmin(Admin admin,HttpSession session){
        Admin admin2 = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin2.isAdmin_authority_root()){
            Integer i =adminMapper.updateByID(admin);
            return "redirect:/adm/admin_info";
        }
        return "admin_homepage";
    }

    @PostMapping("/admin_add")
    public String add_admin_info(Admin admin,HttpSession session){
        Admin admin2 = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin2.isAdmin_authority_root()){
            Integer i = adminMapper.addAdmin(admin);
            return "redirect:/adm/admin_info";
        }
        return "admin_homepage";
    }

    @GetMapping("/admin_password")
    public String get_manage_password(HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }
        return "admin_admin_password";
    }

    @PostMapping("/admin_password")
    public String update_user_password(@RequestParam("admin_password_new") String admin_password_new,@RequestParam("admin_password") String admin_password, HttpSession session){
        Admin admin = (Admin) session.getAttribute("adm");
        String old_password = adminMapper.old_password(admin.getAdmin_id());

        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else{
            if(admin_password.equals(old_password)){
                Integer update_password = adminMapper.update_password(admin.getAdmin_id(),admin_password_new);

                return "redirect:/adm/homepage";
            }
            return "redirect:/adm/admin_password";
        }

    }

    @PostMapping("/login")
    public String admin_login(@RequestParam("admin_id") String admin_id,
                              @RequestParam("admin_password") String admin_password,
                              HttpSession session, Login login,
                              Map<String,Object> map){
        Admin admin = adminMapper.adminlogin(admin_id,admin_password);
        if(admin==null){
            map.put("ac","用户名或密码错误");
            return "admin_login";
        }else{
            session.setAttribute("adm",admin);
            Long val = System.currentTimeMillis();
            BigInteger timestamp = new BigInteger(val.toString());
            login.setLogin_timestamp(timestamp);
            login.setLogin_type("管理员");
            login.setLogin_userid(admin.getAdmin_id());

            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            login.setLogin_datetime(dateFormat.format(date));

            Integer i = adminMapper.addLog(login);
            return "redirect:/adm/homepage";
        }
    }

    @PostMapping("/logout")
    public String admin_logout(HttpSession session){
        session.removeAttribute("adm");
        return "redirect:/adm/homepage";
    }

    @GetMapping("/admin_logindata")
    public String student_information_Page(@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,HttpSession session,Model model){
        Admin admin = (Admin) session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_root()){
            PageHelper.startPage(pageNum,pageSize);
            List<Login> lists = adminMapper.findAllLog();
            PageInfo<Login> pageInfo=new PageInfo<Login>(lists);
            model.addAttribute("lists",pageInfo);
            return "admin_admin_logindata";
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
