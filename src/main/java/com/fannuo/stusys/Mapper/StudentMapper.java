package com.fannuo.stusys.Mapper;

import com.fannuo.stusys.Entity.Login;
import com.fannuo.stusys.Entity.Student;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface StudentMapper {

    @Select(("select * from test.student_info where student_id=#{stuid}"))
    Student getStuByID(BigInteger stuid);

    @Select("select password from student_info where student_id=#{student_id}")
    String old_password(BigInteger student_id);

    @Select("SELECT * FROM student_info")
    List<Student> findAllStu();

    @Select("select * from student_info where student_id=#{student_id}")
    Student findoneStu(BigInteger stuid);

    @Select("select * from student_info where student_id=#{student_id} and password=#{password}")
    Student userlogin(String student_id,String password);

    @Insert("insert into student_info (student_id,student_name,student_username,college,student_class,sex,password,student_email,student_phone,student_address,student_note,student_img)  values(#{student_id},#{student_name},#{student_username},#{college},#{student_class},#{sex},#{password},#{student_email},#{student_phone},#{student_address},#{student_note},#{student_img})")
    Integer addStudent(Student student);

    @Insert("insert into login_info (login_timestamp,login_type,login_userid,login_datetime) values(#{login_timestamp},#{login_type},#{login_userid},#{login_datetime})")
    Integer addLog(Login login);

    @Delete("delete from student_info where student_id=#{id}")
    Integer deleteByID(BigInteger id);

    @Update("update student_info set student_name=#{student_name},college=#{college},student_class=#{student_class},sex=#{sex},password=#{password},student_email=#{student_email},student_phone=#{student_phone},student_address=#{student_address},student_note=#{student_note},student_img=#{student_img} where student_id=#{student_id}")
    Integer updateByID(Student student);

    @Update("update student_info set password=#{student_password_new} where student_id=#{student_id}")
    Integer update_password(BigInteger student_id,String student_password_new);
}
