/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.storageupgrade.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.storageupgrade.StorageUpgradeMod;

public class StorageUpgradeModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StorageUpgradeMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> STORAGE_UPGRADE_TAB = REGISTRY.register("storage_upgrade_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.storage_upgrade.storage_upgrade_tab")).icon(() -> new ItemStack(StorageUpgradeModBlocks.STORAGE_TERMINAL.get())).displayItems((parameters, tabData) -> {
				tabData.accept(StorageUpgradeModBlocks.LASER_CABLE_1.get().asItem());
				tabData.accept(StorageUpgradeModBlocks.STORAGE_TERMINAL.get().asItem());
				tabData.accept(StorageUpgradeModBlocks.DISK_STORAGE.get().asItem());
				tabData.accept(StorageUpgradeModItems.CARBON_FIBERS.get());
				tabData.accept(StorageUpgradeModItems.STORAGE_COMPONENT_1K.get());
				tabData.accept(StorageUpgradeModItems.STORAGE_COMPONENT_4K.get());
				tabData.accept(StorageUpgradeModItems.STORAGE_COMPONENT_64K.get());
				tabData.accept(StorageUpgradeModItems.STORAGE_TOOL.get());
			}).build());
}