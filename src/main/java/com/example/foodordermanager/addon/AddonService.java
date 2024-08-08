package com.example.foodordermanager.addon;

import com.example.foodordermanager.addon.dto.AddonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddonService {

    @Autowired
    private AddonRepository addonRepository;

    public List<AddonDTO> getAllAddons() {
        return addonRepository.findAll().stream()
                .map(AddonMapper::mapToAddonDTO)
                .collect(Collectors.toList());
    }

    public List<AddonDTO> createAddons(List<AddonDTO> addonDTOs) {
        List<AddonEntity> addons = addonDTOs.stream()
                .map(AddonMapper::mapToAddon)
                .collect(Collectors.toList());
        List<AddonEntity> savedAddons = addonRepository.saveAll(addons);
        return savedAddons.stream()
                .map(AddonMapper::mapToAddonDTO)
                .collect(Collectors.toList());
    }
}