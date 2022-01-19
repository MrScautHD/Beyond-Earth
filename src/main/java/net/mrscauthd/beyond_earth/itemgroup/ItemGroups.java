package net.mrscauthd.beyond_earth.itemgroup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.ModInit;

public class ItemGroups {
	public static CreativeModeTab tab_normal = new CreativeModeTabFill2("tab_normal") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModInit.TIER_1_ROCKET_ITEM.get(), 1);
		}

	};
	public static CreativeModeTab tab_machines = new CreativeModeTabFill2("tab_machines") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModInit.NASA_WORKBENCH_ITEM.get(), 1);
		}
	};
	public static CreativeModeTab tab_basics = new CreativeModeTabFill2("tab_basics") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModInit.DESH_ENGINE.get(), 1);
		}
	};
	public static CreativeModeTab tab_materials = new CreativeModeTabFill2("tab_materials") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModInit.IRON_PLATE.get(), 1);
		}
	};
	public static CreativeModeTab tab_flags = new CreativeModeTabFill2("tab_flags") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModInit.FLAG_PURPLE_BLOCK.get(), 1);
		}
	};
	public static CreativeModeTab tab_blocks = new CreativeModeTabFill2("tab_blocks") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModInit.MOON_IRON_ORE.get(), 1);
		}
	};
	public static CreativeModeTab tab_spawn_eggs = new CreativeModeTabFill2("tab_spawn_eggs") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModInit.ALIEN_SPAWN_EGG.get(), 1);
		}
	};
}
