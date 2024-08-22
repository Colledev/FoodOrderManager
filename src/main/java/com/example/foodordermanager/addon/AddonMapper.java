package com.example.foodordermanager.addon;

import com.example.foodordermanager.addon.dto.AddonDTO;

public class AddonMapper {
    public static AddonDTO mapToAddonDTO(AddonEntity addon) {
        AddonDTO addonDTO = new AddonDTO();
        addonDTO.setId(addon.getId());
        addonDTO.setName(addon.getName());
        addonDTO.setPrice(addon.getPrice());
        return addonDTO;
    }

    public static AddonEntity mapToAddon(AddonDTO addonDTO) {
        AddonEntity addon = new AddonEntity();
        addon.setId(addonDTO.getId());
        addon.setName(addonDTO.getName());
        addon.setPrice(addonDTO.getPrice());
        return addon;
    }

}
