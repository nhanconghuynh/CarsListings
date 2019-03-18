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

        //Grab all car categories from database and send them to template
        model.addAttribute("categories", categoryRepository.findAll());
//        model.addAttribute("cars", carRepository.findAll());

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
            carRepository.save(car);
        }
        catch (IOException e){
            e.printStackTrace();
            return "redirect:/updateprocess";
        }

        return "redirect:/";
    }



    @RequestMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") long id){
        Car car = carRepository.findById(id).get();
        Category category = car.getCategory();
        categoryRepository.findById(category.getId()).get().getCars().remove(car);
        categoryRepository.save( categoryRepository.findById(category.getId()).get());
        carRepository.deleteById(id);
        return "redirect:/";
    }

}


