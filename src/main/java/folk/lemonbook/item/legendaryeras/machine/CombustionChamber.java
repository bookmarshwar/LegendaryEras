package folk.lemonbook.item.legendaryeras.machine;

import folk.lemonbook.item.legendaryeras.entity.CombustionChamberEntity;
import folk.lemonbook.item.legendaryeras.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

//ȼ����,�ṩ����
public class CombustionChamber extends BaseEntityBlock {
    //����Ч��,
    private float heat_efficiency=1f;
    //��Ч��������


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
                entity.drops();//�ж��Ƴ�ʱ�Ƿ�Ϊ��ȷʵ��,������Ʒ
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
    //��ȡticker
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type,EntityInit.COMBUSTION_CHAMBER_ENTITY.get(),CombustionChamberEntity::tick);
    }
}
