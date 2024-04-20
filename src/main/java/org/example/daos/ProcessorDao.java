package org.example.daos;

import org.apache.commons.dbcp2.BasicDataSource;
import org.example.models.Processor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class ProcessorDao {
    private JdbcTemplate jdbcTemplate;

    public ProcessorDao(BasicDataSource basicDataSource){
        jdbcTemplate = new JdbcTemplate(basicDataSource);
    }

    public List<Processor> getAllProcessors(){
        List<Processor> processors = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from processor;");
        while(results.next()){
            processors.add(mapRowToProcessor(results));
        }
        return processors;
    }

    public Processor getProcessorByProcessorId(int id){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from processor where processor_id = ?", id);
        if(results.next()){
            return mapRowToProcessor(results);
        }
        return null;
    }

    public Processor mapRowToProcessor(SqlRowSet rowSet){
        Processor processor = new Processor();
        processor.setProcessorId(rowSet.getInt("processor_id"));
        processor.setBrandId(rowSet.getInt("brand_id"));
        processor.setSocketId(rowSet.getInt("socket_id"));
        processor.setRamTypeId(rowSet.getInt("ram_type_id"));
        processor.setProductName(rowSet.getString("product_name"));
        processor.setModel(rowSet.getString("model"));
        processor.setPrice(rowSet.getBigDecimal("price"));
        return processor;
    }
}
