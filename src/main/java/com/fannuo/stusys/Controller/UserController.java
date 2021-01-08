package com.fannuo.stusys.Controller;

import com.fannuo.stusys.Entity.*;
import com.fannuo.stusys.Mapper.*;
import com.fannuo.stusys.Utils.FileUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.jdi.event.StepEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("usr")
public class UserController {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/homepage")
    public String get_userhomepage(HttpSession session){
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }
        return "user_homepage";
    }

    @GetMapping("/admin_homepage")
    public String get_admin_homepage(){
        return "redirect:/stu/homepage";
    }

    @GetMapping("/manage_logout")
    public String get_manage_logout(HttpSession session){
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }
        return "user_manage_logout";
    }

    @GetMapping("/manage_password")
    public String get_manage_password(HttpSession session){
        Student student = (Student)session.getAttribute("stu");
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }
        return "user_manage_password";
    }

    @PostMapping("/manage_password")
    public String update_user_password(@RequestParam("student_password_new") String student_password_new,
                                       @RequestParam("password") String password,
                                       @RequestParam("student_password_new1") String student_password_new1,
                                       HttpSession session,
                                       Map<String,Object> map){
        Student student = (Student)session.getAttribute("stu");
        String old_password = studentMapper.old_password(student.getStudent_id());

        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }else{
            if(student_password_new.equals(student_password_new1)){

                if(password.equals(old_password)){
                    Integer update_password = studentMapper.update_password(student.getStudent_id(),student_password_new);
                    return "redirect:/usr/homepage";
                }else {
                    map.put("bc","原密码错误");
                    return "user_manage_password";
                }
            }else {
                map.put("ac","两次密码不一致");
                return "user_manage_password";
            }
        }
    }

    @GetMapping("/manage_userdata")
    public String get_manage_userdata(HttpSession session,Model model){
        Student student = (Student)session.getAttribute("stu");
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }
        Student onestudent = studentMapper.findoneStu(student.getStudent_id());
        model.addAttribute("stu",onestudent);
        return "user_manage_userdata";
    }

    @PostMapping("/manage_userdata")
    public  String updateStudent(@RequestParam("file") MultipartFile file, Student student,HttpSession session){
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }else {
            String fileName = file.getOriginalFilename();
            String filePath = FileUtil.getUpLoadFilePath();
            fileName = System.currentTimeMillis()+fileName;
            try{
                FileUtil.uploadFile(file.getBytes(),filePath,fileName);
            } catch (Exception e){
                //TODO: handle exception
            }
            student.setStudent_img(fileName);
            Integer i =studentMapper.updateByID(student);
            return "redirect:/usr/homepage";
        }

    }

    @GetMapping("/my_grade")
    public String get_my_grade(HttpSession session,Model model){
        Student student = (Student)session.getAttribute("stu");
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }else {
            String student_id = String.valueOf(student.getStudent_id()) ;
            student_id = "%" + student_id + "%";
            Team team = teamMapper.findoneTeam(student_id);
            if(team != null){
                model.addAttribute("team",team);

                return "user_my_grade";
            }
            return "redirect:/usr/project_info";
        }
    }

    @GetMapping("/my_grade_old")
    public String get_my_grade_old(HttpSession session,Model model){
        Student student = (Student)session.getAttribute("stu");
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }else {
            String student_id = String.valueOf(student.getStudent_id()) ;
            student_id = "%" + student_id + "%";

            List<Team> lists = teamMapper.findAllTeambyID(student_id);
            model.addAttribute("lists",lists);
            return "user_my_grade_old";
        }

    }

    @GetMapping("/my_project")
    public String get_my_project(HttpSession session,Model model){
        Student student = (Student)session.getAttribute("stu");
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }else {
            String student_id = String.valueOf(student.getStudent_id()) ;
            student_id = "%" + student_id + "%";
            Team team = teamMapper.findoneTeam(student_id);
            if(team != null){
                model.addAttribute("team",team);

                return "user_my_project";
            }
            return "redirect:/usr/project_info";
        }

    }

    @GetMapping("/report")
    public String get_report(HttpSession session,Model model){
        Student student = (Student)session.getAttribute("stu");
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }else {
            List<Report> lists = reportMapper.findReportByID(student.getStudent_id());
            model.addAttribute("lists",lists);
            return "user_report";
            }
    }

    @GetMapping("/report_new")
    public String get_report_new(HttpSession session,Model model){
        Student student = (Student)session.getAttribute("stu");
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }else {
            String student_id = String.valueOf(student.getStudent_id()) ;
            student_id = "%" + student_id + "%";
            Team team = teamMapper.findoneTeam(student_id);
            model.addAttribute("team",team);
            return "user_report_new";
        }
    }

    @PostMapping("/report_new")
    public String add_newreport( Report report,HttpSession session){
        Student student = (Student)session.getAttribute("stu");
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }else {
            Long val = System.currentTimeMillis();
            BigInteger idbytime = new BigInteger(val.toString());
            report.setReport_id(idbytime);

            String student_id = String.valueOf(student.getStudent_id()) ;
            student_id = "%" + student_id + "%";
            Team team = teamMapper.findoneTeam(student_id);
            report.setReport_project_name(team.getTeam_project());
            report.setReport_team_id(team.getTeam_id());
            report.setReport_student_id(student.getStudent_id());
            report.setReport_student_name(student.getStudent_name());

            Integer i =reportMapper.addNewReport(report);
            return "redirect:/usr/homepage";
        }

    }

    @GetMapping("/viewReport/{report_id}")
    public String viewReport(@PathVariable("report_id") BigInteger report_id,Model model,HttpSession session){
        Student student = (Student)session.getAttribute("stu");
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }else {
            Report report = reportMapper.view_report(report_id);
            model.addAttribute("Report",report);
            return "user_report_view";

        }
    }

    @GetMapping("/project_info")
    public String project_information_Page(HttpSession session,Model model){
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }
        List<Project> lists = projectMapper.findAllProj();
        model.addAttribute("lists",lists);
        return "user_project_info";
    }

    @GetMapping("/team_info")
    public String team_information_Page(@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "6") Integer pageSize,HttpSession session,Model model){

        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Team> lists = teamMapper.findAllTeam();
        PageInfo<Team> pageInfo=new PageInfo<Team>(lists);
        model.addAttribute("lists",pageInfo);
        return "user_team_info";
    }

    @GetMapping("/joinTeam/{team_id}")
    public String updateByID(@PathVariable("team_id") BigInteger team_id,Model model,HttpSession session){
        Student student = (Student)session.getAttribute("stu");
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }else {
            String student_id = String.valueOf(student.getStudent_id()) ;
            student_id = "%" + student_id + "%";
            Team team = teamMapper.findoneTeam(student_id);
            if(team == null){
                Integer i = teamMapper.joinTeam(student.getStudent_id(),student.getStudent_name(),team_id);
                return "redirect:/usr/team_info";
            }else return "redirect:/usr/team_info";

        }

    }

    @GetMapping("/team_infobypro/{project_name}")
    public String team_informationbypro_Page(@PathVariable("project_name") String project_name, HttpSession session,Model model){
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }
        List<Team> lists = teamMapper.findsomeTeam(project_name);
        model.addAttribute("lists",lists);
        return "user_team_infobypro";
    }


    /*@GetMapping("/joinTeam")
    public String joinByID(HttpSession session, BigInteger team_id){
        Student student = (Student)session.getAttribute("stu");
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }
        Integer i = teamMapper.joinTeam(student.getStudent_id(),team_id);

        return "redirect:/user/user_team_info";
    }*/

    @PostMapping("/deletestudent")
    public String deleteonestudent(HttpSession session){
        Student student = (Student)session.getAttribute("stu");
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }else{
            Integer i = studentMapper.deleteByID(student.getStudent_id());
            return "redirect:/usr/login";
        }
    }

    @GetMapping("/blank")
    public String blankPage(HttpSession session,Model model){
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }
        return "blank";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String user_login(@RequestParam("student_id") String student_id,
                             @RequestParam("password") String password,
                             HttpSession session, Login login,
                             Map<String,Object> map){
        Student student = studentMapper.userlogin(student_id,password);
        if(student==null){
            map.put("ac","用户名或密码错误");
            return "login";
        }else{
            session.setAttribute("stu",student);
            Long val = System.currentTimeMillis();
            BigInteger timestamp = new BigInteger(val.toString());
            login.setLogin_timestamp(timestamp);
            login.setLogin_type("学生");
            login.setLogin_userid(student.getStudent_id());

            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            login.setLogin_datetime(dateFormat.format(date));

            Integer i = studentMapper.addLog(login);
            return "redirect:/usr/homepage";
        }
    }

    @PostMapping("/logout")
    public String user_logout(HttpSession session){
        session.removeAttribute("stu");
        return "redirect:/usr/homepage";
    }

    @GetMapping("/register")
    public String admin_register_Page(){
        return "register";
    }

    @GetMapping("/settings")
    public String admin_settings_Page(HttpSession session){
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
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

