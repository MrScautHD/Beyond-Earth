package net.mrscauthd.beyond_earth.common.data.recipes;

import org.apache.commons.lang3.tuple.Triple;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrscauthd.beyond_earth.common.entities.alien.AlienTrade;

public abstract class AlienTradingRecipe extends BeyondEarthRecipe {

	private final VillagerProfession job;
	private final int level;
	private final int xp;
	private final int maxUses;
	private final float priceMultiplier;

	public AlienTradingRecipe(ResourceLocation id, JsonObject json) {
		super(id, json);

		this.job = ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation(GsonHelper.getAsString(json, "job")));
		this.level = GsonHelper.getAsInt(json, "level");
		this.xp = GsonHelper.getAsInt(json, "xp");
		this.maxUses = GsonHelper.getAsInt(json, "maxUses", AlienTrade.MAX_USES);
		this.priceMultiplier = GsonHelper.getAsFloat(json, "priceMultiplier", 0.05F);
	}

	public AlienTradingRecipe(ResourceLocation id, FriendlyByteBuf buffer) {
		super(id, buffer);

		this.job = buffer.readRegistryId();
		this.level = buffer.readInt();
		this.xp = buffer.readInt();
		this.maxUses = buffer.readInt();
		this.priceMultiplier = buffer.readFloat();
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		super.write(buffer);

		buffer.writeRegistryId(ForgeRegistries.VILLAGER_PROFESSIONS, this.job);
		buffer.writeInt(this.level);
		buffer.writeInt(this.xp);
		buffer.writeInt(this.maxUses);
		buffer.writeFloat(this.priceMultiplier);
	}

	public abstract Triple<ItemStack, ItemStack, ItemStack> getTrade(Entity trader, RandomSource rand);

	@Override
	public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
		return true;
	}

	public VillagerProfession getJob() {
		return this.job;
	}

	public int getLevel() {
		return this.level;
	}

	public int getXP() {
		return this.xp;
	}

	public int getMaxUses() {
		return this.maxUses;
	}

	public float getPriceMultiplier() {
		return this.priceMultiplier;
	}

}
