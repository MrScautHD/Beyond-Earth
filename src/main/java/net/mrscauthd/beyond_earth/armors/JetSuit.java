package net.mrscauthd.beyond_earth.armors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.capabilities.oxygen.CapabilityOxygen;
import net.mrscauthd.beyond_earth.capabilities.oxygen.IOxygenStorage;
import net.mrscauthd.beyond_earth.capabilities.oxygen.OxygenUtil;
import net.mrscauthd.beyond_earth.capabilities.oxygen.SpaceSuitCapabilityProvider;
import net.mrscauthd.beyond_earth.renderers.armors.JetSuitModel;
import net.mrscauthd.beyond_earth.events.Methods;
import net.mrscauthd.beyond_earth.gauge.GaugeTextHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class JetSuit {

    public static class OxygenMask extends ArmorItem {
        public OxygenMask(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
            super(p_40386_, p_40387_, p_40388_);
        }

        @Override
        public void initializeClient(Consumer<IItemRenderProperties> consumer) {
            consumer.accept(new IItemRenderProperties() {

                @Override
                public HumanoidModel getBaseArmorModel(LivingEntity living, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> defaultModel) {

                    Map<String, ModelPart> map = Map.of("head", new JetSuitModel.JET_SUIT_P1(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JET_SUIT_P1.LAYER_LOCATION)).head,

                            "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())
                    );

                    ModelPart modelPart = new ModelPart(Collections.emptyList(), map);
                    JetSuitModel.JET_SUIT_P1 armorModel = new JetSuitModel.JET_SUIT_P1(modelPart);

                    armorModel.entity = living;

                    return armorModel;
                }
            });
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return BeyondEarth.MODID + ":textures/armor/jet_suit_oxygen_mask.png";
        }
    }

    public static class Suit extends ArmorItem {
        public Suit(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
            super(p_40386_, p_40387_, p_40388_);
        }

        @Override
        public void initializeClient(Consumer<IItemRenderProperties> consumer) {
            consumer.accept(new IItemRenderProperties() {

                @Override
                public HumanoidModel getBaseArmorModel(LivingEntity living, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> defaultModel) {

                    Map<String, ModelPart> map = Map.of(
                            "body", new JetSuitModel.JET_SUIT_P1(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JET_SUIT_P1.LAYER_LOCATION)).body,
                            "right_arm", new JetSuitModel.JET_SUIT_P1(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JET_SUIT_P1.LAYER_LOCATION)).rightArm,
                            "left_arm", new JetSuitModel.JET_SUIT_P1(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JET_SUIT_P1.LAYER_LOCATION)).leftArm,

                            "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())
                    );

                    ModelPart modelPart = new ModelPart(Collections.emptyList(), map);
                    JetSuitModel.JET_SUIT_P1 armorModel = new JetSuitModel.JET_SUIT_P1(modelPart);

                    armorModel.entity = living;

                    return armorModel;
                }
            });
        }

        @Override
        public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
            return Methods.jetSuitCheck(entity);
        }

        @Override
        public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
            if (!entity.level.isClientSide) {
                int nextFlightTick = flightTicks + 1;
                if (nextFlightTick % 10 == 0) {
                    if (nextFlightTick % 20 == 0) {
                        stack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(EquipmentSlot.CHEST));
                    }
                    entity.gameEvent(GameEvent.ELYTRA_GLIDE);
                }
            }
            return false;
        }

        @Override
        public void fillItemCategory(CreativeModeTab p_41391_, NonNullList<ItemStack> p_41392_) {
            super.fillItemCategory(p_41391_, p_41392_);
            if (this.allowedIn(p_41391_)) {
                ItemStack itemStack = new ItemStack(this);
                IOxygenStorage oxygenStorage = itemStack.getCapability(CapabilityOxygen.OXYGEN).orElse(null);

                oxygenStorage.setOxygenStored(oxygenStorage.getMaxOxygenStored());
                p_41392_.add(itemStack);
            }
        }

        @Override
        public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
            return new SpaceSuitCapabilityProvider(stack, 48000);
        }

        @Override
        public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
            super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
            IOxygenStorage oxygenStorage = OxygenUtil.getItemStackOxygenStorage(p_41421_);
            p_41423_.add(Component.literal("Energy: 0"));
            p_41423_.add(GaugeTextHelper.buildSpacesuitOxygenTooltip(oxygenStorage));
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return BeyondEarth.MODID + ":textures/armor/jet_suit.png";
        }

        @Override
        public void onArmorTick(ItemStack stack, Level world, Player player) {
            Methods.extractArmorOxygenUsingTimer(stack, player);
        }
    }

    public static class Pants extends ArmorItem {
        public Pants(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
            super(p_40386_, p_40387_, p_40388_);
        }

        @Override
        public void initializeClient(Consumer<IItemRenderProperties> consumer) {
            consumer.accept(new IItemRenderProperties() {

                @Override
                public HumanoidModel getBaseArmorModel(LivingEntity living, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> defaultModel) {

                    Map<String, ModelPart> map = Map.of(
                            "right_leg", new JetSuitModel.JET_SUIT_P2(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JET_SUIT_P2.LAYER_LOCATION)).rightLeg,
                            "left_leg", new JetSuitModel.JET_SUIT_P2(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JET_SUIT_P2.LAYER_LOCATION)).leftLeg,

                            "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap())

                    );

                    ModelPart modelPart = new ModelPart(Collections.emptyList(), map);
                    JetSuitModel.JET_SUIT_P2 armorModel = new JetSuitModel.JET_SUIT_P2(modelPart);

                    armorModel.entity = living;

                    return armorModel;
                }
            });
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return BeyondEarth.MODID + ":textures/armor/jet_suit_pants.png";
        }
    }

    public static class Boots extends ArmorItem {
        public Boots(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
            super(p_40386_, p_40387_, p_40388_);
        }

        @Override
        public void initializeClient(Consumer<IItemRenderProperties> consumer) {
            consumer.accept(new IItemRenderProperties() {

                @Override
                public HumanoidModel getBaseArmorModel(LivingEntity living, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> defaultModel) {

                    Map<String, ModelPart> map = Map.of(
                            "right_leg", new JetSuitModel.JET_SUIT_P1(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JET_SUIT_P1.LAYER_LOCATION)).rightLeg,
                            "left_leg", new JetSuitModel.JET_SUIT_P1(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JET_SUIT_P1.LAYER_LOCATION)).leftLeg,

                            "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap())

                    );

                    ModelPart modelPart = new ModelPart(Collections.emptyList(), map);
                    JetSuitModel.JET_SUIT_P1 armorModel = new JetSuitModel.JET_SUIT_P1(modelPart);

                    armorModel.entity = living;

                    return armorModel;
                }
            });
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return BeyondEarth.MODID + ":textures/armor/jet_suit.png";
        }
    }
}
