package oit.is.z1898.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper {

    @Select("SELECT * from matches")
    ArrayList<Match> selectByMatches();

    @Insert("INSERT INTO matches (player1, player2, result) VALUES (#{player1}, #{player2}, #{result})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertMatch(Match match);

}