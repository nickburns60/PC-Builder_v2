package org.example;

import org.apache.commons.dbcp2.BasicDataSource;
import org.example.daos.BrandDAO;
import org.example.models.Brand;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static BasicDataSource basicDataSource;
    public static BrandDAO brandDAO;
    public static void main( String[] args )
    {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:postgresql://localhost:5432/PC-Building-Program");
        basicDataSource.setUsername("postgres");
        basicDataSource.setPassword("postgres1");

        brandDAO = new BrandDAO(basicDataSource);

        Scanner input = new Scanner(System.in);

        System.out.println("Create a brand");
        String userInput = input.nextLine();
        Brand newBrand = new Brand();
        newBrand.setName(userInput);
        newBrand = brandDAO.createBrand(newBrand);
        System.out.println(newBrand.getName() + " created");

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter brand search term: ");
//        String searchTerm = scanner.nextLine();
//
//        List<Brand> brands = brandDAO.searchBrandByName(searchTerm);
//        for(Brand brand : brands){
//            System.out.println(brand.getName());
//        }
    }
}
