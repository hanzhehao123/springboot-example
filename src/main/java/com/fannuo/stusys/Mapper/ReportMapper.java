package com.fannuo.stusys.Mapper;

import com.fannuo.stusys.Entity.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ReportMapper {

    @Select("select * from report_info where report_student_id=#{report_student_id}")
    List<Report> findReportByID(BigInteger report_student_id);

    @Select("select * from report_info where report_id=#{report_id}")
    Report findReportByReportID(BigInteger report_id);

    @Select("select * from report_info where report_id=#{report_id}")
    Report view_report(BigInteger report_id);

    @Select("select * from report_info")
    List<Report> findAllReport();

    @Insert("insert into report_info (report_id,report_title,report_project_name,report_team_id,report_student_id,report_student_name,report_date,report_content,report_type)  values(#{report_id},#{report_title},#{report_project_name},#{report_team_id},#{report_student_id},#{report_student_name},#{report_date},#{report_content},#{report_type})")
    Integer addNewReport(Report report);

    @Update("update report_info set report_type=#{report_type},report_title=#{report_title},report_content=#{report_content},report_state=#{report_state} where report_id=#{report_id}")
    Integer updateByID(Report report);
}
