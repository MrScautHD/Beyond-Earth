package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TabsRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BeyondEarth.MODID);

    public static RegistryObject<CreativeModeTab> DEFAULT = CREATIVE_MOD_TAB.register("tab_normal", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tab_normal"))
            .icon(() -> new ItemStack(ItemsRegistry.ROCKET_ITEM.get()))
            .displayItems((feature, builder) -> {
                builder.accept(ItemsRegistry.ROCKET_ITEM.get());
                builder.accept(ItemsRegistry.ROVER_ITEM.get());
                builder.accept(ItemsRegistry.SPACE_HELMET.get());
                builder.accept(ItemsRegistry.SPACE_SUIT.get());
                builder.accept(ItemsRegistry.SPACE_PANTS.get());
                builder.accept(ItemsRegistry.SPACE_BOOTS.get());
                builder.accept(ItemsRegistry.NETHERITE_SPACE_HELMET.get());
                builder.accept(ItemsRegistry.NETHERITE_SPACE_SUIT.get());
                builder.accept(ItemsRegistry.NETHERITE_SPACE_PANTS.get());
                builder.accept(ItemsRegistry.NETHERITE_SPACE_BOOTS.get());
                builder.accept(ItemsRegistry.JET_HELMET.get());
                builder.accept(ItemsRegistry.JET_SUIT.get());
                builder.accept(ItemsRegistry.JET_PANTS.get());
                builder.accept(ItemsRegistry.JET_BOOTS.get());
                builder.accept(ItemsRegistry.ROCKET_LAUNCH_PAD_ITEM.get());
                builder.accept(ItemsRegistry.FLAG_ITEM.get());
                builder.accept(ItemsRegistry.COAL_TORCH_ITEM.get());
                builder.accept(ItemsRegistry.COAL_LANTERN_ITEM.get());
                builder.accept(ItemsRegistry.CHEESE.get());
                builder.accept(ItemsRegistry.FUEL_BUCKET.get());
                builder.accept(ItemsRegistry.OIL_BUCKET.get());
            }).build());

    public static RegistryObject<CreativeModeTab> MACHINES = CREATIVE_MOD_TAB.register("tab_machines", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tab_machines"))
            .icon(() -> new ItemStack(ItemsRegistry.NASA_WORKBENCH_ITEM.get()))
            .withTabsBefore(DEFAULT.getId())
            .displayItems((feature, builder) -> {
                builder.accept(ItemsRegistry.NASA_WORKBENCH_ITEM.get());
                builder.accept(ItemsRegistry.SOLAR_PANEL_ITEM.get());
                builder.accept(ItemsRegistry.COAL_GENERATOR_ITEM.get());
                builder.accept(ItemsRegistry.COMPRESSOR_ITEM.get());
                builder.accept(ItemsRegistry.FUEL_REFINERY_ITEM.get());
                builder.accept(ItemsRegistry.OXYGEN_LOADER_ITEM.get());
                builder.accept(ItemsRegistry.OXYGEN_BUBBLE_DISTRIBUTOR_ITEM.get());
                builder.accept(ItemsRegistry.WATER_PUMP_ITEM.get());
            }).build());

    public static RegistryObject<CreativeModeTab> BASICS = CREATIVE_MOD_TAB.register("tab_basics", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tab_basics"))
            .icon(() -> new ItemStack(ItemsRegistry.DESH_ENGINE.get()))
            .withTabsBefore(MACHINES.getId())
            .displayItems((feature, builder) -> {
                builder.accept(ItemsRegistry.COAL_TORCH_ITEM.get());
                builder.accept(ItemsRegistry.HAMMER.get());
                builder.accept(ItemsRegistry.IRON_ROD.get());
                builder.accept(ItemsRegistry.OXYGEN_GEAR.get());
                builder.accept(ItemsRegistry.OXYGEN_TANK.get());
                builder.accept(ItemsRegistry.WHEEL.get());
                builder.accept(ItemsRegistry.ENGINE_FAN.get());
                builder.accept(ItemsRegistry.ENGINE_FRAME.get());
                builder.accept(ItemsRegistry.ROCKET_FIN.get());
                builder.accept(ItemsRegistry.ROCKET_NOSE_CONE.get());
                builder.accept(ItemsRegistry.STEEL_ENGINE.get());
                builder.accept(ItemsRegistry.DESH_ENGINE.get());
                builder.accept(ItemsRegistry.OSTRUM_ENGINE.get());
                builder.accept(ItemsRegistry.CALORITE_ENGINE.get());
                builder.accept(ItemsRegistry.STEEL_TANK.get());
                builder.accept(ItemsRegistry.DESH_TANK.get());
                builder.accept(ItemsRegistry.OSTRUM_TANK.get());
                builder.accept(ItemsRegistry.CALORITE_TANK.get());
            }).build());

    public static RegistryObject<CreativeModeTab> MATERIALS = CREATIVE_MOD_TAB.register("tab_materials", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tab_materials"))
            .icon(() -> new ItemStack(ItemsRegistry.IRON_PLATE.get()))
            .withTabsBefore(BASICS.getId())
            .displayItems((feature, builder) -> {
                builder.accept(ItemsRegistry.STEEL_INGOT.get());
                builder.accept(ItemsRegistry.DESH_INGOT.get());
                builder.accept(ItemsRegistry.OSTRUM_INGOT.get());
                builder.accept(ItemsRegistry.CALORITE_INGOT.get());
                builder.accept(ItemsRegistry.ICE_SHARD.get());
                builder.accept(ItemsRegistry.IRON_PLATE.get());
                builder.accept(ItemsRegistry.DESH_PLATE.get());
                builder.accept(ItemsRegistry.COMPRESSED_STEEL.get());
                builder.accept(ItemsRegistry.COMPRESSED_DESH.get());
                builder.accept(ItemsRegistry.COMPRESSED_OSTRUM.get());
                builder.accept(ItemsRegistry.COMPRESSED_CALORITE.get());
                builder.accept(ItemsRegistry.STEEL_NUGGET.get());
                builder.accept(ItemsRegistry.DESH_NUGGET.get());
                builder.accept(ItemsRegistry.OSTRUM_NUGGET.get());
                builder.accept(ItemsRegistry.CALORITE_NUGGET.get());
                builder.accept(ItemsRegistry.RAW_DESH.get());
                builder.accept(ItemsRegistry.RAW_OSTRUM.get());
                builder.accept(ItemsRegistry.RAW_CALORITE.get());
            }).build());

    public static RegistryObject<CreativeModeTab> GLOBES = CREATIVE_MOD_TAB.register("tab_globes", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tab_globes"))
            .icon(() -> new ItemStack(ItemsRegistry.GLACIO_GLOBE_ITEM.get()))
            .withTabsBefore(MATERIALS.getId())
            .displayItems((feature, builder) -> {
                builder.accept(ItemsRegistry.EARTH_GLOBE_ITEM.get());
                builder.accept(ItemsRegistry.MOON_GLOBE_ITEM.get());
                builder.accept(ItemsRegistry.MARS_GLOBE_ITEM.get());
                builder.accept(ItemsRegistry.VENUS_GLOBE_ITEM.get());
                builder.accept(ItemsRegistry.MERCURY_GLOBE_ITEM.get());
                builder.accept(ItemsRegistry.GLACIO_GLOBE_ITEM.get());
            }).build());

    public static RegistryObject<CreativeModeTab> BLOCKS = CREATIVE_MOD_TAB.register("tab_blocks", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tab_blocks"))
            .icon(() -> new ItemStack(ItemsRegistry.MOON_STONE_BRICKS_ITEM.get()))
            .withTabsBefore(GLOBES.getId())
            .displayItems((feature, builder) -> {
                builder.accept(ItemsRegistry.STEEL_BLOCK_ITEM.get());
                builder.accept(ItemsRegistry.DESH_BLOCK_ITEM.get());
                builder.accept(ItemsRegistry.OSTRUM_BLOCK_ITEM.get());
                builder.accept(ItemsRegistry.CALORITE_BLOCK_ITEM.get());
                builder.accept(ItemsRegistry.RAW_DESH_BLOCK_ITEM.get());
                builder.accept(ItemsRegistry.RAW_OSTRUM_BLOCK_ITEM.get());
                builder.accept(ItemsRegistry.RAW_CALORITE_BLOCK_ITEM.get());
                builder.accept(ItemsRegistry.IRON_PLATING_BLOCK_ITEM.get());
                builder.accept(ItemsRegistry.IRON_MARK_BLOCK_ITEM.get());
                builder.accept(ItemsRegistry.DESH_PILLAR_BLOCK_ITEM.get());
                builder.accept(ItemsRegistry.DESH_PLATING_BLOCK_ITEM.get());
                builder.accept(ItemsRegistry.BLUE_IRON_PILLAR_BLOCK_ITEM.get());
                builder.accept(ItemsRegistry.INFERNAL_SPIRE_ITEM.get());
                builder.accept(ItemsRegistry.BARRICADE_BLOCK_ITEM.get());
                builder.accept(ItemsRegistry.METEORITE_ITEM.get());
                builder.accept(ItemsRegistry.MOON_STONE_ITEM.get());
                builder.accept(ItemsRegistry.MOON_STONE_BRICKS_ITEM.get());
                builder.accept(ItemsRegistry.MOON_STONE_BRICK_SLAB_ITEM.get());
                builder.accept(ItemsRegistry.MOON_STONE_BRICK_STAIRS_ITEM.get());
                builder.accept(ItemsRegistry.CRACKED_MOON_STONE_BRICKS_ITEM.get());
                builder.accept(ItemsRegistry.MARS_STONE_ITEM.get());
                builder.accept(ItemsRegistry.MARS_STONE_BRICKS_ITEM.get());
                builder.accept(ItemsRegistry.MARS_STONE_BRICK_SLAB_ITEM.get());
                builder.accept(ItemsRegistry.MARS_STONE_BRICK_STAIRS_ITEM.get());
                builder.accept(ItemsRegistry.CRACKED_MARS_STONE_BRICKS_ITEM.get());
                builder.accept(ItemsRegistry.MERCURY_STONE_ITEM.get());
                builder.accept(ItemsRegistry.MERCURY_STONE_BRICKS_ITEM.get());
                builder.accept(ItemsRegistry.MERCURY_STONE_BRICK_SLAB_ITEM.get());
                builder.accept(ItemsRegistry.MERCURY_STONE_BRICK_STAIRS_ITEM.get());
                builder.accept(ItemsRegistry.CRACKED_MERCURY_STONE_BRICKS_ITEM.get());
                builder.accept(ItemsRegistry.VENUS_STONE_ITEM.get());
                builder.accept(ItemsRegistry.VENUS_STONE_BRICKS_ITEM.get());
                builder.accept(ItemsRegistry.VENUS_STONE_BRICK_SLAB_ITEM.get());
                builder.accept(ItemsRegistry.VENUS_STONE_BRICK_STAIRS_ITEM.get());
                builder.accept(ItemsRegistry.CRACKED_VENUS_STONE_BRICKS_ITEM.get());
                builder.accept(ItemsRegistry.VENUS_SANDSTONE_ITEM.get());
                builder.accept(ItemsRegistry.VENUS_SANDSTONE_BRICKS_ITEM.get());
                builder.accept(ItemsRegistry.VENUS_SANDSTONE_BRICK_SLAB_ITEM.get());
                builder.accept(ItemsRegistry.VENUS_SANDSTONE_BRICK_STAIRS_ITEM.get());
                builder.accept(ItemsRegistry.CRACKED_VENUS_SANDSTONE_BRICKS_ITEM.get());
                builder.accept(ItemsRegistry.PERMAFROST_ITEM.get());
                builder.accept(ItemsRegistry.GLACIO_STONE_ITEM.get());
                builder.accept(ItemsRegistry.GLACIO_STONE_BRICKS_ITEM.get());
                builder.accept(ItemsRegistry.GLACIO_STONE_BRICK_SLAB_ITEM.get());
                builder.accept(ItemsRegistry.GLACIO_STONE_BRICK_STAIRS_ITEM.get());
                builder.accept(ItemsRegistry.CRACKED_GLACIO_STONE_BRICKS_ITEM.get());
                builder.accept(ItemsRegistry.MOON_SAND_ITEM.get());
                builder.accept(ItemsRegistry.MARS_SAND_ITEM.get());
                builder.accept(ItemsRegistry.VENUS_SAND_ITEM.get());
                builder.accept(ItemsRegistry.MOON_CHEESE_ORE_ITEM.get());
                builder.accept(ItemsRegistry.MOON_DESH_ORE_ITEM.get());
                builder.accept(ItemsRegistry.MOON_IRON_ORE_ITEM.get());
                builder.accept(ItemsRegistry.MOON_ICE_SHARD_ITEM.get());
                builder.accept(ItemsRegistry.MARS_IRON_ORE_ITEM.get());
                builder.accept(ItemsRegistry.MARS_DIAMOND_ORE_ITEM.get());
                builder.accept(ItemsRegistry.MARS_OSTRUM_ORE_ITEM.get());
                builder.accept(ItemsRegistry.MARS_ICE_SHARD_ORE_ITEM.get());
                builder.accept(ItemsRegistry.MERCURY_IRON_ORE_ITEM.get());
                builder.accept(ItemsRegistry.VENUS_COAL_ORE_ITEM.get());
                builder.accept(ItemsRegistry.VENUS_GOLD_ORE_ITEM.get());
                builder.accept(ItemsRegistry.VENUS_DIAMOND_ORE_ITEM.get());
                builder.accept(ItemsRegistry.VENUS_CALORITE_ORE_ITEM.get());
                builder.accept(ItemsRegistry.GLACIO_ICE_SHARD_ORE_ITEM.get());
                builder.accept(ItemsRegistry.GLACIO_COAL_ORE_ITEM.get());
                builder.accept(ItemsRegistry.GLACIO_COPPER_ORE_ITEM.get());
                builder.accept(ItemsRegistry.GLACIO_IRON_ORE_ITEM.get());
                builder.accept(ItemsRegistry.GLACIO_LAPIS_ORE_ITEM.get());
            }).build());

    public static RegistryObject<CreativeModeTab> SPAWN_EGGS = CREATIVE_MOD_TAB.register("tab_spawn_eggs", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tab_spawn_eggs"))
            .icon(() -> new ItemStack(ItemsRegistry.ALIEN_SPAWN_EGG.get()))
            .withTabsBefore(BLOCKS.getId())
            .displayItems((feature, builder) -> {
                builder.accept(ItemsRegistry.ALIEN_SPAWN_EGG.get());
                builder.accept(ItemsRegistry.ALIEN_ZOMBIE_SPAWN_EGG.get());
                builder.accept(ItemsRegistry.STAR_CRAWLER_SPAWN_EGG.get());
                builder.accept(ItemsRegistry.PYGRO_SPAWN_EGG.get());
                builder.accept(ItemsRegistry.PYGRO_BRUTE_SPAWN_EGG.get());
                builder.accept(ItemsRegistry.MOGLER_SPAWN_EGG.get());
                builder.accept(ItemsRegistry.MARTIAN_RAPTOR_SPAWN_EGG.get());
            }).build());
}
