package org.example.daos;

import org.apache.commons.dbcp2.BasicDataSource;
import org.example.models.StorageDrive;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import viewmodels.StorageDriveWithBrand;

import java.util.ArrayList;
import java.util.List;

public class StorageDriveDao {
    private JdbcTemplate jdbcTemplate;

    public StorageDriveDao(BasicDataSource basicDataSource){
        jdbcTemplate = new JdbcTemplate(basicDataSource);
    }


    public List<StorageDriveWithBrand> getStorageDrivesWithBrands(){
        List<StorageDriveWithBrand> storageDrives = new ArrayList<>();
        String sql = "select storage_drive_id, brand_name, product_name, capacity_gb, form_factor, price\n" +
                "from storage_drive\n" +
                "join brand on brand.brand_id = storage_drive.brand_id;\n";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()){
            StorageDriveWithBrand storageDrive = new StorageDriveWithBrand();
            storageDrive.setStorageDriveId(rowSet.getInt("storage_drive_id"));
            storageDrive.setBrandName(rowSet.getString("brand_name"));
            storageDrive.setProductName(rowSet.getString("product_name"));
            storageDrive.setCapacityGb(rowSet.getInt("capacity_gb"));
            storageDrive.setFormFactor(rowSet.getString("form_factor"));
            storageDrive.setPrice(rowSet.getBigDecimal("price"));
            storageDrives.add(storageDrive);
        }
        return storageDrives;
    }

    public StorageDrive getStorageDriveById(int storageDriveId){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from storage_drive where storage_drive_id = ?;", storageDriveId);
        if (results.next()){
            return mapRowToStorageDrive(results);
        }
        return null;
    }

    public StorageDrive mapRowToStorageDrive(SqlRowSet rowSet){
        StorageDrive storageDrive = new StorageDrive();
        storageDrive.setStorageDriveId(rowSet.getInt("storage_drive_id"));
        storageDrive.setBrandId(rowSet.getInt("brand_id"));
        storageDrive.setProductName(rowSet.getString("product_name"));
        storageDrive.setModel(rowSet.getString("model"));
        storageDrive.setCapacityGb(rowSet.getInt("capacity_gb"));
        storageDrive.setFormFactor(rowSet.getString("form_factor"));
        storageDrive.setPrice(rowSet.getBigDecimal("price"));
        return storageDrive;
    }
}
