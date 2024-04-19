package org.example;

import org.apache.commons.dbcp2.BasicDataSource;
import org.example.daos.BrandDAO;
import org.example.models.Brand;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static BasicDataSource basicDataSource;
    public static BrandDAO brandDao;
    public static void main( String[] args )
    {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:postgresql://localhost:5432/PC-Building-Program");
        basicDataSource.setUsername("postgres");
        basicDataSource.setPassword("postgres1");

        brandDao = new BrandDAO(basicDataSource);

        Scanner input = new Scanner(System.in);
//        //Create a brand
//        System.out.println("Create a brand");
//        String userInput = input.nextLine();
//        Brand newBrand = new Brand();
//        newBrand.setName(userInput);
//        newBrand = brandDao.createBrand(newBrand);
//        System.out.println(newBrand.getName() + " created");
//
//        //Update a brand
//        System.out.println("Choose a brand to update");
//        List<Brand> brands = brandDao.getAllBrands();
//        int i = 1;
//        for(Brand brand : brands){
//            System.out.println(i + ") " + brand.getName());
//            i++;
//        }
//        int selectedBrand = Integer.parseInt(input.nextLine());
//        System.out.println("What is the new name");
//        String newBrandName = input.nextLine();
//        Brand brandToUpdate = new Brand(selectedBrand, "");
//        brandToUpdate.setName(newBrandName);
//        brandDao.updateBrand(brandToUpdate);

        System.out.println("Select a brand to remove: ");
        List<Brand> brands = brandDao.getAllBrands();
        int i = 1;
        for(Brand brand : brands){
            System.out.println(i + ") " + brand.getName());
            i++;
        }
        int selectedBrand = Integer.parseInt(input.nextLine());
        brandDao.deleteBrandById(selectedBrand);
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
