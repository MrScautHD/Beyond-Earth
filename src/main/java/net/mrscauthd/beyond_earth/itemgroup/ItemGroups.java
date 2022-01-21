package net.mrscauthd.beyond_earth.itemgroup;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.ModInit;
import net.mrscauthd.beyond_earth.item.IFillCategoryAltItem;

public class ItemGroups {
	public static CreativeModeTab tab_normal = new CreativeModeTab("tab_normal") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModInit.TIER_1_ROCKET_ITEM.get(), 1);
		}

		@Override
		public void fillItemList(net.minecraft.core.NonNullList<ItemStack> list) {
			super.fillItemList(list);

			int columns = 9;
			int emptyCount = columns - (list.size() % columns);

			if (emptyCount > 0) {
				emptyCount += columns;
			}

			for (int i = 0; i < emptyCount; i++) {
				list.add(ItemStack.EMPTY);
			}

			List<IFillCategoryAltItem> altItems = new ArrayList<>();
			altItems.add(ModInit.TIER_1_ROCKET_ITEM.get());
			altItems.add(ModInit.TIER_2_ROCKET_ITEM.get());
			altItems.add(ModInit.TIER_3_ROCKET_ITEM.get());
			altItems.add(ModInit.TIER_4_ROCKET_ITEM.get());
			altItems.add(ModInit.ROVER_ITEM.get());

			for (IFillCategoryAltItem item : altItems) {
				item.fillItemCategoryAlt(this, list);
			}
		};

	};
	public static CreativeModeTab tab_machines = new CreativeModeTab("tab_machines") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModInit.NASA_WORKBENCH_ITEM.get(), 1);
		}
	};
	public static CreativeModeTab tab_basics = new CreativeModeTab("tab_basics") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModInit.DESH_ENGINE.get(), 1);
		}
	};
	public static CreativeModeTab tab_materials = new CreativeModeTab("tab_materials") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModInit.IRON_PLATE.get(), 1);
		}
	};
	public static CreativeModeTab tab_flags = new CreativeModeTab("tab_flags") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModInit.FLAG_PURPLE_BLOCK.get(), 1);
		}
	};
	public static CreativeModeTab tab_blocks = new CreativeModeTab("tab_blocks") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModInit.MOON_IRON_ORE.get(), 1);
		}
	};
	public static CreativeModeTab tab_spawn_eggs = new CreativeModeTab("tab_spawn_eggs") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModInit.ALIEN_SPAWN_EGG.get(), 1);
		}
	};
}
