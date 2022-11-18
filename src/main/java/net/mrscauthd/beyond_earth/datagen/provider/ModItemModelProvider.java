package net.mrscauthd.beyond_earth.datagen.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        /** GENERATED MODELS */
        basicItem(ItemsRegistry.COAL_TORCH_ITEM.get());
        basicItem(ItemsRegistry.COAL_LANTERN_ITEM.get());
        basicItem(ItemsRegistry.CHEESE.get());
        basicItem(ItemsRegistry.OXYGEN_GEAR.get());
        basicItem(ItemsRegistry.OXYGEN_TANK.get());
        basicItem(ItemsRegistry.WHEEL.get());
        basicItem(ItemsRegistry.ENGINE_FRAME.get());
        basicItem(ItemsRegistry.ENGINE_FAN.get());
        basicItem(ItemsRegistry.ROCKET_NOSE_CONE.get());
        basicItem(ItemsRegistry.STEEL_ENGINE.get());
        basicItem(ItemsRegistry.DESH_ENGINE.get());
        basicItem(ItemsRegistry.OSTRUM_ENGINE.get());
        basicItem(ItemsRegistry.CALORITE_ENGINE.get());
        basicItem(ItemsRegistry.STEEL_TANK.get());
        basicItem(ItemsRegistry.DESH_TANK.get());
        basicItem(ItemsRegistry.OSTRUM_TANK.get());
        basicItem(ItemsRegistry.CALORITE_TANK.get());
        basicItem(ItemsRegistry.ROCKET_FIN.get());
        basicItem(ItemsRegistry.STEEL_INGOT.get());
        basicItem(ItemsRegistry.DESH_INGOT.get());
        basicItem(ItemsRegistry.OSTRUM_INGOT.get());
        basicItem(ItemsRegistry.CALORITE_INGOT.get());
        basicItem(ItemsRegistry.ICE_SHARD.get());
        basicItem(ItemsRegistry.IRON_PLATE.get());
        basicItem(ItemsRegistry.DESH_PLATE.get());
        basicItem(ItemsRegistry.COMPRESSED_STEEL.get());
        basicItem(ItemsRegistry.COMPRESSED_DESH.get());
        basicItem(ItemsRegistry.COMPRESSED_OSTRUM.get());
        basicItem(ItemsRegistry.COMPRESSED_CALORITE.get());
        basicItem(ItemsRegistry.STEEL_NUGGET.get());
        basicItem(ItemsRegistry.DESH_NUGGET.get());
        basicItem(ItemsRegistry.OSTRUM_NUGGET.get());
        basicItem(ItemsRegistry.CALORITE_NUGGET.get());
        basicItem(ItemsRegistry.RAW_DESH.get());
        basicItem(ItemsRegistry.RAW_OSTRUM.get());
        basicItem(ItemsRegistry.RAW_CALORITE.get());
        basicItem(ItemsRegistry.FUEL_BUCKET.get());
        basicItem(ItemsRegistry.OIL_BUCKET.get());

        /** HANDHELD MODELS */
        handheldModel(ItemsRegistry.IRON_ROD);
        handheldModel(ItemsRegistry.HAMMER);

        /** SPAWN EGG MODELS */
        spawnEggModel(ItemsRegistry.ALIEN_SPAWN_EGG);
        spawnEggModel(ItemsRegistry.ALIEN_ZOMBIE_SPAWN_EGG);
        spawnEggModel(ItemsRegistry.STAR_CRAWLER_SPAWN_EGG);
        spawnEggModel(ItemsRegistry.PYGRO_SPAWN_EGG);
        spawnEggModel(ItemsRegistry.PYGRO_BRUTE_SPAWN_EGG);
        spawnEggModel(ItemsRegistry.MOGLER_SPAWN_EGG);
        spawnEggModel(ItemsRegistry.MARTIAN_RAPTOR_SPAWN_EGG);

        /** ARMOR ITEM MODELS */
        armorItemModel(ItemsRegistry.SPACE_HELMET);
        armorItemModel(ItemsRegistry.SPACE_SUIT);
        armorItemModel(ItemsRegistry.SPACE_PANTS);
        armorItemModel(ItemsRegistry.SPACE_BOOTS);
        armorItemModel(ItemsRegistry.NETHERITE_SPACE_HELMET);
        armorItemModel(ItemsRegistry.NETHERITE_SPACE_SUIT);
        armorItemModel(ItemsRegistry.NETHERITE_SPACE_PANTS);
        armorItemModel(ItemsRegistry.NETHERITE_SPACE_BOOTS);
        armorItemModel(ItemsRegistry.JET_HELMET);
        armorItemModel(ItemsRegistry.JET_SUIT);
        armorItemModel(ItemsRegistry.JET_PANTS);
        armorItemModel(ItemsRegistry.JET_BOOTS);

        /** TEMPLATE MODELS */
        templateModel(ItemsRegistry.EARTH_GLOBE_ITEM, "globe", "block/globes", "particle");
        templateModel(ItemsRegistry.MOON_GLOBE_ITEM, "globe", "block/globes", "particle");
        templateModel(ItemsRegistry.MARS_GLOBE_ITEM, "globe", "block/globes", "particle");
        templateModel(ItemsRegistry.MERCURY_GLOBE_ITEM, "globe", "block/globes", "particle");
        templateModel(ItemsRegistry.VENUS_GLOBE_ITEM, "globe", "block/globes", "particle");
        templateModel(ItemsRegistry.GLACIO_GLOBE_ITEM, "globe", "block/globes", "particle");

        /** BLOCK ITEM MODELS */
        blockItemModel(ItemsRegistry.STEEL_BLOCK_ITEM);
        blockItemModel(ItemsRegistry.DESH_BLOCK_ITEM);
        blockItemModel(ItemsRegistry.OSTRUM_BLOCK_ITEM);
        blockItemModel(ItemsRegistry.CALORITE_BLOCK_ITEM);
        blockItemModel(ItemsRegistry.RAW_DESH_BLOCK_ITEM);
        blockItemModel(ItemsRegistry.RAW_OSTRUM_BLOCK_ITEM);
        blockItemModel(ItemsRegistry.RAW_CALORITE_BLOCK_ITEM);
        blockItemModel(ItemsRegistry.IRON_PLATING_BLOCK_ITEM);
        blockItemModel(ItemsRegistry.DESH_PILLAR_BLOCK_ITEM);
        blockItemModel(ItemsRegistry.DESH_PLATING_BLOCK_ITEM);
        blockItemModel(ItemsRegistry.BLUE_IRON_PILLAR_BLOCK_ITEM);
        blockItemModel(ItemsRegistry.INFERNAL_SPIRE_ITEM);
        blockItemModel(ItemsRegistry.BARRICADE_BLOCK_ITEM);
        blockItemModel(ItemsRegistry.METEORITE_ITEM);
        blockItemModel(ItemsRegistry.MOON_STONE_ITEM);
        blockItemModel(ItemsRegistry.MOON_STONE_BRICKS_ITEM);
        blockItemModel(ItemsRegistry.CRACKED_MOON_STONE_BRICKS_ITEM);
        blockItemModel(ItemsRegistry.MOON_STONE_BRICK_SLAB_ITEM);
        blockItemModel(ItemsRegistry.MOON_STONE_BRICK_STAIRS_ITEM);
        blockItemModel(ItemsRegistry.MARS_STONE_ITEM);
        blockItemModel(ItemsRegistry.MARS_STONE_BRICKS_ITEM);
        blockItemModel(ItemsRegistry.CRACKED_MARS_STONE_BRICKS_ITEM);
        blockItemModel(ItemsRegistry.MARS_STONE_BRICK_SLAB_ITEM);
        blockItemModel(ItemsRegistry.MARS_STONE_BRICK_STAIRS_ITEM);
        blockItemModel(ItemsRegistry.MERCURY_STONE_ITEM);
        blockItemModel(ItemsRegistry.MERCURY_STONE_BRICKS_ITEM);
        blockItemModel(ItemsRegistry.CRACKED_MERCURY_STONE_BRICKS_ITEM);
        blockItemModel(ItemsRegistry.MERCURY_STONE_BRICK_SLAB_ITEM);
        blockItemModel(ItemsRegistry.MERCURY_STONE_BRICK_STAIRS_ITEM);
        blockItemModel(ItemsRegistry.VENUS_STONE_ITEM);
        blockItemModel(ItemsRegistry.VENUS_STONE_BRICKS_ITEM);
        blockItemModel(ItemsRegistry.CRACKED_VENUS_STONE_BRICKS_ITEM);
        blockItemModel(ItemsRegistry.VENUS_STONE_BRICK_SLAB_ITEM);
        blockItemModel(ItemsRegistry.VENUS_STONE_BRICK_STAIRS_ITEM);
        blockItemModel(ItemsRegistry.VENUS_SANDSTONE_ITEM);
        blockItemModel(ItemsRegistry.VENUS_SANDSTONE_BRICKS_ITEM);
        blockItemModel(ItemsRegistry.CRACKED_VENUS_SANDSTONE_BRICKS_ITEM);
        blockItemModel(ItemsRegistry.VENUS_SANDSTONE_BRICK_SLAB_ITEM);
        blockItemModel(ItemsRegistry.VENUS_SANDSTONE_BRICK_STAIRS_ITEM);
        blockItemModel(ItemsRegistry.PERMAFROST_ITEM);
        blockItemModel(ItemsRegistry.GLACIO_STONE_ITEM);
        blockItemModel(ItemsRegistry.GLACIO_STONE_BRICKS_ITEM);
        blockItemModel(ItemsRegistry.CRACKED_GLACIO_STONE_BRICKS_ITEM);
        blockItemModel(ItemsRegistry.GLACIO_STONE_BRICK_SLAB_ITEM);
        blockItemModel(ItemsRegistry.GLACIO_STONE_BRICK_STAIRS_ITEM);
        blockItemModel(ItemsRegistry.MOON_SAND_ITEM);
        blockItemModel(ItemsRegistry.MARS_SAND_ITEM);
        blockItemModel(ItemsRegistry.VENUS_SAND_ITEM);
        blockItemModel(ItemsRegistry.MOON_CHEESE_ORE_ITEM);
        blockItemModel(ItemsRegistry.MOON_DESH_ORE_ITEM);
        blockItemModel(ItemsRegistry.MOON_IRON_ORE_ITEM);
        blockItemModel(ItemsRegistry.MOON_ICE_SHARD_ITEM);
        blockItemModel(ItemsRegistry.MARS_IRON_ORE_ITEM);
        blockItemModel(ItemsRegistry.MARS_DIAMOND_ORE_ITEM);
        blockItemModel(ItemsRegistry.MARS_OSTRUM_ORE_ITEM);
        blockItemModel(ItemsRegistry.MARS_ICE_SHARD_ORE_ITEM);
        blockItemModel(ItemsRegistry.MERCURY_IRON_ORE_ITEM);
        blockItemModel(ItemsRegistry.VENUS_COAL_ORE_ITEM);
        blockItemModel(ItemsRegistry.VENUS_GOLD_ORE_ITEM);
        blockItemModel(ItemsRegistry.VENUS_DIAMOND_ORE_ITEM);
        blockItemModel(ItemsRegistry.VENUS_CALORITE_ORE_ITEM);
        blockItemModel(ItemsRegistry.GLACIO_ICE_SHARD_ORE_ITEM);
        blockItemModel(ItemsRegistry.GLACIO_COAL_ORE_ITEM);
        blockItemModel(ItemsRegistry.GLACIO_COPPER_ORE_ITEM);
        blockItemModel(ItemsRegistry.GLACIO_IRON_ORE_ITEM);
        blockItemModel(ItemsRegistry.GLACIO_LAPIS_ORE_ITEM);
        blockItemModel(ItemsRegistry.ROCKET_LAUNCH_PAD_ITEM);
    }

    private ItemModelBuilder handheldModel(RegistryObject<?> item) {
        return getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile("item/handheld")).texture("layer0", new ResourceLocation(this.modid, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder blockItemModel(RegistryObject<?> item) {
        return getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile(new ResourceLocation(this.modid, "block/" + item.getId().getPath())));
    }

    private ItemModelBuilder spawnEggModel(RegistryObject<?> item) {
        return getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile("item/template_spawn_egg"));
    }

    private ItemModelBuilder armorItemModel(RegistryObject<?> item) {
        return getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", new ResourceLocation(this.modid, "item/armor/" + item.getId().getPath()));
    }

    private ItemModelBuilder templateModel(RegistryObject<?> item, String template, String texturePath, String textureType) {
        return getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile(new ResourceLocation(this.modid, "template/" + template))).texture(textureType, new ResourceLocation(this.modid, texturePath + "/" + item.getId().getPath()));
    }
}
