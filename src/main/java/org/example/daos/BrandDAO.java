package org.example.daos;

import org.apache.commons.dbcp2.BasicDataSource;
import org.example.DaoException;
import org.example.models.Brand;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO {
    private JdbcTemplate jdbcTemplate;

    public BrandDAO(BasicDataSource basicDataSource){
        jdbcTemplate = new JdbcTemplate(basicDataSource);
    }

    public Brand getBrandById(int id){
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from brand where brand_id = ?;", id);
        if (results.next()){
            return mapRowToBrand(results);
        }
        return null;
    }
    public List<Brand> getAllBrands(){
        List<Brand> brands = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from brand;");
        while (results.next()){
            brands.add(mapRowToBrand(results));
        }
        return brands;
    }

    public List<Brand> searchBrandByName(String name){
        List<Brand> brands = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet("select * from brand where brand_name iLike ?", "%" + name + "%");
        while (results.next()){
            brands.add(mapRowToBrand(results));
        }
        return brands;
    }
    public Brand createBrand(Brand newBrand){
        try{
            int brandId = jdbcTemplate.queryForObject("insert into brand (brand_name) values (?) returning brand_id;", int.class, newBrand.getName());
            return getBrandById(brandId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error creating new brand. ", e);
        }
    }
    public Brand updateBrand(Brand brand){
        Brand updatedBrand = null;
        try{
            int numOfRows = jdbcTemplate.update("update brand set brand_name=? where brand_id=?;", brand.getName(), brand.getId());
            if(numOfRows == 0){
                throw new DaoException("Zero rows affected, expected at least 1. ");
            }else{
                updatedBrand = getBrandById(brand.getId());
            }
            return updatedBrand;
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error updating brand. ", e);
        }
    }

    public int deleteBrandById(int brandId){
        int numOfRows = 0;
        try{
            jdbcTemplate.update("delete from storage_drive where brand_id = ?;", brandId);
            jdbcTemplate.update("delete from cpu_cooler where brand_id = ?;", brandId);
            jdbcTemplate.update("delete from fans where brand_id = ?;", brandId);
            jdbcTemplate.update("delete from processor where brand_id = ?;", brandId);
            jdbcTemplate.update("delete from graphics_card where brand_id = ?;", brandId);
            jdbcTemplate.update("delete from motherboard where brand_id = ?;", brandId);
            jdbcTemplate.update("delete from ram where brand_id = ?;", brandId);
            jdbcTemplate.update("delete from psu where brand_id = ?;", brandId);
            jdbcTemplate.update("delete from pc_case where brand_id = ?;", brandId);
            numOfRows = jdbcTemplate.update("delete from brand where brand_id = ?;", brandId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to database or server. ", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Error deleting brand. ", e);
        }
        return numOfRows;
    }

    public Brand mapRowToBrand(SqlRowSet rowSet){
        Brand brand = new Brand();
        brand.setId(rowSet.getInt("brand_id"));
        brand.setName(rowSet.getString("brand_name"));
        return brand;
    }
}
