package folk.lemonbook.item.legendaryeras.machine;

import folk.lemonbook.item.legendaryeras.entity.CombustionChamberEntity;
import folk.lemonbook.item.legendaryeras.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

//燃烧室,提供热能
public class CombustionChamber extends BaseEntityBlock {
    public   static  final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    //热能效率,
    private float heat_efficiency=1f;
    //热效增加升级


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return defaultBlockState().setValue(FACING,blockPlaceContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING,rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);//增加属性
    }

    public CombustionChamber() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CombustionChamberEntity(pos,state);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.getBlock()!=newState.getBlock()){
            BlockEntity blockEntity=level.getBlockEntity(pos);
            if(blockEntity instanceof CombustionChamberEntity entity){
                entity.drops();//判断移除时是否为正确实体,掉落物品
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult hitResult) {

        if(!level.isClientSide()){
            BlockEntity entity=level.getBlockEntity(pos);
            if(entity instanceof CombustionChamberEntity){
                NetworkHooks.openScreen((ServerPlayer) player,(CombustionChamberEntity) entity,pos);
            }
            else {
                try {
                    throw  new IllegalAccessException(("entity is error,container is missing! "));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return super.use(state, level, pos, player, interactionHand, hitResult);
    }
    //获取ticker
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type,EntityInit.COMBUSTION_CHAMBER_ENTITY.get(),CombustionChamberEntity::tick);
    }
}
