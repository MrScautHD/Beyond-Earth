package net.mrscauthd.beyond_earth.compats.crafttweaker;

import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import com.blamejared.crafttweaker.api.fluid.CTFluidIngredient;
import com.blamejared.crafttweaker.api.fluid.MCFluidStack;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.ingredient.IIngredientWithAmount;
import com.blamejared.crafttweaker.api.tag.type.KnownTag;
import com.blamejared.crafttweaker.api.util.Many;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.mrscauthd.beyond_earth.crafting.FluidIngredient;
import net.mrscauthd.beyond_earth.crafting.IngredientStack;

public class CTUtils {

	@SuppressWarnings("unchecked")
	public static IngredientStack toItemIngredientStack(CommandStringDisplayable input) {
		if (input instanceof IIngredientWithAmount ingrdientWithAmount) {
			return toItemIngredientStack(ingrdientWithAmount);
		} else if (input instanceof IIngredient ingrdient) {
			return toItemIngredientStack(ingrdient);
		} else if (input instanceof Many<?> many) {
			return toItemIngredientStack((Many<KnownTag<Item>>) many);
		} else if (input instanceof KnownTag<?> tag) {
			return toItemIngredientStack((KnownTag<Item>) tag);
		} else {
			return IngredientStack.EMPTY;
		}
	}

	public static IngredientStack toItemIngredientStack(IIngredient input) {
		return new IngredientStack(input.asVanillaIngredient(), 1);
	}

	public static IngredientStack toItemIngredientStack(IIngredientWithAmount input) {
		return new IngredientStack(input.getIngredient().asVanillaIngredient(), input.getAmount());
	}

	public static IngredientStack toItemIngredientStack(Many<KnownTag<Item>> input) {
		TagKey<Item> tagKey = input.getData().getTagKey();
		return new IngredientStack(Ingredient.of(tagKey), input.getAmount());
	}

	public static IngredientStack toItemIngredientStack(KnownTag<Item> input) {
		TagKey<Item> tagKey = input.getTagKey();
		return new IngredientStack(Ingredient.of(tagKey), 1);
	}

	@SuppressWarnings("unchecked")
	public static FluidIngredient toFluidIngredient(CommandStringDisplayable input) {
		if (input instanceof CTFluidIngredient fluidIngredient) {
			return toFluidIngredient(fluidIngredient);
		} else if (input instanceof Many<?> many) {
			return toFluidIngredient(new CTFluidIngredient.FluidTagWithAmountIngredient((Many<KnownTag<Fluid>>) many));
		} else {
			return new FluidIngredient.Empty();
		}
	}

	public static FluidIngredient toFluidIngredient(CTFluidIngredient input) {
		return input.mapTo(FluidIngredient::of, FluidIngredient::of, FluidIngredient::of);
	}

	public static CTFluidIngredient toFluidIngredient(FluidIngredient input) {
		return new CTFluidIngredient.CompoundFluidIngredient(input.toStacks().stream().map(CTUtils::asFluidIngredient).toList());
	}

	public static CTFluidIngredient asFluidIngredient(FluidStack fs) {
		return new MCFluidStack(fs).asFluidIngredient();
	}

}
