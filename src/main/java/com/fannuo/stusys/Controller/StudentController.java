package com.fannuo.stusys.Controller;

import com.fannuo.stusys.Entity.Admin;
import com.fannuo.stusys.Entity.Student;
import com.fannuo.stusys.Mapper.StudentMapper;
import com.fannuo.stusys.Utils.FileUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("stu")
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    /*@GetMapping("/all")
    public String getAllStudent(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize, Model model,HttpSession session){
        Student student = (Student)session.getAttribute("stu");
        if(session.getAttribute("stu")==null){
            return "redirect:/usr/login";
        }
        PageHelper.startPage(1,5);
        List<Student> lists = studentMapper.findAllStu();
        PageInfo<Student> pageInfo=new PageInfo<Student>(lists);

        model.addAttribute("lists",pageInfo);
        return "student_information";
    }*/

    @GetMapping("/homepage")
    public String get_home_page(HttpSession session){
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }
        return "admin_homepage";
    }

    @GetMapping("/student_info")
    public String student_information_Page(@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,HttpSession session,Model model){
        Admin admin = (Admin) session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_student()){
            PageHelper.startPage(pageNum,pageSize);
            List<Student> lists = studentMapper.findAllStu();
            PageInfo<Student> pageInfo=new PageInfo<Student>(lists);
            model.addAttribute("lists",pageInfo);
            return "admin_student_information";
        }
        return "admin_homepage";
    }

    @GetMapping("/student_add")
    public String student_add_Page(HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_student()){
            return "admin_student_add";
        }
        return "admin_homepage";
    }

    @PostMapping("/student_add")
    public String add_student_info(@RequestParam("file") MultipartFile file, Student student, HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_student()){
            String fileName = file.getOriginalFilename();
            String filePath = FileUtil.getUpLoadFilePath();
            fileName = System.currentTimeMillis()+fileName;
            try{
                FileUtil.uploadFile(file.getBytes(),filePath,fileName);
            } catch (Exception e){
                //TODO: handle exception
            }
            student.setStudent_img(fileName);

            Integer i = studentMapper.addStudent(student);
            return "redirect:/stu/student_info";
        }
        return "admin_homepage";
    }

    /*@GetMapping("/add")
    public String addStudent(){
        return "student_add";
    }


    @PostMapping("/add")
    public String addStuInfo(Student student){
        Integer i = studentMapper.addStudent(student);
        return "redirect:/stu/student_info";
    }*/

    @PostMapping("/register")
    public String register_StuInfo(Student student){
        Integer i = studentMapper.addStudent(student);
        return "redirect:/usr/login";
    }

    @GetMapping("/delStu/{id}")
    public String deleteByID(@PathVariable("id") BigInteger stuid,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_student()){
            Integer i = studentMapper.deleteByID(stuid);

            return "redirect:/stu/student_info";
        }
        return "admin_homepage";
    }



    @GetMapping("/update/{id}")
    public String updateByID(@PathVariable("id") BigInteger student_id,Model model,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_student()){
            Student student = studentMapper.getStuByID(student_id);
            model.addAttribute("stu",student);

            return "admin_student_change";
        }
        return "admin_homepage";
    }

    @PostMapping("/update")
    public  String updateStudent(@RequestParam("file") MultipartFile file,Student student,HttpSession session){
        Admin admin = (Admin)session.getAttribute("adm");
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }else if(admin.isAdmin_authority_student()){
            Student student2 = studentMapper.getStuByID(student.getStudent_id());
            String fileName = file.getOriginalFilename();
            if(fileName.length()<=0) {
                student.setStudent_img(student2.getStudent_img());
            }else {
                String filePath = FileUtil.getUpLoadFilePath();
                fileName = System.currentTimeMillis() + fileName;
                try {
                    FileUtil.uploadFile(file.getBytes(), filePath, fileName);
                } catch (Exception e) {
                    //TODO: handle exception
                }
                student.setStudent_img(fileName);
            }
            Integer i =studentMapper.updateByID(student);
            return "redirect:/stu/student_info";
        }
        return "admin_homepage";
    }

    @GetMapping("/blank")
    public String blankPage(Model model,HttpSession session){
        if(session.getAttribute("adm")==null){
            return "redirect:/adm/login";
        }
        List<Student> lists = studentMapper.findAllStu();
        model.addAttribute("lists",lists);
        return "blank";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "redirect:/adm/login";
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
