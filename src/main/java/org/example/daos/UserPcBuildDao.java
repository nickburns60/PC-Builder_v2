package org.example.daos;

import org.apache.commons.dbcp2.BasicDataSource;
import org.example.DaoException;
import org.example.models.UserPcBuild;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class UserPcBuildDao {
    private JdbcTemplate jdbcTemplate;

    public UserPcBuildDao(BasicDataSource basicDataSource){
        jdbcTemplate = new JdbcTemplate(basicDataSource);
    }

    public UserPcBuild getUserPcBuildByPcId(int id){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from user_pc_build where pc_id = ?;", id);
        if(results.next()){
            return mapRowToUserPcBuild(results);
        }
        return null;
    }
    public List<UserPcBuild> getAllUserPcBuilds(){
        List<UserPcBuild> userPcBuilds = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from user_pc_build;");
        while (results.next()){
            userPcBuilds.add(mapRowToUserPcBuild(results));
        }
        return userPcBuilds;
    }

    public UserPcBuild createUserPcBuild(UserPcBuild newBuild){
        try{
            int pcId = jdbcTemplate.queryForObject("insert into user_pc_build (processor_id, graphics_card_id, " +
                            "motherboard_id, ram_id, psu_id, storage_drive_id, case_id, fan_id, cpu_cooler_id) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?) returning pc_id;", int.class, newBuild.getProcessorId(), newBuild.getGraphicsCardId(),
                    newBuild.getMotherboardId(), newBuild.getRamId(), newBuild.getPsuId(), newBuild.getStorageDriveId(), newBuild.getCaseId(),
                    newBuild.getFanId(), newBuild.getCpuCoolerId(), newBuild.getPcId());
            return getUserPcBuildByPcId(pcId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error creating UserPcBuild. ", e);
        }

    }

    public UserPcBuild mapRowToUserPcBuild(SqlRowSet rowSet){
        UserPcBuild userPcBuild = new UserPcBuild();
        userPcBuild.setPcId(rowSet.getInt("pc_id"));
        userPcBuild.setProcessorId(rowSet.getInt("processor_id"));
        userPcBuild.setGraphicsCardId(rowSet.getInt("graphics_card_id"));
        userPcBuild.setMotherboardId(rowSet.getInt("motherboard_id"));
        userPcBuild.setRamId(rowSet.getInt("ram_id"));
        userPcBuild.setPsuId(rowSet.getInt("psu_id"));
        userPcBuild.setStorageDriveId(rowSet.getInt("storage_drive_id"));
        userPcBuild.setCaseId(rowSet.getInt("case_id"));
        userPcBuild.setFanId(rowSet.getInt("fan_id"));
        userPcBuild.setCpuCoolerId(rowSet.getInt("cpu_cooler_id"));
        return userPcBuild;
    }
}
