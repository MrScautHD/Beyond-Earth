package net.mrscauthd.beyond_earth.common.armors;

import com.google.common.collect.Maps;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenProvider;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenStorage;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.registries.EffectRegistry;
import net.mrscauthd.beyond_earth.common.util.Methods;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public abstract class ISpaceArmor extends ArmorItem {
    private static final HashMap<String, ResourceLocation> TEXTURES = Maps.newHashMap();

    public ISpaceArmor(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties);
    }

    public static abstract class Helmet extends ISpaceArmor {
        public Helmet(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
            super(armorMaterial, equipmentSlot, properties);
        }
    }

    public static abstract class Chestplate extends ISpaceArmor {
        public float oxygenTime;

        public Chestplate(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
            super(armorMaterial, equipmentSlot, properties);
        }

        @Override
        public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list) {
            super.fillItemCategory(tab, list);
            if (this.allowedIn(tab)) {
                ItemStack itemStack = new ItemStack(this);
                OxygenStorage oxygen = itemStack.getCapability(OxygenProvider.OXYGEN).orElse(null);

                if (oxygen != null) {
                    oxygen.setOxygen(oxygen.getMaxCapacity());
                }

                list.add(itemStack);
            }
        }

        @Override
        public void appendHoverText(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
            super.appendHoverText(itemStack, level, list, tooltipFlag);
            OxygenStorage oxygen = itemStack.getCapability(OxygenProvider.OXYGEN).orElse(null);

            if (oxygen != null) {
                list.add(Component.translatable("general." + BeyondEarth.MODID + ".oxygen").append(": ").withStyle(ChatFormatting.BLUE).append("\u00A76" + oxygen.getOxygen() + " mb" +  "\u00A78" + " | " + "\u00A7c" + oxygen.getMaxCapacity() + " mb"));
            }
        }

        @Override
        public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
            return new OxygenProvider(this.getOxygenCapacity());
        }

        @Override
        public void onArmorTick(ItemStack stack, Level level, Player player) {
            super.onArmorTick(stack, level, player);

            this.calculateOxygenTime(stack, player);
        }

        public void calculateOxygenTime(ItemStack stack, Player player) { //TODO ADD FLUID TYPE SUPPORT
            if (!player.getAbilities().instabuild && !player.isSpectator() && Methods.isLivingInAnySpaceSuits(player) && !player.hasEffect(EffectRegistry.OXYGEN_EFFECT.get()) && Config.PLAYER_OXYGEN_SYSTEM.get() && (Methods.isSpaceLevelWithoutOxygen(player.level) || player.isEyeInFluid(FluidTags.WATER))) {
                OxygenStorage oxygen = stack.getCapability(OxygenProvider.OXYGEN).orElse(null);

                if (oxygen != null) {
                    if (oxygen.getOxygen() > 0) {//TODO CHECK THIS AGAIN
                        this.oxygenTime++;

                        if (this.oxygenTime > 3) {
                            oxygen.setOxygen(oxygen.getOxygen() - 1);
                            this.oxygenTime = 0;
                        }
                    }
                }
            }
        }

        public abstract int getOxygenCapacity();
    }

    public static abstract class Leggings extends ISpaceArmor {
        public Leggings(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
            super(armorMaterial, equipmentSlot, properties);
        }
    }

    public static abstract class Boots extends ISpaceArmor {
        public Boots(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
            super(armorMaterial, equipmentSlot, properties);
        }
    }

    public ResourceLocation getTexture(ItemStack itemStack, LivingEntity entity) {
        String loc = itemStack.getItem().getArmorTexture(itemStack, entity, itemStack.getEquipmentSlot(), null);
        ResourceLocation texture = TEXTURES.get(loc);
        if (texture == null) {
            texture = new ResourceLocation(loc);
            TEXTURES.put(loc, texture);
        }

        return texture;
    }
}
