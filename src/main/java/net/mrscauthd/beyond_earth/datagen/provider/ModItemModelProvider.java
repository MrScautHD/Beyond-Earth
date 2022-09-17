package net.mrscauthd.beyond_earth.datagen.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        /** GENERATED MODEL */
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

        /** HANDHELD MODEL */
        handheldModel(ItemsRegistry.IRON_ROD);
        handheldModel(ItemsRegistry.HAMMER);

        /** SPAWN EGG MODEL */
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
    }

    private ItemModelBuilder handheldModel(RegistryObject<Item> item) {
        return getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile("item/handheld")).texture("layer0", new ResourceLocation(this.modid, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder spawnEggModel(RegistryObject<ForgeSpawnEggItem> item) {
        return getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile("item/template_spawn_egg"));
    }

    private ItemModelBuilder armorItemModel(RegistryObject<Item> item) {
        return getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", new ResourceLocation(this.modid, "item/armor/" + item.getId().getPath()));
    }


    /*
    private ItemModelBuilder cubeModel(RegistryObject<Block> block) {
        return cube(block.getId().getPath(), new ResourceLocation());
    }*/
}
