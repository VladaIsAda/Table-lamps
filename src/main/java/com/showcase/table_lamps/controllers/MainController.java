package com.showcase.table_lamps.controllers;

import com.showcase.table_lamps.LampEntity;
import com.showcase.table_lamps.LampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping
@Controller
public class MainController {

    @Autowired
    LampRepository lampRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/admin")
    public String admin(Model model) {
        List<LampEntity> allLampList = lampRepository.findAll();
        model.addAttribute("lampList", allLampList);
        return "redirect:/lampList";
    }


    @GetMapping("/lampList")
    public String getAllLamps(Model model) {
        List<LampEntity> allLampList = lampRepository.findAll();
        model.addAttribute("lampList", allLampList);
        return "lampList";
    }


    @GetMapping("/lampList/lampPage/{id}")
    public String getLampById(@PathVariable(value = "id") Integer lampId, Model model) {
        LampEntity lampEntity = lampRepository.findById(lampId).get();
        model.addAttribute("lamp", lampEntity);
        return "lampPage";
    }

    @GetMapping("/lampList/add")
    public String formAddLamp(Model model) {
        return "addLamp";
    }

    @PostMapping("/lampList/add")
    public String addLamp(@RequestParam String name,
                          @RequestParam String stand,
                          @RequestParam String material,
                          @RequestParam String power_type,
                          @RequestParam Double height,
                          @RequestParam("file") MultipartFile file,
                          @RequestParam("price") Double price,
                          HttpServletRequest request, Model model) throws IOException {

        LampEntity lamp = new LampEntity();
        lamp.setName(name);
        lamp.setStand(stand);
        lamp.setMaterial(material);
        lamp.setPower_type(power_type);
        lamp.setHeight(height);
        lamp.setPrice(price);

        if (file != null && !file.getOriginalFilename().isEmpty()) {

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + '.' + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));

            lamp.setImage(resultFileName);

        }
        lampRepository.save(lamp);

        return "addLamp";
    }


    @PostMapping("lampList/lampPage/{id}")
    public String deleteLamp(@PathVariable(value = "id") Integer lampId) {
        LampEntity lamp = lampRepository.findById(lampId).get();
        lampRepository.delete(lamp);
        return "redirect:/lampList";
    }

    @GetMapping("/lampList/lampPage/{id}/edit")
    public String editLampMap(@PathVariable(value = "id") Integer lampId,
                              Model model) {
        LampEntity lampEntity = lampRepository.findById(lampId).get();
        model.addAttribute("lamp", lampEntity);
        return "editLamp";
    }

    @PostMapping("/lampList/lampPage/{id}/edit")
    public String editLamp(@PathVariable(value = "id") Integer lampId,
                           @RequestParam String name,
                           @RequestParam String stand,
                           @RequestParam String material,
                           @RequestParam String power_type,
                           @RequestParam Double height,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam("price") Double price,
                           HttpServletRequest request, Model model) throws IOException {
        LampEntity lamp = lampRepository.findById(lampId).get();

        lamp.setName(name);
        lamp.setStand(stand);
        lamp.setMaterial(material);
        lamp.setPower_type(power_type);
        lamp.setHeight(height);
        lamp.setPrice(price);

        if (file != null && !file.getOriginalFilename().isEmpty()) {

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + '.' + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));

            lamp.setImage(resultFileName);

        }
        lampRepository.save(lamp);
        return "redirect:/lampList";
    }


    @GetMapping("/home")
    public String home(Model model) {
        List<LampEntity> allLampList = lampRepository.findAll();
        model.addAttribute("lampList", allLampList);
        return "home";
    }


    @GetMapping("/home/lamp/{id}")
    public String LampById(@PathVariable(value = "id") Integer lampId, Model model) {
        LampEntity lampEntity = lampRepository.findById(lampId).get();
        model.addAttribute("lamp", lampEntity);
        return "lamp";
    }

}
