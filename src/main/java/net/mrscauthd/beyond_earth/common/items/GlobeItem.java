package net.mrscauthd.beyond_earth.common.items;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.mrscauthd.beyond_earth.client.entities.renderers.globe.GlobeItemRenderer;

import java.util.function.Consumer;

public class GlobeItem extends BlockItem {

    @OnlyIn(Dist.CLIENT)
    private GlobeItemRenderer renderer;

    public GlobeItem(Block p_40565_, Properties p_40566_, ResourceLocation texture) {
        super(p_40565_, p_40566_);
        this.renderer = new GlobeItemRenderer<>(texture);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return GlobeItem.this.renderer;
            }
        });
    }
}
