package net.mrscauthd.beyond_earth.globe;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import net.mrscauthd.beyond_earth.entities.renderer.globe.GlobeItemRenderer;

import java.util.function.Consumer;

public class GlobeItem extends BlockItem {

    @OnlyIn(Dist.CLIENT)
    private GlobeItemRenderer renderer;

    public GlobeItem(Block p_40565_, Properties p_40566_, ResourceLocation texture) {
        super(p_40565_, p_40566_);
        this.renderer = new GlobeItemRenderer<>(texture);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return GlobeItem.this.renderer;
            }
        });
    }
}
