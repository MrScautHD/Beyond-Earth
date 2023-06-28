package net.mrscauthd.beyond_earth.common.tabs;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.registries.BlockRegistry;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TabsRegistry {
    public static CreativeModeTab DEFAULT;
    public static CreativeModeTab MACHINES;
    public static CreativeModeTab BASICS;
    public static CreativeModeTab MATERIALS;
    public static CreativeModeTab GLOBES;
    public static CreativeModeTab BLOCKS;
    public static CreativeModeTab SPAWN_EGG;

    @SubscribeEvent
    public static void registerCreativeModTabs(CreativeModeTabEvent.Register event) {
        DEFAULT = event.registerCreativeModeTab(new ResourceLocation(BeyondEarth.MODID, "tab_normal"),
                builder -> builder.icon(() -> new ItemStack(ItemsRegistry.TIER_1_ROCKET_ITEM.get())).title(Component.translatable("itemGroup.tab_normal")));
        MACHINES = event.registerCreativeModeTab(new ResourceLocation(BeyondEarth.MODID, "tab_machines"),
                builder -> builder.icon(() -> new ItemStack(ItemsRegistry.NASA_WORKBENCH_ITEM.get())).title(Component.translatable("itemGroup.tab_machines")));
        BASICS = event.registerCreativeModeTab(new ResourceLocation(BeyondEarth.MODID, "tab_basics"),
                builder -> builder.icon(() -> new ItemStack(ItemsRegistry.DESH_ENGINE.get())).title(Component.translatable("itemGroup.tab_basics")));
        MATERIALS = event.registerCreativeModeTab(new ResourceLocation(BeyondEarth.MODID, "tab_materials"),
                builder -> builder.icon(() -> new ItemStack(ItemsRegistry.IRON_PLATE.get())).title(Component.translatable("itemGroup.tab_materials")));
        GLOBES = event.registerCreativeModeTab(new ResourceLocation(BeyondEarth.MODID, "tab_globes"),
                builder -> builder.icon(() -> new ItemStack(BlockRegistry.GLACIO_GLOBE_BLOCK.get())).title(Component.translatable("itemGroup.tab_globes")));
        BLOCKS = event.registerCreativeModeTab(new ResourceLocation(BeyondEarth.MODID, "tab_blocks"),
                builder -> builder.icon(() -> new ItemStack(BlockRegistry.MOON_IRON_ORE.get())).title(Component.translatable("itemGroup.tab_blocks")));
        SPAWN_EGG = event.registerCreativeModeTab(new ResourceLocation(BeyondEarth.MODID, "tab_spawn_eggs"),
                builder -> builder.icon(() -> new ItemStack(ItemsRegistry.ALIEN_SPAWN_EGG.get())).title(Component.translatable("itemGroup.tab_spawn_eggs")));
    }
}
