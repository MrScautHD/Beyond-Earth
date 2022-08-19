package net.mrscauthd.beyond_earth.compats.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.plugin.CraftTweakerPlugin;
import com.blamejared.crafttweaker.api.plugin.ICraftTweakerPlugin;
import com.blamejared.crafttweaker.api.plugin.ILoaderRegistrationHandler;
import com.blamejared.crafttweaker.api.plugin.IScriptLoadSourceRegistrationHandler;
import com.blamejared.crafttweaker.api.plugin.IScriptRunModuleConfiguratorRegistrationHandler;
import com.blamejared.crafttweaker.api.zencode.scriptrun.IScriptRunModuleConfigurator;

import net.mrscauthd.beyond_earth.BeyondEarthMod;

@CraftTweakerPlugin(BeyondEarthMod.MODID + ":ct_plugin")
public class BECraftTweakerPlugin implements ICraftTweakerPlugin {

	@Override
	public void registerLoadSource(IScriptLoadSourceRegistrationHandler handler) {
		handler.registerLoadSource(CTConstants.SOURCE_LOADER_ID);
	}

	@Override
	public void registerLoaders(ILoaderRegistrationHandler handler) {
		handler.registerLoader(CTConstants.SOURCE_LOADER_NAME, CraftTweakerConstants.DEFAULT_LOADER_NAME);
	}

	@Override
	public void registerModuleConfigurators(final IScriptRunModuleConfiguratorRegistrationHandler handler) {
		IScriptRunModuleConfigurator defaultConfig = IScriptRunModuleConfigurator.createDefault(CraftTweakerConstants.DEFAULT_LOADER_NAME);
		handler.registerConfigurator(CTConstants.SOURCE_LOADER_NAME, defaultConfig);
	}
}
