package net.mcreator.storageupgrade.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.storageupgrade.procedures.ConnectingCablesProcedure;
import net.mcreator.storageupgrade.init.StorageUpgradeModBlocks;

import javax.annotation.Nullable;

public class LaserCable6Block extends Block {
	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

	public LaserCable6Block(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.GLASS).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(7, 7, 7, 9, 9, 16), box(7, 9, 7, 9, 16, 9), box(6, 9, 9, 7, 10, 16), box(9, 9, 9, 10, 10, 16), box(9, 6, 6, 10, 7, 16), box(6, 6, 6, 7, 7, 16), box(6, 10, 9, 7, 16, 10), box(6, 7, 6, 7, 16, 7),
					box(9, 7, 6, 10, 16, 7), box(9, 10, 9, 10, 16, 10));
			case NORTH -> Shapes.or(box(7, 7, 0, 9, 9, 9), box(7, 9, 7, 9, 16, 9), box(9, 9, 0, 10, 10, 7), box(6, 9, 0, 7, 10, 7), box(6, 6, 0, 7, 7, 10), box(9, 6, 0, 10, 7, 10), box(9, 10, 6, 10, 16, 7), box(9, 7, 9, 10, 16, 10),
					box(6, 7, 9, 7, 16, 10), box(6, 10, 6, 7, 16, 7));
			case EAST -> Shapes.or(box(7, 7, 7, 16, 9, 9), box(7, 9, 7, 9, 16, 9), box(9, 9, 9, 16, 10, 10), box(9, 9, 6, 16, 10, 7), box(6, 6, 6, 16, 7, 7), box(6, 6, 9, 16, 7, 10), box(9, 10, 9, 10, 16, 10), box(6, 7, 9, 7, 16, 10),
					box(6, 7, 6, 7, 16, 7), box(9, 10, 6, 10, 16, 7));
			case WEST -> Shapes.or(box(0, 7, 7, 9, 9, 9), box(7, 9, 7, 9, 16, 9), box(0, 9, 6, 7, 10, 7), box(0, 9, 9, 7, 10, 10), box(0, 6, 9, 10, 7, 10), box(0, 6, 6, 10, 7, 7), box(6, 10, 6, 7, 16, 7), box(9, 7, 6, 10, 16, 7),
					box(9, 7, 9, 10, 16, 10), box(6, 10, 9, 7, 16, 10));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData, Player entity) {
		return new ItemStack(StorageUpgradeModBlocks.LASER_CABLE_1.get());
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		ConnectingCablesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, orientation, moving);
		ConnectingCablesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}