package net.mrscauthd.beyond_earth.common.data.recipes.conditions;
/**
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.config.Config;

public class ConfigSteelManagementCondition implements ICondition {

    public static final ResourceLocation NAME = new ResourceLocation(BeyondEarth.MODID, "steel_management");

    private final int level;

    public ConfigSteelManagementCondition(int level) {
        this.level = level;
    }

    @Override
    public ResourceLocation getID() {
        return NAME;
    }

    @Override
    public boolean test(IContext context) {
        return Config.STEEL_MANAGEMENT.get() < this.level;
    }

    @Override
    public String toString() {
        return "steel_management(\"" + this.level + "\")";
    }

    public static class Serializer implements IConditionSerializer<ConfigSteelManagementCondition> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, ConfigSteelManagementCondition value) {
            json.addProperty("level", value.level);
        }

        @Override
        public ConfigSteelManagementCondition read(JsonObject json) {
            return new ConfigSteelManagementCondition(GsonHelper.getAsInt(json, "level"));
        }

        @Override
        public ResourceLocation getID() {
            return ConfigSteelManagementCondition.NAME;
        }
    }
}
*/