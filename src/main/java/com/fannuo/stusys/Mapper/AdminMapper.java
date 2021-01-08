package com.fannuo.stusys.Mapper;

import com.fannuo.stusys.Entity.Admin;
import com.fannuo.stusys.Entity.Login;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface AdminMapper {

    @Select(("select * from test.admin_info where admin_id=#{admin_id}"))
    Admin getAdmByID(BigInteger admin_id);

    @Select("SELECT * FROM admin_info")
    List<Admin> findAllAdm();

    @Select("select admin_password from admin_info where admin_id=#{admin_id}")
    String old_password(BigInteger admin_id);

    @Select("SELECT * FROM login_info")
    List<Login> findAllLog();

    @Select("select * from admin_info where admin_id=#{admin_id} and admin_password=#{admin_password}")
    Admin adminlogin(String admin_id,String admin_password);

    @Insert("insert into admin_info (admin_id,admin_username,admin_password,admin_authority_student,admin_authority_project,admin_authority_team,admin_authority_root,admin_note)  values(#{admin_id},#{admin_username},#{admin_password},#{admin_authority_student},#{admin_authority_project},#{admin_authority_team},#{admin_authority_root},#{admin_note})")
    Integer addAdmin(Admin admin);

    @Insert("insert into login_info (login_timestamp,login_type,login_userid,login_datetime) values(#{login_timestamp},#{login_type},#{login_userid},#{login_datetime})")
    Integer addLog(Login login);

    @Delete("delete from admin_info where admin_id=#{admin_id}")
    Integer deleteByID(BigInteger admin_id);

    @Update("update admin_info set admin_username=#{admin_username},admin_password=#{admin_password},admin_authority_student=#{admin_authority_student},admin_authority_project=#{admin_authority_project},admin_authority_team=#{admin_authority_team},admin_authority_root=#{admin_authority_root},admin_note=#{admin_note} where admin_id=#{admin_id}")
    Integer updateByID(Admin admin);

    @Update("update admin_info set admin_password=#{admin_password_new} where admin_id=#{admin_id}")
    Integer update_password(BigInteger admin_id,String admin_password_new);

}
