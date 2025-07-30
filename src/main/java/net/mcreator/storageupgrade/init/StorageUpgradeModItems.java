/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.storageupgrade.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.storageupgrade.StorageUpgradeMod;

import java.util.function.Function;

public class StorageUpgradeModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(StorageUpgradeMod.MODID);
	public static final DeferredItem<Item> LASER_CABLE_1 = block(StorageUpgradeModBlocks.LASER_CABLE_1);
	public static final DeferredItem<Item> LASER_CABLE_2 = block(StorageUpgradeModBlocks.LASER_CABLE_2);
	public static final DeferredItem<Item> LASER_CABLE_3 = block(StorageUpgradeModBlocks.LASER_CABLE_3);
	public static final DeferredItem<Item> LASER_CABLE_4 = block(StorageUpgradeModBlocks.LASER_CABLE_4);
	public static final DeferredItem<Item> LASER_CABLE_5 = block(StorageUpgradeModBlocks.LASER_CABLE_5);
	public static final DeferredItem<Item> LASER_CABLE_6 = block(StorageUpgradeModBlocks.LASER_CABLE_6);
	public static final DeferredItem<Item> LASER_ROUTER = block(StorageUpgradeModBlocks.LASER_ROUTER);
	public static final DeferredItem<Item> STORAGE_TERMINAL = block(StorageUpgradeModBlocks.STORAGE_TERMINAL);
	public static final DeferredItem<Item> DISK_STORAGE = block(StorageUpgradeModBlocks.DISK_STORAGE);

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), properties);
	}
}