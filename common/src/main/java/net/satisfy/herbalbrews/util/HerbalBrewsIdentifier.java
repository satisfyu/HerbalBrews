package net.satisfy.herbalbrews.util;

import net.minecraft.resources.ResourceLocation;
import net.satisfy.herbalbrews.HerbalBrews;

public class HerbalBrewsIdentifier extends ResourceLocation {

    public HerbalBrewsIdentifier(String path) {
        super(HerbalBrews.MOD_ID, path);
    }

    public static String asString(String path) {
        return (HerbalBrews.MOD_ID + ":" + path);
    }
}
