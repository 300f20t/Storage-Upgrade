package net.mcreator.storageupgrade.world.inventory;

import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.storageupgrade.init.StorageUpgradeModMenus;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class StorageTerminalGUIMenu extends AbstractContainerMenu implements StorageUpgradeModMenus.MenuAccessor {
	public final Map<String, Object> menuState = new HashMap<>() {
		@Override
		public Object put(String key, Object value) {
			if (!this.containsKey(key) && this.size() >= 41)
				return null;
			return super.put(key, value);
		}
	};
	public final Level world;
	public final Player entity;
	public int x, y, z;
	private ContainerLevelAccess access = ContainerLevelAccess.NULL;
	private IItemHandler internal;
	private final Map<Integer, Slot> customSlots = new HashMap<>();
	private boolean bound = false;
	private Supplier<Boolean> boundItemMatcher = null;
	private Entity boundEntity = null;
	private BlockEntity boundBlockEntity = null;

	public StorageTerminalGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(StorageUpgradeModMenus.STORAGE_TERMINAL_GUI.get(), id);
		this.entity = inv.player;
		this.world = inv.player.level();
		this.internal = new ItemStackHandler(37);
		BlockPos pos = null;
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
			access = ContainerLevelAccess.create(world, pos);
		}
		if (pos != null) {
			if (extraData.readableBytes() == 1) { // bound to item
				byte hand = extraData.readByte();
				ItemStack itemstack = hand == 0 ? this.entity.getMainHandItem() : this.entity.getOffhandItem();
				this.boundItemMatcher = () -> itemstack == (hand == 0 ? this.entity.getMainHandItem() : this.entity.getOffhandItem());
				IItemHandler cap = itemstack.getCapability(Capabilities.ItemHandler.ITEM);
				if (cap != null) {
					this.internal = cap;
					this.bound = true;
				}
			} else if (extraData.readableBytes() > 1) { // bound to entity
				extraData.readByte(); // drop padding
				boundEntity = world.getEntity(extraData.readVarInt());
				if (boundEntity != null) {
					IItemHandler cap = boundEntity.getCapability(Capabilities.ItemHandler.ENTITY);
					if (cap != null) {
						this.internal = cap;
						this.bound = true;
					}
				}
			} else { // might be bound to block
				boundBlockEntity = this.world.getBlockEntity(pos);
				if (boundBlockEntity instanceof BaseContainerBlockEntity baseContainerBlockEntity) {
					this.internal = new InvWrapper(baseContainerBlockEntity);
					this.bound = true;
				}
			}
		}
		this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, -20, 8) {
			private final int slot = 0;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;
		}));
		this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 7, 8) {
			private final int slot = 1;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 25, 8) {
			private final int slot = 2;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 43, 8) {
			private final int slot = 3;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 61, 8) {
			private final int slot = 4;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 79, 8) {
			private final int slot = 5;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 97, 8) {
			private final int slot = 6;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 115, 8) {
			private final int slot = 7;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 133, 8) {
			private final int slot = 8;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 151, 8) {
			private final int slot = 9;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 7, 26) {
			private final int slot = 10;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 25, 26) {
			private final int slot = 11;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 43, 26) {
			private final int slot = 12;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 61, 26) {
			private final int slot = 13;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 79, 26) {
			private final int slot = 14;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(15, this.addSlot(new SlotItemHandler(internal, 15, 97, 26) {
			private final int slot = 15;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(16, this.addSlot(new SlotItemHandler(internal, 16, 115, 26) {
			private final int slot = 16;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(17, this.addSlot(new SlotItemHandler(internal, 17, 133, 26) {
			private final int slot = 17;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(18, this.addSlot(new SlotItemHandler(internal, 18, 151, 26) {
			private final int slot = 18;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(19, this.addSlot(new SlotItemHandler(internal, 19, 7, 44) {
			private final int slot = 19;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(20, this.addSlot(new SlotItemHandler(internal, 20, 25, 44) {
			private final int slot = 20;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(21, this.addSlot(new SlotItemHandler(internal, 21, 43, 44) {
			private final int slot = 21;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(22, this.addSlot(new SlotItemHandler(internal, 22, 61, 44) {
			private final int slot = 22;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(23, this.addSlot(new SlotItemHandler(internal, 23, 79, 44) {
			private final int slot = 23;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(24, this.addSlot(new SlotItemHandler(internal, 24, 97, 44) {
			private final int slot = 24;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(25, this.addSlot(new SlotItemHandler(internal, 25, 115, 44) {
			private final int slot = 25;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(26, this.addSlot(new SlotItemHandler(internal, 26, 133, 44) {
			private final int slot = 26;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(27, this.addSlot(new SlotItemHandler(internal, 27, 151, 44) {
			private final int slot = 27;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(28, this.addSlot(new SlotItemHandler(internal, 28, 7, 62) {
			private final int slot = 28;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(29, this.addSlot(new SlotItemHandler(internal, 29, 25, 62) {
			private final int slot = 29;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(30, this.addSlot(new SlotItemHandler(internal, 30, 43, 62) {
			private final int slot = 30;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(31, this.addSlot(new SlotItemHandler(internal, 31, 61, 62) {
			private final int slot = 31;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(32, this.addSlot(new SlotItemHandler(internal, 32, 79, 62) {
			private final int slot = 32;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(33, this.addSlot(new SlotItemHandler(internal, 33, 97, 62) {
			private final int slot = 33;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(34, this.addSlot(new SlotItemHandler(internal, 34, 115, 62) {
			private final int slot = 34;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(35, this.addSlot(new SlotItemHandler(internal, 35, 133, 62) {
			private final int slot = 35;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		this.customSlots.put(36, this.addSlot(new SlotItemHandler(internal, 36, 151, 62) {
			private final int slot = 36;
			private int x = StorageTerminalGUIMenu.this.x;
			private int y = StorageTerminalGUIMenu.this.y;

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		}));
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));
	}

	@Override
	public boolean stillValid(Player player) {
		if (this.bound) {
			if (this.boundItemMatcher != null)
				return this.boundItemMatcher.get();
			else if (this.boundBlockEntity != null)
				return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
			else if (this.boundEntity != null)
				return this.boundEntity.isAlive();
		}
		return true;
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 37) {
				if (!this.moveItemStackTo(itemstack1, 37, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, 37, false)) {
				if (index < 37 + 27) {
					if (!this.moveItemStackTo(itemstack1, 37 + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.moveItemStackTo(itemstack1, 37, 37 + 27, false))
						return ItemStack.EMPTY;
				}
				return ItemStack.EMPTY;
			}
			if (itemstack1.isEmpty()) {
				slot.setByPlayer(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
			slot.onTake(playerIn, itemstack1);
		}
		return itemstack;
	}

	@Override
	protected boolean moveItemStackTo(ItemStack p_38904_, int p_38905_, int p_38906_, boolean p_38907_) {
		boolean flag = false;
		int i = p_38905_;
		if (p_38907_) {
			i = p_38906_ - 1;
		}
		if (p_38904_.isStackable()) {
			while (!p_38904_.isEmpty() && (p_38907_ ? i >= p_38905_ : i < p_38906_)) {
				Slot slot = this.slots.get(i);
				ItemStack itemstack = slot.getItem();
				if (slot.mayPlace(itemstack) && !itemstack.isEmpty() && ItemStack.isSameItemSameComponents(p_38904_, itemstack)) {
					int j = itemstack.getCount() + p_38904_.getCount();
					int k = slot.getMaxStackSize(itemstack);
					if (j <= k) {
						p_38904_.setCount(0);
						itemstack.setCount(j);
						slot.set(itemstack);
						flag = true;
					} else if (itemstack.getCount() < k) {
						p_38904_.shrink(k - itemstack.getCount());
						itemstack.setCount(k);
						slot.set(itemstack);
						flag = true;
					}
				}
				if (p_38907_) {
					i--;
				} else {
					i++;
				}
			}
		}
		if (!p_38904_.isEmpty()) {
			if (p_38907_) {
				i = p_38906_ - 1;
			} else {
				i = p_38905_;
			}
			while (p_38907_ ? i >= p_38905_ : i < p_38906_) {
				Slot slot1 = this.slots.get(i);
				ItemStack itemstack1 = slot1.getItem();
				if (itemstack1.isEmpty() && slot1.mayPlace(p_38904_)) {
					int l = slot1.getMaxStackSize(p_38904_);
					slot1.setByPlayer(p_38904_.split(Math.min(p_38904_.getCount(), l)));
					slot1.setChanged();
					flag = true;
					break;
				}
				if (p_38907_) {
					i--;
				} else {
					i++;
				}
			}
		}
		return flag;
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		if (!bound && playerIn instanceof ServerPlayer serverPlayer) {
			if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
				for (int j = 0; j < internal.getSlots(); ++j) {
					playerIn.drop(internal.getStackInSlot(j), false);
					if (internal instanceof IItemHandlerModifiable ihm)
						ihm.setStackInSlot(j, ItemStack.EMPTY);
				}
			} else {
				for (int i = 0; i < internal.getSlots(); ++i) {
					playerIn.getInventory().placeItemBackInInventory(internal.getStackInSlot(i));
					if (internal instanceof IItemHandlerModifiable ihm)
						ihm.setStackInSlot(i, ItemStack.EMPTY);
				}
			}
		}
	}

	@Override
	public Map<Integer, Slot> getSlots() {
		return Collections.unmodifiableMap(customSlots);
	}

	@Override
	public Map<String, Object> getMenuState() {
		return menuState;
	}
}