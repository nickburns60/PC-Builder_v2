package org.example.daos;

import org.apache.commons.dbcp2.BasicDataSource;
import org.example.models.Fans;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import viewmodels.FanWithBrand;

import java.util.ArrayList;
import java.util.List;

public class FanDao {
    private JdbcTemplate jdbcTemplate;

    public FanDao(BasicDataSource basicDataSource){
        jdbcTemplate = new JdbcTemplate(basicDataSource);
    }


    public List<FanWithBrand> getFans(){
        List<FanWithBrand> fans = new ArrayList<>();
        String sql = "select fan_id, brand_name, product_name, num_of_fans, color, rgb, size_mm, price\n" +
                "from fans\n" +
                "join brand on brand.brand_id = fans.brand_id\n";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()){
            FanWithBrand fan = new FanWithBrand();
            fan.setFanId(rowSet.getInt("fan_id"));
            fan.setBrandName(rowSet.getString("brand_name"));
            fan.setProductName(rowSet.getString("product_name"));
            fan.setNumOfFans(rowSet.getInt("num_of_fans"));
            fan.setColor(rowSet.getString("color"));
            fan.setRgb(rowSet.getBoolean("rgb"));
            fan.setSizeInMm(rowSet.getInt("size_mm"));
            fan.setPrice(rowSet.getBigDecimal("price"));
            fans.add(fan);
        }
        return fans;
    }

    public Fans getFanById(int fanId){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from fans where fan_id = ?;", fanId);
        if (results.next()){
            return mapRowToFan(results);
        }
        return null;
    }

    public Fans mapRowToFan(SqlRowSet rowSet){
        Fans fan = new Fans();
        fan.setFanId(rowSet.getInt("fan_id"));
        fan.setBrandId(rowSet.getInt("brand_id"));
        fan.setProductName(rowSet.getString("product_name"));
        fan.setModel(rowSet.getString("model"));
        fan.setNumOfFans(rowSet.getInt("num_of_fans"));
        fan.setColor(rowSet.getString("color"));
        fan.setRgb(rowSet.getBoolean("rgb"));
        fan.setSizeInMm(rowSet.getInt("size_mm"));
        fan.setPrice(rowSet.getBigDecimal("price"));
        return fan;
    }
}
