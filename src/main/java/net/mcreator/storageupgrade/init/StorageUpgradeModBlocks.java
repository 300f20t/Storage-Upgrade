/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.storageupgrade.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.storageupgrade.block.StorageTerminalBlock;
import net.mcreator.storageupgrade.block.LaserRouterBlock;
import net.mcreator.storageupgrade.block.LaserCable6Block;
import net.mcreator.storageupgrade.block.LaserCable5Block;
import net.mcreator.storageupgrade.block.LaserCable4Block;
import net.mcreator.storageupgrade.block.LaserCable3Block;
import net.mcreator.storageupgrade.block.LaserCable2Block;
import net.mcreator.storageupgrade.block.LaserCable1Block;
import net.mcreator.storageupgrade.block.DiskStorageBlock;
import net.mcreator.storageupgrade.StorageUpgradeMod;

import java.util.function.Function;

public class StorageUpgradeModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(StorageUpgradeMod.MODID);
	public static final DeferredBlock<Block> LASER_CABLE_1 = register("laser_cable_1", LaserCable1Block::new);
	public static final DeferredBlock<Block> LASER_CABLE_2 = register("laser_cable_2", LaserCable2Block::new);
	public static final DeferredBlock<Block> LASER_CABLE_3 = register("laser_cable_3", LaserCable3Block::new);
	public static final DeferredBlock<Block> LASER_CABLE_4 = register("laser_cable_4", LaserCable4Block::new);
	public static final DeferredBlock<Block> LASER_CABLE_5 = register("laser_cable_5", LaserCable5Block::new);
	public static final DeferredBlock<Block> LASER_CABLE_6 = register("laser_cable_6", LaserCable6Block::new);
	public static final DeferredBlock<Block> LASER_ROUTER = register("laser_router", LaserRouterBlock::new);
	public static final DeferredBlock<Block> STORAGE_TERMINAL = register("storage_terminal", StorageTerminalBlock::new);
	public static final DeferredBlock<Block> DISK_STORAGE = register("disk_storage", DiskStorageBlock::new);

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier, BlockBehaviour.Properties.of());
	}
}