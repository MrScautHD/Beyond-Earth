package net.mrscauthd.beyond_earth.compats.crafttweaker.bracket;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotation.BracketResolver;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTConstants;
import net.mrscauthd.beyond_earth.crafting.RocketPart;
import net.mrscauthd.beyond_earth.registries.RocketPartsRegistry;

@ZenRegister
@ZenCodeType.Name(CTConstants.BRACKET_HANDLER)
public class CTBracketHandlers {

	@ZenCodeType.Method
	@BracketResolver(CTConstants.BRACKET_ROCKET_PART)
	public static RocketPart getRocketPart(String name) {
		return findRegistryEntry(CTConstants.BRACKET_ROCKET_PART, name, RocketPartsRegistry.ROCKET_PARTS_REGISTRY.get());
	}

	public static String toString(RocketPart part) {
		return CTBracketUtils.toBracketString(CTConstants.BRACKET_ROCKET_PART, part.getRegistryName());
	}

	private static <V extends IForgeRegistryEntry<V>> V findRegistryEntry(String bracket, String name, IForgeRegistry<V> registry) {
		ResourceLocation registryName = ResourceLocation.tryParse(name);

		if (registryName == null) {
			throw new IllegalArgumentException("Invalid name");
		} else if (!registry.containsKey(registryName)) {
			throw new IllegalArgumentException("RegistryEntry not found by typeName :<" + (bracket + ":" + name) + ">");
		} else {
			return registry.getValue(registryName);
		}
	}

}
