package net.mrscauthd.beyond_earth.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.crafting.RocketPart;

import java.util.function.Supplier;

public class RocketPartsRegistry {

    public static final DeferredRegister<RocketPart> ROCKET_PARTS = DeferredRegister.create(new ResourceLocation(BeyondEarthMod.MODID, "rocket_part"), BeyondEarthMod.MODID);
    public static final Supplier<IForgeRegistry<RocketPart>> ROCKET_PARTS_REGISTRY = ROCKET_PARTS.makeRegistry(RocketPart.class, RegistryBuilder::new);

    /** ROCKET PARTS */
    public static final RegistryObject<RocketPart> ROCKET_PART_EMPTY = ROCKET_PARTS.register("emtpy", () -> RocketPart.EMPTY);
    public static final RegistryObject<RocketPart> ROCKET_PART_NOSE = ROCKET_PARTS.register("nose", () -> new RocketPart(1));
    public static final RegistryObject<RocketPart> ROCKET_PART_BODY = ROCKET_PARTS.register("body", () -> new RocketPart(6));
    public static final RegistryObject<RocketPart> ROCKET_PART_TANK = ROCKET_PARTS.register("tank", () -> new RocketPart(2));
    public static final RegistryObject<RocketPart> ROCKET_PART_FIN_LEFT = ROCKET_PARTS.register("fin_left", () -> new RocketPart(2));
    public static final RegistryObject<RocketPart> ROCKET_PART_FIN_RIGHT = ROCKET_PARTS.register("fin_right", () -> new RocketPart(2));
    public static final RegistryObject<RocketPart> ROCKET_PART_ENGINE = ROCKET_PARTS.register("engine", () -> new RocketPart(1));
}
