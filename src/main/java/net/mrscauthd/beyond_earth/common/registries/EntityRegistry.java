package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.entities.*;
import net.mrscauthd.beyond_earth.common.entities.alien.AlienEntity;
import net.mrscauthd.beyond_earth.common.entities.pygro.PygroEntity;

public class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BeyondEarth.MODID);

    /** LIVING ENTITIES */
    public static final RegistryObject<EntityType<AlienEntity>> ALIEN = ENTITIES.register("alien", () -> EntityType.Builder.of(AlienEntity::new, MobCategory.CREATURE).sized(0.75f, 2.5f).build(new ResourceLocation(BeyondEarth.MODID, "alien").toString()));
    public static final RegistryObject<EntityType<AlienZombieEntity>> ALIEN_ZOMBIE = ENTITIES.register("alien_zombie", () -> EntityType.Builder.of(AlienZombieEntity::new, MobCategory.MONSTER).sized(0.6f, 2.4f).build(new ResourceLocation(BeyondEarth.MODID, "alien_zombie").toString()));
    public static final RegistryObject<EntityType<StarCrawlerEntity>> STAR_CRAWLER = ENTITIES.register("star_crawler", () -> EntityType.Builder.of(StarCrawlerEntity::new, MobCategory.MONSTER).sized(1.3f, 1f).build(new ResourceLocation(BeyondEarth.MODID, "star_crawler").toString()));
    public static final RegistryObject<EntityType<PygroEntity>> PYGRO = ENTITIES.register("pygro", () -> EntityType.Builder.of(PygroEntity::new, MobCategory.MONSTER).fireImmune().sized(0.6f, 1.8f).build(new ResourceLocation(BeyondEarth.MODID, "pygro").toString()));
    public static final RegistryObject<EntityType<PygroBruteEntity>> PYGRO_BRUTE = ENTITIES.register("pygro_brute", () -> EntityType.Builder.of(PygroBruteEntity::new, MobCategory.MONSTER).fireImmune().sized(0.6f, 1.8f).build(new ResourceLocation(BeyondEarth.MODID, "pygro_brute").toString()));
    public static final RegistryObject<EntityType<MoglerEntity>> MOGLER = ENTITIES.register("mogler", () -> EntityType.Builder.of(MoglerEntity::new, MobCategory.MONSTER).sized(1.4f, 1.4f).build(new ResourceLocation(BeyondEarth.MODID, "mogler").toString()));
    public static final RegistryObject<EntityType<MartianRaptor>> MARTIAN_RAPTOR = ENTITIES.register("martian_raptor", () -> EntityType.Builder.of(MartianRaptor::new, MobCategory.MONSTER).sized(0.75f, 2.0f).build(new ResourceLocation(BeyondEarth.MODID, "martian_raptor").toString()));


    /** VEHICLE ENTITIES */
    public static final RegistryObject<EntityType<RocketTier1Entity>> TIER_1_ROCKET = ENTITIES.register("rocket_t1", () -> EntityType.Builder.of(RocketTier1Entity::new, MobCategory.MISC).sized(1.1f, 4.4f).fireImmune().build(new ResourceLocation(BeyondEarth.MODID, "rocket_t1").toString()));
    public static final RegistryObject<EntityType<RocketTier2Entity>> TIER_2_ROCKET = ENTITIES.register("rocket_t2", () -> EntityType.Builder.of(RocketTier2Entity::new, MobCategory.MISC).sized(1.1f, 4.6f).fireImmune().build(new ResourceLocation(BeyondEarth.MODID, "rocket_t2").toString()));
    public static final RegistryObject<EntityType<RocketTier3Entity>> TIER_3_ROCKET = ENTITIES.register("rocket_t3", () -> EntityType.Builder.of(RocketTier3Entity::new, MobCategory.MISC).sized(1.1f, 4.8f).fireImmune().build(new ResourceLocation(BeyondEarth.MODID, "rocket_t3").toString()));
    public static final RegistryObject<EntityType<RocketTier4Entity>> TIER_4_ROCKET = ENTITIES.register("rocket_t4", () -> EntityType.Builder.of(RocketTier4Entity::new, MobCategory.MISC).sized(1.1f, 6.1f).fireImmune().build(new ResourceLocation(BeyondEarth.MODID, "rocket_t4").toString()));
    public static final RegistryObject<EntityType<LanderEntity>> LANDER = ENTITIES.register("lander", () -> EntityType.Builder.of(LanderEntity::new, MobCategory.MISC).sized(1.0f, 2.0f).fireImmune().build(new ResourceLocation(BeyondEarth.MODID, "lander").toString()));
    public static final RegistryObject<EntityType<RoverEntity>> ROVER = ENTITIES.register("rover", () -> EntityType.Builder.of(RoverEntity::new, MobCategory.MISC).sized(2.5f, 1.0f).fireImmune().build(new ResourceLocation(BeyondEarth.MODID, "rover").toString()));

    /** PROJECTILE ENTITIES */
    public static final RegistryObject<EntityType<? extends IceSpitEntity>> ICE_SPIT_ENTITY = ENTITIES.register("ice_spit_entity", () -> EntityType.Builder.<IceSpitEntity>of(IceSpitEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(BeyondEarth.MODID, "alien_spit_entity").toString()));
}
