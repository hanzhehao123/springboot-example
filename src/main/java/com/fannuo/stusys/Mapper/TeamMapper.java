package com.fannuo.stusys.Mapper;

import com.fannuo.stusys.Entity.Team;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface TeamMapper {

    @Select("select * from test.team_info where team_id=#{team_id}")
    Team getTeamByID(BigInteger team_id);

    @Select("SELECT * FROM team_info")
    List<Team> findAllTeam();

    @Select("SELECT * FROM team_info where team_member_id like #{student_id} AND team_progress = 100")
    List<Team> findAllTeambyID(String student_id);

    @Select("SELECT * FROM team_info WHERE team_project=#{project_name}")
    List<Team> findsomeTeam(String project_name);

    @Select("select * from team_info where team_member_id like #{student_id} AND team_progress < 100")
    Team findoneTeam(String student_id);

    @Insert("insert into team_info (team_id,team_name,team_project,team_leader,team_mate,team_intro,team_progress,team_grade1,team_grade2,team_grade3,team_grade,team_member_id)  values(#{team_id},#{team_name},#{team_project},#{team_leader},#{team_mate},#{team_intro},#{team_progress},#{team_grade1},#{team_grade2},#{team_grade3},#{team_grade},#{team_member_id})")
    Integer addTeam(Team team);

    @Delete("delete from team_info where team_id=#{team_id}")
    Integer deleteByID(BigInteger team_id);

    @Update("update team_info set team_name=#{team_name},team_project=#{team_project},team_leader=#{team_leader},team_mate=#{team_mate},team_intro=#{team_intro},team_progress=#{team_progress},team_grade1=#{team_grade1},team_grade2=#{team_grade2},team_grade3=#{team_grade3},team_grade=#{team_grade},team_member_id=#{team_member_id} where team_id=#{team_id}")
    Integer updateByID(Team team);

    @Update("update team_info set team_member_id=concat(team_member_id,',',#{student_id}),team_mate=concat(team_mate,',',#{student_name}) where team_id=#{team_id} AND team_progress < 100")
    Integer joinTeam(BigInteger student_id,String student_name,BigInteger team_id);

}
