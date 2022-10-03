package net.mrscauthd.beyond_earth.common.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.mrscauthd.beyond_earth.BeyondEarth;

@JeiPlugin
public class Jei implements IModPlugin {
    
    private static final ResourceLocation UID = new ResourceLocation(BeyondEarth.MODID, "jei");

    @Override
    public ResourceLocation getPluginUid()
    {
        return UID;
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {
        // TODO Auto-generated method stub
        IModPlugin.super.registerCategories(registration);
    }
    
    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        // TODO Auto-generated method stub
        IModPlugin.super.registerRecipes(registration);
    }
}
