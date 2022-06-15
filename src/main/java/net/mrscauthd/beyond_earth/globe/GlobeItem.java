package net.mrscauthd.beyond_earth.globe;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.IItemRenderProperties;
import net.mrscauthd.beyond_earth.events.ClientEventBusSubscriber;

import java.util.function.Consumer;

public class GlobeItem extends BlockItem {
    public GlobeItem(Block p_40565_, Properties p_40566_) {
        super(p_40565_, p_40566_);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return ClientEventBusSubscriber.GLOBE_RENDERER;
            }
        });
    }
}
