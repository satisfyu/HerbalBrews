package net.satisfy.herbalbrews.core.util;

import net.minecraft.resources.ResourceLocation;
import net.satisfy.herbalbrews.HerbalBrews;

public class HerbalBrewsIdentifier extends ResourceLocation {
    public HerbalBrewsIdentifier(String path) {
        super(HerbalBrews.MOD_ID, path);
    }
}
