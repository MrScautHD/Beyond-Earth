package net.mrscauthd.beyond_earth.common.compats.mekanism;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;

public class MekanismCompat {
    public static final String MODID = "mekanism";
    public static final boolean LOADED = ModList.get().isLoaded(MODID);

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }

}