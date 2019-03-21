package com.nhanhuynh.challenge;

import com.cloudinary.strategies.AbstractApiStrategy;
import com.cloudinary.strategies.AbstractUploaderStrategy;
import com.cloudinary.strategies.StrategyLoader;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class HomeController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    CloudinaryConfig cloudc;



    @RequestMapping("/")
    public String index(Model model){

        for(Category cat: categoryRepository.findAll()) {
            if ((cat.getIdnum() != 1) && (!cat.getCars().isEmpty()) )
                categoryRepository.findById(cat.getId()).get().setHascar(true);

            if ((cat.getIdnum() == 1) && (cat.getCars().isEmpty())) {
                model.addAttribute("recentcarsremoved", "There are currently no recently removed car listings.");
                cat.setHascar(true);
            }
        }
       List<Car> recentaddcarlist = new ArrayList<>();
        List<Car> recentmodifiedcarlist = new ArrayList<>();

        for(Car car: carRepository.findAll()) {
            if (car.isRecentadd())
                recentaddcarlist.add(car);
            if (car.isRecentmodified())
                recentmodifiedcarlist.add(car);
        }

        if (recentaddcarlist.isEmpty())
            model.addAttribute("recentcarsaddedmsg", "There are currently no recently added car listing.");
        else
            model.addAttribute("recentcarsadded", recentaddcarlist);

        if (recentmodifiedcarlist.isEmpty())
            model.addAttribute("recentcarsmodifiedmsg", "There are currently no recently updated/modified car listing.");
        else
            model.addAttribute("recentcarsmodified", recentmodifiedcarlist);


        model.addAttribute("categories", categoryRepository.findAll());

        return "index";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addform";
    }

   @PostMapping("/addprocess")
    public String processaddForm(@Valid @ModelAttribute("car") Car car, BindingResult result, Model model,
                                 @RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) {
       model.addAttribute("categories", categoryRepository.findAll());

       if (result.hasErrors() && !file.isEmpty()) {
           model.addAttribute("message", "Please re-select a file to upload.");
           return "addform";
       }

       else if (result.hasErrors() && file.isEmpty()){
            model.addAttribute("message", "Please select a file to upload.");
            return "addform";
        }

       else if (file.isEmpty()) {
           model.addAttribute("message", "Please select a file to upload.");
           return "addform";
       }

       try {
            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            car.setCarphoto(uploadResult.get("url").toString());

            Category category = car.getCategory();
            car.setRecentadd(true);
            categoryRepository.findById(category.getId()).get().getCars().add(car);
            categoryRepository.save(categoryRepository.findById(category.getId()).get());

            redirectAttributes.addFlashAttribute("addsuccess",
                    "You have successfully uploaded '" + file.getOriginalFilename() + "'" +
                        " and added a car listing for a ");
           redirectAttributes.addFlashAttribute("info",car.getCategory().getType() +
                        " " + car.getYear()+ " " + car.getManufacturer() +
                        " " + car.getCarmodel() + " " + car.getCartrim() + ".");

        }
        catch (IOException e){
            e.printStackTrace();
            return "redirect:/add";
        }

        return "redirect:/";
    }


    @GetMapping("/addcategory")
    public String addCategoryForm(Model model){
        model.addAttribute("category", new Category());
        return "addcategoryform";
    }

     @PostMapping("/addcategoryprocess")
    public String processForm(@Valid @ModelAttribute("category") Category category, BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "addcategoryform";
        }

//        List<Category> categories = new ArrayList<>();
//        categoryRepository.findAll().forEach(categories::add);
//        category.setIdnum(categories.size()+1);

        category.setIdnum((int)categoryRepository.count()+1);
        categoryRepository.save(category);
         redirectAttributes.addFlashAttribute("addsuccess",
                 "You have successfully added a new car category named ");
         redirectAttributes.addFlashAttribute("info", "'" + category.getType() +"'.");
        return "redirect:/";
    }

    @GetMapping("/deletecategory")
    public String deleteCategoryForm(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("categories", categoryRepository.findAll());
        return "deletecategoryform :: deletecategoryModal";
    }

    @PostMapping("/deletecategoryprocess")
    public String deletecategoryprocessForm(Car car, Model model, RedirectAttributes redirectAttributes) {
        Car carset = new Car();
        Category category = car.getCategory();
        // clear all cars in category
        for(Iterator<Car> i = categoryRepository.findById(category.getId()).get().getCars().iterator(); i.hasNext();) {
            carset = i.next();
            i.remove();
            carRepository.delete(carset);
        }
            //categoryRepository.findById(category.getId()).get().getCars().remove(element);


        redirectAttributes.addFlashAttribute("addsuccess",
                "You have successfully delete the category named ");
        redirectAttributes.addFlashAttribute("info", "'" + category.getType() +"'.");
        categoryRepository.deleteById(category.getId());
        return "redirect:/";
    }


    @RequestMapping("/details/{id}")
    public String showCar(@PathVariable("id") long id, Model model) {
//        Car car = carRepository.findById(id).get();
//       Category category = car.getCategory();

//        for(Car value: category.getCars()){
//            String element = (String) object;
//        }
//              category.getCars().
//        model.addAttribute("car", car);

//        model.addAttribute("category", category);

        model.addAttribute("car", carRepository.findById(id).get());
//        model.addAttribute("categories", categoryRepository.findAll());
        return "showdetails";
    }


    @RequestMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id , Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "updateform";
    }

    @PostMapping("/updateprocess")
    public String processupdateForm(@Valid @ModelAttribute("car") Car car, BindingResult result, Model model,
                                    @RequestParam("file")MultipartFile file) {
        model.addAttribute("categories", categoryRepository.findAll());

        if (result.hasErrors()) {
            return "updateform";
        }

        if (file.isEmpty()){
            return "redirect:/updateprocess";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            car.setCarphoto(uploadResult.get("url").toString());
            car.setRecentmodified(true);
            carRepository.save(car);
        }
        catch (IOException e){
            e.printStackTrace();
            return "redirect:/updateprocess";
        }

        return "redirect:/";
    }



    @RequestMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") long id, RedirectAttributes redirectAttributes ){
        Car car = carRepository.findById(id).get();
        Category category = car.getCategory();

        categoryRepository.findById(category.getId()).get().getCars().remove(car);


        categoryRepository.findById(category.getId()).get().getCars().remove(car);

        categoryRepository.save( categoryRepository.findById(category.getId()).get());

        redirectAttributes.addFlashAttribute("addsuccess",
                "You have successfully deleted a ");
        redirectAttributes.addFlashAttribute("info",car.getCategory().getType() +
                " " + car.getYear()+ " " + car.getManufacturer() +
                " " + car.getCarmodel() + " " + car.getCartrim() + ".");

        carRepository.deleteById(id);


        return "redirect:/";
    }

}


