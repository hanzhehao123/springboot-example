package com.fannuo.stusys.Mapper;

import com.fannuo.stusys.Entity.Project;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ProjectMapper {

    @Select(("select * from project_info where project_id=#{project_id}"))
    Project getProjByID(BigInteger project_id);

    @Select("SELECT * FROM project_info")
    List<Project> findAllProj();

    @Insert("insert into project_info (project_id,project_type,project_name,project_intro,project_state,project_startdate,project_enddate)  values(#{project_id},#{project_type},#{project_name},#{project_intro},#{project_state},#{project_startdate},#{project_enddate})")
    Integer addProject(Project project);

    @Delete("delete from project_info where project_id=#{project_id}")
    Integer deleteByID(BigInteger project_id);

    @Update("update project_info set project_type=#{project_type},project_name=#{project_name},project_intro=#{project_intro},project_state=#{project_state} where project_id=#{project_id}")
    Integer updateByID(Project project);


}
