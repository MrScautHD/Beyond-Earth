package net.mrscauthd.beyond_earth.common.armors;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeMod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.renderers.armors.JetSuitModel;
import net.mrscauthd.beyond_earth.common.keybinds.KeyVariables;
import net.mrscauthd.beyond_earth.common.util.Methods;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

public class JetSuit {

    public static class Helmet extends ISpaceArmor.Helmet {
        public Helmet(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
            super(armorMaterial, equipmentSlot, properties);
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {

                @Override
                public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {

                    Map<String, ModelPart> map = Map.of("head", new JetSuitModel.JetSuitP1<>(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JetSuitP1.LAYER_LOCATION)).head,

                            "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())
                    );

                    ModelPart modelPart = new ModelPart(Collections.emptyList(), map);
                    JetSuitModel.JetSuitP1<?> armorModel = new JetSuitModel.JetSuitP1<>(modelPart, livingEntity, itemStack);

                    return armorModel;
                }
            });
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return BeyondEarth.MODID + ":textures/armor/jet_suit.png";
        }
    }

    public static class Suit extends ISpaceArmor.Chestplate {
        public static final String TAG_MODE = BeyondEarth.MODID + ":jet_suit_mode";
        public float spacePressTime;

        public Suit(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
            super(armorMaterial, equipmentSlot, properties);
        }

        //TODO IMPROVE FIRE MATH
        //TODO FINISH ANIMATIONS
        //TODO ADD ENERGY CAP
        //TODO REWORK OVERLAY

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {

                @Override
                public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {

                    Map<String, ModelPart> map = Map.of(
                            "body", new JetSuitModel.JetSuitP1<>(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JetSuitP1.LAYER_LOCATION)).body,
                            "right_arm", new JetSuitModel.JetSuitP1<>(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JetSuitP1.LAYER_LOCATION)).rightArm,
                            "left_arm", new JetSuitModel.JetSuitP1<>(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JetSuitP1.LAYER_LOCATION)).leftArm,
                            "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())
                    );

                    ModelPart modelPart = new ModelPart(Collections.emptyList(), map);
                    JetSuitModel.JetSuitP1<?> armorModel = new JetSuitModel.JetSuitP1<>(modelPart, livingEntity, itemStack);

                    return armorModel;
                }
            });
        }

        public enum ModeType {
            DISABLED(Component.translatable("general." + BeyondEarth.MODID + ".jet_suit_disabled_mode"), ChatFormatting.RED, 0),
            NORMAL(Component.translatable("general." + BeyondEarth.MODID + ".jet_suit_normal_mode"), ChatFormatting.GREEN, 1),
            HOVER(Component.translatable("general." + BeyondEarth.MODID + ".jet_suit_hover_mode"), ChatFormatting.GREEN, 2),
            ELYTRA(Component.translatable("general." + BeyondEarth.MODID + ".jet_suit_elytra_mode"), ChatFormatting.GREEN, 3);

            private final int mode;
            private final ChatFormatting chatFormatting;
            private final Component component;

            ModeType(Component component, ChatFormatting chatFormatting, int mode) {
                this.mode = mode;
                this.chatFormatting = chatFormatting;
                this.component = component;
            }

            public int getMode() {
                return this.mode;
            }

            public ChatFormatting getChatFormatting() {
                return this.chatFormatting;
            }

            public Component getTranslationKey() {
                return this.component;
            }
        }

        public int getMode(ItemStack itemStack) {
            return itemStack.getOrCreateTag().getInt(TAG_MODE);
        }

        public ModeType getModeType(ItemStack itemStack) {
            int mode = this.getMode(itemStack);

            if (mode == 1) {
                return ModeType.NORMAL;
            }
            else if (mode == 2) {
                return ModeType.HOVER;
            }
            else if (mode == 3) {
                return ModeType.ELYTRA;
            }

            return ModeType.DISABLED;
        }

        @Override
        public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
            return Methods.isLivingInJetSuit(entity) && this.getMode(stack) == ModeType.ELYTRA.getMode();
        }

        @Override
        public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
            if (!entity.level.isClientSide) {
                int nextFlightTick = flightTicks + 1;
                if (nextFlightTick % 10 == 0) {
                    entity.gameEvent(GameEvent.ELYTRA_GLIDE);
                }
            }

            return true;
        }

        @Override
        public void onArmorTick(ItemStack stack, Level level, Player player) {
            super.onArmorTick(stack, level, player);

            /** JET SUIT FAST BOOST */
            if (player.isSprinting()) {
                this.boost(player, 1.3, true);
            }

            /** JET SUIT SLOW BOOST */
            if (player.zza > 0 && !player.isSprinting()) {
                this.boost(player, 0.9, false);
            }

            /** NORMAL FLY MOVEMENT */
            this.normalFlyModeMovement(player, stack);

            /** CALCULATE PRESS SPACE TIME */
            this.calculateSpacePressTime(player, stack);
        }

        public void normalFlyModeMovement(Player player, ItemStack stack) {
            if (!player.getAbilities().flying && !player.isPassenger() && Methods.isLivingInJetSuit(player)) {

                /** HOVER FLY */
                if (this.getMode(stack) == ModeType.HOVER.getMode() && !player.hasEffect(MobEffects.SLOW_FALLING)) {
                    double gravity = player.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).getBaseValue();
                    Vec3 vec3 = player.getDeltaMovement();

                    /** MAIN MOVEMENT */
                    if (!player.isOnGround() && !player.isInFluidType()) {
                        player.setDeltaMovement(vec3.x, vec3.y + gravity - 0.005, vec3.z);
                        player.resetFallDistance();
                        Methods.disableFlyAntiCheat(player, true);
                    }

                    /** MOVE UP */
                    if (KeyVariables.isHoldingJump(player)) {
                        player.moveRelative(2.0F, new Vec3(0, 0.008, 0));
                        Methods.disableFlyAntiCheat(player, true);
                    }

                    /** MOVE DOWN */
                    if (!player.isOnGround() && player.isCrouching()) {
                        player.moveRelative(2.0F, new Vec3(0, -0.008, 0));

                        if (player instanceof LocalPlayer localPlayer) {
                            localPlayer.crouching = false;
                        }
                    }

                    /** MOVE FORWARD AND BACKWARD */
                    if (!player.isOnGround()) {
                        if (KeyVariables.isHoldingUp(player)) {
                            player.moveRelative(1.0F, new Vec3(0, 0, 0.01));
                        }
                        else if (KeyVariables.isHoldingDown(player)) {
                            player.moveRelative(1.0F, new Vec3(0, 0, -0.01));
                        }
                    }

                    /** MOVE SIDEWAYS */
                    if (!player.isOnGround()) {
                        if (KeyVariables.isHoldingRight(player)) {
                            player.moveRelative(1.0F, new Vec3(-0.01, 0, 0));
                        }
                        else if (KeyVariables.isHoldingLeft(player)) {
                            player.moveRelative(1.0F, new Vec3(0.01, 0, 0));
                        }
                    }
                }

                /** NORMAL FLY */
                if (this.getMode(stack) == ModeType.NORMAL.getMode()) {

                    /** MOVE UP */
                    if (KeyVariables.isHoldingJump(player)) {
                        player.moveRelative(1.2F, new Vec3(0, 0.1, 0));
                        player.resetFallDistance();
                        Methods.disableFlyAntiCheat(player, true);
                    }

                    /** MOVE FORWARD AND BACKWARD */
                    if (!player.isOnGround()) {
                        if (KeyVariables.isHoldingUp(player)) {
                            player.moveRelative(1.0F, new Vec3(0, 0, 0.03));
                        }
                        else if (KeyVariables.isHoldingDown(player)) {
                            player.moveRelative(1.0F, new Vec3(0, 0, -0.03));
                        }
                    }

                    /** MOVE SIDEWAYS */
                    if (!player.isOnGround()) {
                        if (KeyVariables.isHoldingRight(player)) {
                            player.moveRelative(1.0F, new Vec3(-0.03, 0, 0));
                        }
                        else if (KeyVariables.isHoldingLeft(player)) {
                            player.moveRelative(1.0F, new Vec3(0.03, 0, 0));
                        }
                    }
                }
            }
        }

        public void switchJetSuitMode(Player player, ItemStack itemStack) {
            CompoundTag compoundTag = itemStack.getOrCreateTag();

            if (this.getMode(itemStack) < 3) {
                compoundTag.putInt(JetSuit.Suit.TAG_MODE, this.getMode(itemStack) + 1);
            } else {
                compoundTag.putInt(JetSuit.Suit.TAG_MODE, 0);
            }
        }

        public void calculateSpacePressTime(Player player, ItemStack itemStack) {
            int mode = this.getMode(itemStack);

            if (Methods.isLivingInJetSuit(player)) {

                /** NORMAL MODE */
                if (mode == ModeType.NORMAL.getMode()) {
                    if (KeyVariables.isHoldingJump(player)) {
                        if (this.spacePressTime < 2.2F) {
                            this.spacePressTime = this.spacePressTime + 0.2F;
                        }
                    }
                    else if (this.spacePressTime > 0.0F) {
                        this.spacePressTime = this.spacePressTime - 0.2F;
                    }
                }

                /** HOVER MODE */
                if (mode == ModeType.HOVER.getMode()) {
                    if (!player.isOnGround() && this.spacePressTime < 0.6F) {
                        this.spacePressTime = this.spacePressTime + 0.2F;
                    }
                    else if (KeyVariables.isHoldingJump(player)) {
                        if (this.spacePressTime < 1.4F) {
                            this.spacePressTime = this.spacePressTime + 0.2F;
                        }
                    }
                    else if (this.spacePressTime > 0.6F) {
                        this.spacePressTime = this.spacePressTime - 0.2F;
                    }
                }

                /** ELYTRA MODE */
                if (mode == ModeType.ELYTRA.getMode()) {
                    if (KeyVariables.isHoldingUp(player) && player.isFallFlying()) {
                        if (player.isSprinting()) {
                            if (this.spacePressTime < 2.8F) {
                                this.spacePressTime = this.spacePressTime + 0.2F;
                            }
                        } else {
                            if (this.spacePressTime < 2.2F) {
                                this.spacePressTime = this.spacePressTime + 0.2F;
                            }
                        }
                    }
                    else if (this.spacePressTime > 0.0F) {
                        this.spacePressTime = this.spacePressTime - 0.2F;
                    }
                }
            }
        }

        public void boost(Player player, double boost, boolean sonicBoom) {
            Vec3 vec31 = player.getLookAngle();

            if (Methods.isLivingInJetSuit(player) && player.isFallFlying()) {
                Vec3 vec32 = player.getDeltaMovement();
                player.setDeltaMovement(vec32.add(vec31.x * 0.1D + (vec31.x * boost - vec32.x) * 0.5D, vec31.y * 0.1D + (vec31.y * boost - vec32.y) * 0.5D, vec31.z * 0.1D + (vec31.z * boost - vec32.z) * 0.5D));

                if (sonicBoom) {
                    Vec3 vec33 = player.getLookAngle().scale(6.5D);

                    if (player.level instanceof ServerLevel) {
                        for (ServerPlayer p : ((ServerLevel) player.level).getServer().getPlayerList().getPlayers()) {
                            ((ServerLevel) player.level).sendParticles(p, ParticleTypes.FLASH, true, player.getX() - vec33.x, player.getY() - vec33.y, player.getZ() - vec33.z, 1, 0, 0, 0, 0.001);
                        }
                    }
                }
            }
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return BeyondEarth.MODID + ":textures/armor/jet_suit.png";
        }

        @Override
        public int getOxygenCapacity() {
            return 60000;
        }
    }

    public static class Pants extends ISpaceArmor.Leggings {
        public Pants(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
            super(armorMaterial, equipmentSlot, properties);
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {

                @Override
                public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {

                    Map<String, ModelPart> map = Map.of(
                            "right_leg", new JetSuitModel.JetSuitP2<>(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JetSuitP2.LAYER_LOCATION)).rightLeg,
                            "left_leg", new JetSuitModel.JetSuitP2<>(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JetSuitP2.LAYER_LOCATION)).leftLeg,

                            "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap())

                    );

                    ModelPart modelPart = new ModelPart(Collections.emptyList(), map);
                    JetSuitModel.JetSuitP2<?> armorModel = new JetSuitModel.JetSuitP2<>(modelPart, livingEntity, itemStack);

                    return armorModel;
                }
            });
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return BeyondEarth.MODID + ":textures/armor/jet_suit_pants.png";
        }
    }

    public static class Boots extends ISpaceArmor.Boots {
        public Boots(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot, Properties properties) {
            super(armorMaterial, equipmentSlot, properties);
        }

        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {

                @Override
                public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                    Map<String, ModelPart> map = Map.of(
                            "right_leg", new JetSuitModel.JetSuitP1<>(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JetSuitP1.LAYER_LOCATION)).rightLeg,
                            "left_leg", new JetSuitModel.JetSuitP1<>(Minecraft.getInstance().getEntityModels().bakeLayer(JetSuitModel.JetSuitP1.LAYER_LOCATION)).leftLeg,

                            "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap())

                    );

                    ModelPart modelPart = new ModelPart(Collections.emptyList(), map);
                    JetSuitModel.JetSuitP1<?> armorModel = new JetSuitModel.JetSuitP1<>(modelPart, livingEntity, itemStack);

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
