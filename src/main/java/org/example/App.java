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

        boolean continueProgram = true;
        while (continueProgram){
            System.out.println("This is the PC Part Picker. What would you like to do?");
            String menu = """
                1)  View Brands
                2)  Create Brand
                3)  Update Brand
                4)  Delete Brand
                """;
            System.out.println(menu);
            Scanner input = new Scanner(System.in);
            String menuSelection = input.nextLine();

            if(menuSelection.equals("1") || menuSelection.equalsIgnoreCase("view brands")){
                //System.out.println("Select a brand to remove: ");
                List<Brand> brands = brandDao.getAllBrands();
                int i = 1;
                for(Brand brand : brands){
                    System.out.println(i + ") " + brand.getName());
                    i++;
                }
            } else if (menuSelection.equals("2") || menuSelection.equalsIgnoreCase("create brand")) {
                //Create a brand
                System.out.println("Create a brand");
                String userInput = input.nextLine();
                Brand newBrand = new Brand();
                newBrand.setName(userInput);
                newBrand = brandDao.createBrand(newBrand);
                System.out.println(newBrand.getName() + " created");
            } else if (menuSelection.equals("3") || menuSelection.equalsIgnoreCase("update brand")) {
                //Update a brand
                System.out.println("Choose a brand to update");
                List<Brand> brands = brandDao.getAllBrands();
                int i = 1;
                for(Brand brand : brands){
                    System.out.println(i + ") " + brand.getName());
                    i++;
                }
                int selectedBrand = Integer.parseInt(input.nextLine());
                System.out.println("What is the new name");
                String newBrandName = input.nextLine();
                Brand brandToUpdate = new Brand(selectedBrand, "");
                brandToUpdate.setName(newBrandName);
                brandDao.updateBrand(brandToUpdate);
            } else if (menuSelection.equals("4") || menuSelection.equalsIgnoreCase("delete brand")) {
                System.out.println("Select a brand to remove: ");
                List<Brand> brands = brandDao.getAllBrands();
                int i = 1;
                for(Brand brand : brands){
                    System.out.println(i + ") " + brand.getName());
                    i++;
                }
                int selectedBrand = Integer.parseInt(input.nextLine());
                Brand brandSelected = brandDao.getBrandById(selectedBrand);
                String brandNameSelected = brandSelected.getName();
                brandDao.deleteBrandById(selectedBrand);
                System.out.println(brandNameSelected + " has been deleted.");
            }
            System.out.println("Would you like to 1) return to menu or 2) end program");
            if (input.nextLine().equals("2") || input.nextLine().equalsIgnoreCase("end program")){
                continueProgram = false;
            }
        }






//




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
