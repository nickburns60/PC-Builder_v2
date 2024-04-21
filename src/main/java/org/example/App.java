package org.example;

        import org.apache.commons.dbcp2.BasicDataSource;
        import org.example.daos.*;
        import org.example.models.*;
        import viewmodels.GPUWithBrandWattage;
        import viewmodels.MoboWithSocketFormRamBrand;
        import viewmodels.ProcessorWithBrandSocketRam;
        import viewmodels.RamWithBrandRamType;

        import java.util.List;
        import java.util.Scanner;


public class App
{
    public static BasicDataSource basicDataSource;
    public static BrandDAO brandDao;
    public static ProcessorDao processorDao;
    public static GraphicsCardDao graphicsCardDao;
    public static MotherboardDao motherboardDao;
    public static RamDao ramDao;
    public static void main( String[] args )
    {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:postgresql://localhost:5432/PC-Building-Program");
        basicDataSource.setUsername("postgres");
        basicDataSource.setPassword("postgres1");

        brandDao = new BrandDAO(basicDataSource);
        processorDao = new ProcessorDao(basicDataSource);
        graphicsCardDao = new GraphicsCardDao(basicDataSource);
        motherboardDao = new MotherboardDao(basicDataSource);
        ramDao = new RamDao(basicDataSource);


        boolean continueProgram = true;
        while (continueProgram){
//            System.out.println("This is the PC Part Picker. What would you like to do?");
//            String menu = """
//                1)  View Brands
//                2)  Create Brand
//                3)  Update Brand
//                4)  Delete Brand
//                """;
//            System.out.println(menu);
//            Scanner input = new Scanner(System.in);
//            String menuSelection = input.nextLine();
//
//            if(menuSelection.equals("1") || menuSelection.equalsIgnoreCase("view brands")){
//                List<Brand> brands = brandDao.getAllBrands();
//                int i = 1;
//                for(Brand brand : brands){
//                    System.out.println(i + ") " + brand.getName());
//                    i++;
//                }
//            } else if (menuSelection.equals("2") || menuSelection.equalsIgnoreCase("create brand")) {
//                //Create a brand
//                System.out.println("Create a brand");
//                String userInput = input.nextLine();
//                Brand newBrand = new Brand();
//                newBrand.setName(userInput);
//                newBrand = brandDao.createBrand(newBrand);
//                System.out.println(newBrand.getName() + " created");
//            } else if (menuSelection.equals("3") || menuSelection.equalsIgnoreCase("update brand")) {
//                //Update a brand
//                System.out.println("Choose a brand to update");
//                List<Brand> brands = brandDao.getAllBrands();
//                int i = 1;
//                for(Brand brand : brands){
//                    System.out.println(i + ") " + brand.getName());
//                    i++;
//                }
//                int selectedBrand = Integer.parseInt(input.nextLine());
//                System.out.println("What is the new name");
//                String newBrandName = input.nextLine();
//                Brand brandToUpdate = new Brand(selectedBrand, "");
//                brandToUpdate.setName(newBrandName);
//                brandDao.updateBrand(brandToUpdate);
//            } else if (menuSelection.equals("4") || menuSelection.equalsIgnoreCase("delete brand")) {
//                System.out.println("Select a brand to remove: ");
//                List<Brand> brands = brandDao.getAllBrands();
//                int i = 1;
//                for(Brand brand : brands){
//                    System.out.println(i + ") " + brand.getName());
//                    i++;
//                }
//                int selectedBrand = Integer.parseInt(input.nextLine());
//                Brand brandSelected = brandDao.getBrandById(selectedBrand);
//                String brandNameSelected = brandSelected.getName();
//                brandDao.deleteBrandById(selectedBrand);
//                System.out.println(brandNameSelected + " has been deleted.");
//            }
//            System.out.println("Would you like to 1) return to menu or 2) end program");
//            if (input.nextLine().equals("2") || input.nextLine().equalsIgnoreCase("end program")){
//                continueProgram = false;
//            }
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome to the PC part picker. We will help you build your dream computer! Choose from our options below to begin.");
            String menu = """
                    **************************************
                    *** 1) Start a new pc build        ***
                    *** 2) Update an existing pc build ***
                    *** 3) Delete an existing pc build ***
                    **************************************
                    """;
            System.out.println(menu);
            String menuChoice = input.nextLine();
            if(menuChoice.equals("1")){
                //Create a UserPcBuild
                UserPcBuild userPcBuild = new UserPcBuild();
                System.out.println("You have chosen to build a new pc. Select which part category you would like to start with.");
                String partCategories = """
                        ************************
                        *** 1) Processor     ***
                        *** 2) Graphics Card ***
                        *** 3) Motherboard   ***
                        ************************
                        """;
                System.out.println(partCategories);
                String categorySelection = input.nextLine();
                if(categorySelection.equals("1")){
                    //processor selection
                    System.out.println("Press enter to view Processor selection.");
                    input.nextLine();
                    List<ProcessorWithBrandSocketRam> processors = processorDao.getAllProcessorsWithFullInfoDisplayed();
                    for(ProcessorWithBrandSocketRam processor : processors){
                        System.out.println(processor);
                    }
                    System.out.println("Select a processor");
                    String processorSelection = input.nextLine();
                    int processorId = Integer.parseInt(processorSelection);
                    userPcBuild.setProcessorId(processorId);
                    Processor selectedProcessor = processorDao.getProcessorByProcessorId(processorId);
                    String selectedParts = "You have selected " + selectedProcessor + " for processor";
                    System.out.println(selectedParts);


                    //gpu selection
                    System.out.println("Press enter to view Graphics Card selection.");
                    input.nextLine();
                    List<GPUWithBrandWattage> gpus = graphicsCardDao.getAllGpusWithFullInfoDisplayed();
                    for(GPUWithBrandWattage gpu : gpus){
                        System.out.println(gpu);
                    }
                    System.out.println("Select a graphics card");
                    String gpuSelection = input.nextLine();
                    int gpuId = Integer.parseInt(gpuSelection);
                    userPcBuild.setGraphicsCardId(gpuId);
                    GraphicsCard selectedGraphicsCard = graphicsCardDao.getGraphicsCardById(gpuId);
                    selectedParts = selectedParts + " | " + selectedGraphicsCard + " for graphics card";
                    System.out.println(selectedParts);

                    //mobo selection
                    System.out.println("Press enter to view Motherboard selection.");
                    input.nextLine();
                    List<MoboWithSocketFormRamBrand> mobos = motherboardDao.getCompatibleMobosBySocketId(selectedProcessor.getSocketId());
                    for (MoboWithSocketFormRamBrand mobo : mobos){
                        System.out.println(mobo);
                    }
                    System.out.println("Select a motherboard");
                    int moboId = Integer.parseInt(input.nextLine());
                    userPcBuild.setMotherboardId(moboId);
                    Motherboard selectedMobo = motherboardDao.getMotherboardById(moboId);
                    selectedParts = selectedParts + " | " + selectedMobo + " for motherboard";
                    System.out.println(selectedParts);

                    //ram selection
                    System.out.println("Press enter to view Ram selection");
                    input.nextLine();
                    List<RamWithBrandRamType> compatibleRam = ramDao.getCompatibleRamByRamTypeId(selectedMobo.getRamTypeId());
                    for(RamWithBrandRamType ram : compatibleRam){
                        System.out.println(ram);
                    }
                    System.out.println("Select one of our ram options");
                    int ramId = Integer.parseInt(input.nextLine());
                    userPcBuild.setRamId(ramId);
                    Ram selectedRam = ramDao.getRamById(ramId);
                    selectedParts = selectedParts + " | " + selectedRam + " for ram";
                    System.out.println(selectedParts);
                }
            }
        }
    }
}
