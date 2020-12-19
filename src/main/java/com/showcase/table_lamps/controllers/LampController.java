package com.showcase.table_lamps.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.showcase.table_lamps.LampEntity;
import com.showcase.table_lamps.LampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/lamp")
public class LampController {

    @Autowired
    LampRepository lampRepository;




    @GetMapping("")
    public List<LampEntity> getAllLamps() {
        List<LampEntity> allLampList = lampRepository.findAll();
        return allLampList;
    }

    @GetMapping("/{id}")
    public LampEntity getLampById(@PathVariable(value = "id") Integer lampId)

    {
        LampEntity lampEntity = lampRepository.findById(lampId).get();

        return lampEntity;
    }

    @PostMapping("")
    public  LampEntity createLamp(@RequestParam("name") String name,
                                   @RequestParam("stand") String stand,
                                   @RequestParam("material") String material,
                                   @RequestParam("power_type") String power_type,
                                   @RequestParam("height") Double height,
                                   @RequestParam(value = "image", required = false)MultipartFile file,
                                   @RequestParam("price") Double price,
                                   HttpServletRequest request) throws IOException {

        LampEntity lamp= new LampEntity();
        lamp.setName(name);
        lamp.setStand(stand);
        lamp.setMaterial(material);
        lamp.setPower_type(power_type);
        lamp.setHeight(height);
        lamp.setPrice(price);
        lamp.setImage(file.getName());

        LampEntity savedLamp = lampRepository.save(lamp);

        return savedLamp;

    }

@PutMapping("/{id}")
public ResponseEntity<LampEntity> updateLamp (@PathVariable(value = "id") Integer lampId,
                                            @RequestParam("name") String name,
                                              @RequestParam("stand") String stand,
                                              @RequestParam("material") String material,
                                              @RequestParam("power_type") String power_type,
                                              @RequestParam("height") Double height,
                                            @RequestParam("price") Double price,
                                            @RequestParam(value = "image", required = false) MultipartFile file,
                                            HttpServletRequest request) throws IOException {
    LampEntity lamp = lampRepository.findById(lampId).get();

    lamp.setName(name);
    lamp.setStand(stand);
    lamp.setMaterial(material);
    lamp.setPower_type(power_type);
    lamp.setHeight(height);
    lamp.setPrice(price);

    final LampEntity newLamp = lampRepository.save(lamp);
    return ResponseEntity.ok(newLamp);
}

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteLamp(@PathVariable(value = "id") Integer lampId)
    {
        LampEntity lamp = lampRepository.findById(lampId).get();

        lampRepository.delete(lamp);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
