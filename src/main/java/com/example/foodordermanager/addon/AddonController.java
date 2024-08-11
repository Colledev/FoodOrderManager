package com.example.foodordermanager.addon;

import com.example.foodordermanager.addon.dto.AddonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/addon")
public class AddonController {

    private static final Logger log = LoggerFactory.getLogger(AddonController.class);

    @Autowired
    private AddonService addonService;

    @GetMapping
    public ResponseEntity<List<AddonDTO>> getAllAddons() {
        try {
            log.info("Received request to get all addons");
            List<AddonDTO> addons = addonService.getAllAddons();
            log.info("Fetched addons: {}", addons);
            return ResponseEntity.ok(addons);
        } catch (Exception e) {
            log.error("Error occurred while getting all addons", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @PostMapping
    public ResponseEntity<List<AddonDTO>> createAddons(@RequestBody List<AddonDTO> addonDTOs) {
        try {
            log.info("Received request to create addons: {}", addonDTOs);
            List<AddonDTO> createdAddons = addonService.createAddons(addonDTOs);
            log.info("Created addons: {}", createdAddons);
            return ResponseEntity.ok(createdAddons);
        } catch (Exception e) {
            log.error("Error occurred while creating addons", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
