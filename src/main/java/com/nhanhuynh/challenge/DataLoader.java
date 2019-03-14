package com.nhanhuynh.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    CategoryRepository categoryRepository;
//    CarRepository carRepository;

    @Override
    public void run(String... strings) throws Exception{


//        List<YourEntity> entities = YourEntity.findAll();
//        int size = entities.size();

        Category category = new Category(1);

        Car car = new Car();
        car.setCarphoto("https://res.cloudinary.com/dg89xpg9o/image/upload/v1552459268/Challenge6_CarsListing/MCR12A.jpg");
        car.setManufacturer("Toyota");
        car.setCarmodel("Corolla");
        car.setCartrim("LE");
        car.setYear(2012);
        car.setMileage(12000);
        car.setColor("White");
        car.setPrice(10500.00);
        car.setDescription("Very reliable car.");

        category.getCars().add(car);
        car.setCategory(category);


        car = new Car();
        car.setCarphoto("https://res.cloudinary.com/dg89xpg9o/image/upload/v1552459523/Challenge6_CarsListing/2015-honda-civic-review-images-autonation-12_th-e1419262774692.jpg");
        car.setManufacturer("Honda");
        car.setCarmodel("Civic");
        car.setCartrim("EX");
        car.setYear(2015);
        car.setMileage(5000);
        car.setColor("Maroon");
        car.setPrice(30500.00);
        car.setDescription("Nice car. Great value.");

        category.getCars().add(car);
        car.setCategory(category);

        car = new Car();
        car.setCarphoto("https://res.cloudinary.com/dg89xpg9o/image/upload/v1552460285/Challenge6_CarsListing/2017-kia-rio-FUNctional.jpg");
        car.setManufacturer("Kia");
        car.setCarmodel("Rio");
        car.setCartrim("EX");
        car.setYear(2017);
        car.setMileage(5000);
        car.setColor("Dark Green");
        car.setPrice(10000.00);
        car.setDescription("Has all the options.");

        category.getCars().add(car);
        car.setCategory(category);

        car = new Car();
        car.setCarphoto("https://res.cloudinary.com/dg89xpg9o/image/upload/v1552463525/Challenge6_CarsListing/open-19.jpg");
        car.setManufacturer("Ford");
        car.setCarmodel("Fiesta");
        car.setCartrim("ST");
        car.setYear(2019);
        car.setMileage(100);
        car.setColor("Orange");
        car.setPrice(13000.00);
        car.setDescription("Great car. Fully loaded.");

        category.getCars().add(car);
        car.setCategory(category);

        car = new Car();
        car.setCarphoto("https://res.cloudinary.com/dg89xpg9o/image/upload/v1552460609/Challenge6_CarsListing/71IvbqyTkAL._UY560_.jpg");
        car.setManufacturer("MINI");
        car.setCarmodel("Cooper");
        car.setCartrim("SE");
        car.setYear(2018);
        car.setMileage(2000);
        car.setColor("Green");
        car.setPrice(23000.00);
        car.setDescription("A classic.");

        category.getCars().add(car);


        car.setCategory(category);

//        category.setIdnum()
        categoryRepository.save(category);

        category = new Category(2);

        car = new Car();
        car.setCarphoto("https://res.cloudinary.com/dg89xpg9o/image/upload/v1552459523/Challenge6_CarsListing/2015-Toyota-Camry-XSE-front-three-quarters-07.jpg");
        car.setManufacturer("Toyota");
        car.setCarmodel("Camry");
        car.setCartrim("LE");
        car.setYear(2015);
        car.setMileage(20000);
        car.setColor("Red");
        car.setPrice(15500.00);
        car.setDescription("Awesome car. Drive like New.");

        category.getCars().add(car);
        car.setCategory(category);

        //Add the car an empty list
//        Set<Car> cars = new HashSet<Car>();
//        Set<Car> mergeset= new HashSet<Car>();

//        cars.add(car);
//        carRepository.save(car);

        //another car
        car = new Car();
//        car.setCarphoto("");
        car.setManufacturer("Honda");
        car.setCarmodel("Accord");
        car.setCartrim("Touring");
        car.setYear(2017);
        car.setMileage(35000);
        car.setColor("Grey Metallic");
        car.setPrice(20500.00);
        car.setDescription("Very nice Car.");

        category.getCars().add(car);
        car.setCategory(category);


        //add the car
//        cars.add(car);
//        carRepository.save(car);
/*        if (category.getCars().isEmpty())
        category.setCars(cars);
        else {
            mergeset.addAll(category.getCars());
            mergeset.addAll(cars);
            category.setCars(mergeset);
        }
*/


        car = new Car();
        car.setCarphoto("https://res.cloudinary.com/dg89xpg9o/image/upload/v1552462112/Challenge6_CarsListing/2016-ford-fusion-quick-take-review-car-and-driver-photo-664780-s-original.jpg");
        car.setManufacturer("Ford");
        car.setCarmodel("Fusion");
        car.setCartrim("SE");
        car.setYear(2019);
        car.setMileage(500);
        car.setColor("Smooth Blue");
        car.setPrice(28000.00);
        car.setDescription("Best value.");

        category.getCars().add(car);
        car.setCategory(category);

        categoryRepository.save(category);

        category = new Category(3);
        car = new Car();
        car.setCarphoto("https://res.cloudinary.com/dg89xpg9o/image/upload/v1552461923/Challenge6_CarsListing/71W3py0SoWL._UY560_.jpg");
        car.setManufacturer("Ford");
        car.setCarmodel("Taurus");
        car.setCartrim("SEL");
        car.setYear(2018);
        car.setMileage(9000);
        car.setColor("Red Orange");
        car.setPrice(25000.00);
        car.setDescription("Roomy.  Nice options.");

        category.getCars().add(car);


        car.setCategory(category);
        categoryRepository.save(category);

         category = new Category(4);
        categoryRepository.save(category);

         category = new Category(5);
        categoryRepository.save(category);

         category = new Category(6);
        categoryRepository.save(category);

         category = new Category(7);
         categoryRepository.save(category);

         category = new Category(8);
        categoryRepository.save(category);

         category = new Category(9);
         categoryRepository.save(category);

         category = new Category(10);
        categoryRepository.save(category);

         category = new Category(11);
        categoryRepository.save(category);

         category = new Category(12);
        categoryRepository.save(category);

        category = new Category(13);
        categoryRepository.save(category);

        category = new Category(14);
        categoryRepository.save(category);

        category = new Category(15);
        car = new Car();
        car.setCarphoto("https://res.cloudinary.com/dg89xpg9o/image/upload/v1552459269/Challenge6_CarsListing/honda-civic-type-r-2015-_2.jpg");
        car.setManufacturer("Honda");
        car.setCarmodel("Civic");
        car.setCartrim("Type R");
        car.setYear(2017);
        car.setMileage(3000);
        car.setColor("Blue");
        car.setPrice(20500.00);
        car.setDescription("For racing only.");
        category.getCars().add(car);
        car.setCategory(category);
        categoryRepository.save(category);

    }
}
