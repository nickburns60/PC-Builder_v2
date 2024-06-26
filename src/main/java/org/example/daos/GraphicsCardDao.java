package org.example.daos;

import org.apache.commons.dbcp2.BasicDataSource;
import org.example.models.GraphicsCard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import viewmodels.GPUWithBrandWattage;

import java.util.ArrayList;
import java.util.List;

public class GraphicsCardDao {
    private JdbcTemplate jdbcTemplate;
    public GraphicsCardDao(BasicDataSource basicDataSource){
        jdbcTemplate = new JdbcTemplate(basicDataSource);
    }

    public List<GraphicsCard> getAllGpus(){
        List<GraphicsCard> graphicsCards = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from graphics_card");
        while(results.next()){
            graphicsCards.add(mapRowToGpu(results));
        }
        return graphicsCards;
    }

    public List<GPUWithBrandWattage> getAllGpusWithFullInfoDisplayed(){
        List<GPUWithBrandWattage> gpuWithBrandWattages = new ArrayList<>();
        String sql = "select graphics_card_id, brand_name, graphics_card.product_name, wattage, graphics_card.price\n" +
                "from graphics_card\n" +
                "join brand on brand.brand_id = graphics_card.brand_id\n" +
                "join psu_wattage on psu_wattage.psu_wattage_id = graphics_card.psu_wattage_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            GPUWithBrandWattage gpu = new GPUWithBrandWattage();
            gpu.setGraphicsCardId(results.getInt("graphics_card_id"));
            gpu.setBrandName(results.getString("brand_name"));
            gpu.setProductName(results.getString("product_name"));
            gpu.setWattage(results.getInt("wattage"));
            gpu.setPrice(results.getBigDecimal("price"));
            gpuWithBrandWattages.add(gpu);
        }
        return gpuWithBrandWattages;
    }

    public GraphicsCard getGraphicsCardById(int gpuId){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from graphics_card where graphics_card_id = ?;", gpuId);
        if(results.next()){
            return mapRowToGpu(results);
        }
        return null;
    }

    public GraphicsCard mapRowToGpu(SqlRowSet rowSet){
        GraphicsCard graphicsCard = new GraphicsCard();
        graphicsCard.setGraphicsCardId(rowSet.getInt("graphics_card_id"));
        graphicsCard.setBrandId(rowSet.getInt("brand_id"));
        graphicsCard.setProductName(rowSet.getString("product_name"));
        graphicsCard.setModel(rowSet.getString("model"));
        graphicsCard.setPsuWattageId(rowSet.getInt("psu_wattage_id"));
        graphicsCard.setPrice(rowSet.getBigDecimal("price"));
        return graphicsCard;
    }

}
