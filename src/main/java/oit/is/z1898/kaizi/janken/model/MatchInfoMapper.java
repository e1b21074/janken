package oit.is.z1898.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchInfoMapper {

    @Select("SELECT * from matchinfo where isactive = true")
    ArrayList<MatchInfo> selectActiveMatchinfo();

    @Select("SELECT * from matchinfo where (user1 = #{user} or user2 = #{user}) and isactive = true")
    MatchInfo selectByActiveUser(int user);

    @Insert("INSERT INTO matchinfo (user1, user2, user1Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{isActive})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertMatchinfo(MatchInfo matchinfo);
}
