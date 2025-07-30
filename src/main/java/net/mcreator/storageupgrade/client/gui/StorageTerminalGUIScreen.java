package net.mcreator.storageupgrade.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.storageupgrade.world.inventory.StorageTerminalGUIMenu;
import net.mcreator.storageupgrade.init.StorageUpgradeModScreens;

import com.mojang.blaze3d.systems.RenderSystem;

public class StorageTerminalGUIScreen extends AbstractContainerScreen<StorageTerminalGUIMenu> implements StorageUpgradeModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	EditBox search_bar;
	Button button_up;
	Button button_down;

	public StorageTerminalGUIScreen(StorageTerminalGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("search_bar"))
				search_bar.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("storage_upgrade:textures/screens/storage_terminal_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		search_bar.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(RenderType::guiTextured, texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (search_bar.isFocused())
			return search_bar.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String search_barValue = search_bar.getValue();
		super.resize(minecraft, width, height);
		search_bar.setValue(search_barValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void init() {
		super.init();
		search_bar = new EditBox(this.font, this.leftPos + 52, this.topPos + -28, 118, 18, Component.translatable("gui.storage_upgrade.storage_terminal_gui.search_bar"));
		search_bar.setMaxLength(8192);
		search_bar.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "search_bar", content, false);
		});
		this.addWidget(this.search_bar);
		button_up = Button.builder(Component.translatable("gui.storage_upgrade.storage_terminal_gui.button_up"), e -> {
		}).bounds(this.leftPos + 177, this.topPos + 7, 35, 20).build();
		this.addRenderableWidget(button_up);
		button_down = Button.builder(Component.translatable("gui.storage_upgrade.storage_terminal_gui.button_down"), e -> {
		}).bounds(this.leftPos + 177, this.topPos + 61, 46, 20).build();
		this.addRenderableWidget(button_down);
	}
}