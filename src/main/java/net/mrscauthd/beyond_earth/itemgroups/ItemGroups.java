package net.mrscauthd.beyond_earth.itemgroups;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.mrscauthd.beyond_earth.items.FilledAltArmorItem;
import net.mrscauthd.beyond_earth.registries.BlocksRegistry;
import net.mrscauthd.beyond_earth.registries.ItemsRegistry;

public class ItemGroups {
	public static CreativeModeTab tab_normal = new CreativeModeTab("tab_normal") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemsRegistry.TIER_1_ROCKET_ITEM.get(), 1);
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public void fillItemList(NonNullList<ItemStack> list) {
			/**Full Vehicle Items*/
			ItemsRegistry.TIER_1_ROCKET_ITEM.get().fillItemCategoryAlt(this, list);
			ItemsRegistry.TIER_2_ROCKET_ITEM.get().fillItemCategoryAlt(this, list);
			ItemsRegistry.TIER_3_ROCKET_ITEM.get().fillItemCategoryAlt(this, list);
			ItemsRegistry.TIER_4_ROCKET_ITEM.get().fillItemCategoryAlt(this, list);
			ItemsRegistry.ROVER_ITEM.get().fillItemCategoryAlt(this, list);

			int emptyCount = 9 - 5; //9 for max slots 5 how many that are used

			for (int i = 0; i < emptyCount; i++) {
				list.add(ItemStack.EMPTY);
			}

			/**Not Full Vehicle Item*/
			ItemsRegistry.TIER_1_ROCKET_ITEM.get().itemCategoryAlt(this, list);
			ItemsRegistry.TIER_2_ROCKET_ITEM.get().itemCategoryAlt(this, list);
			ItemsRegistry.TIER_3_ROCKET_ITEM.get().itemCategoryAlt(this, list);
			ItemsRegistry.TIER_4_ROCKET_ITEM.get().itemCategoryAlt(this, list);
			ItemsRegistry.ROVER_ITEM.get().itemCategoryAlt(this, list);

			emptyCount = 9 - 5;

			for (int i = 0; i < emptyCount; i++) {
				list.add(ItemStack.EMPTY);
			}

			/**SPACE SUIT*/
			((FilledAltArmorItem) ItemsRegistry.OXYGEN_MASK.get()).itemCategoryAlt(this, list);
			((FilledAltArmorItem) ItemsRegistry.SPACE_SUIT.get()).itemCategoryAlt(this, list);
			((FilledAltArmorItem) ItemsRegistry.SPACE_PANTS.get()).itemCategoryAlt(this, list);
			((FilledAltArmorItem) ItemsRegistry.SPACE_BOOTS.get()).itemCategoryAlt(this, list);
			//Full Space Suit
			((FilledAltArmorItem) ItemsRegistry.SPACE_SUIT.get()).fillItemCategoryAlt(this, list);

			emptyCount = 9 - 5;

			for (int i = 0; i < emptyCount; i++) {
				list.add(ItemStack.EMPTY);
			}

			/**NETHERITE SPACE SUIT*/
			((FilledAltArmorItem) ItemsRegistry.NETHERITE_OXYGEN_MASK.get()).itemCategoryAlt(this, list);
			((FilledAltArmorItem) ItemsRegistry.NETHERITE_SPACE_SUIT.get()).itemCategoryAlt(this, list);
			((FilledAltArmorItem) ItemsRegistry.NETHERITE_SPACE_PANTS.get()).itemCategoryAlt(this, list);
			((FilledAltArmorItem) ItemsRegistry.NETHERITE_SPACE_BOOTS.get()).itemCategoryAlt(this, list);
			//Full Space Suit
			((FilledAltArmorItem) ItemsRegistry.NETHERITE_SPACE_SUIT.get()).fillItemCategoryAlt(this, list);

			emptyCount = 9 - 5;

			for (int i = 0; i < emptyCount; i++) {
				list.add(ItemStack.EMPTY);
			}

			/**Default Items*/
			super.fillItemList(list);
		}
	};
	public static CreativeModeTab tab_machines = new CreativeModeTab("tab_machines") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemsRegistry.NASA_WORKBENCH_ITEM.get(), 1);
		}
	};
	public static CreativeModeTab tab_basics = new CreativeModeTab("tab_basics") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemsRegistry.DESH_ENGINE.get(), 1);
		}
	};
	public static CreativeModeTab tab_materials = new CreativeModeTab("tab_materials") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemsRegistry.IRON_PLATE.get(), 1);
		}
	};
	public static CreativeModeTab tab_flags = new CreativeModeTab("tab_flags") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(BlocksRegistry.FLAG_PURPLE_BLOCK.get(), 1);
		}
	};
	public static CreativeModeTab tab_globes = new CreativeModeTab("tab_globes") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(BlocksRegistry.GLACIO_GLOBE_BLOCK.get(), 1);
		}
	};
	public static CreativeModeTab tab_blocks = new CreativeModeTab("tab_blocks") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(BlocksRegistry.MOON_IRON_ORE.get(), 1);
		}
	};
	public static CreativeModeTab tab_spawn_eggs = new CreativeModeTab("tab_spawn_eggs") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemsRegistry.ALIEN_SPAWN_EGG.get(), 1);
		}
	};
}
