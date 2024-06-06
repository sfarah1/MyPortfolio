package com.baeldung.crud.repositories;

import com.baeldung.crud.DTO.ParticipationDTO;
import com.baeldung.crud.model.ParticipationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParticipationRepositoryJDBC implements ParticipationRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ParticipationRepositoryJDBC(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;
    }

    @Override
    public List<ParticipationDTO> findAllParticipation() {
        return jdbcTemplate.query(
                "select * from participation order by participation_id desc ",
                new ParticipationMapper());
    }

    @Override
    public int addParticipation(ParticipationDTO participationDTO) {
//        participationDTO.getParticipation_date()  时间
        /*
        sql 查询这个时间会不会超过指定时间
        return 0
         */
        String sql = "insert into participation(participation_id,user_name,event_id,participation_date) values(?,?,?,?)";
        return jdbcTemplate.update(sql,participationDTO.getParticipation_id(),
                participationDTO.getUser_name(),participationDTO.getEvent_id(),
                participationDTO.getParticipation_date());
/*
    添加的时候去查询数据库
    判断你participation_date 和另一个时间比较
    判断 -> 添加
     */
    }

    @Override
    public boolean deleteParticipation(int participation_id) {
        String sql = "delete from participation where participation_id=?";
        int rows =jdbcTemplate.update(sql,participation_id);
        return rows>0;
    }


}
