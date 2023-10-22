package folk.lemonbook.item.legendaryeras.entity;

import folk.lemonbook.item.legendaryeras.init.EntityInit;
import folk.lemonbook.item.legendaryeras.machine.CokingFurnace;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;
//焦化炉
public class CokingFurnaceEntity extends BlockEntity implements MenuProvider {
    public CokingFurnaceEntity(BlockPos pos, BlockState state) {
        super(EntityInit.COKING_FURNACE_ENTITY.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CokingFurnaceEntity.this.progress;
                    case 1 -> CokingFurnaceEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CokingFurnaceEntity.this.progress = value;
                    case 1 -> CokingFurnaceEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(2){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

    };

    public int progress=0;
    public int maxProgress=100;
    protected  final ContainerData data ;
    @Override
    public Component getDisplayName() {
        return Component.literal("焦化炉");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        return null;
    }

    public  void drops(){
        //破坏时物品掉出
        SimpleContainer inventory =new SimpleContainer((itemStackHandler.getSlots()));
        for(int i=0;i<itemStackHandler.getSlots();++i){
            inventory.setItem(i,itemStackHandler.getStackInSlot(i));

        }
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }

    public  static  void tick(Level level, BlockPos pos, BlockState state, CokingFurnaceEntity entity){
        if(level.isClientSide){
            return;
        }//仅在服务端执行
        if(hasPecipe(entity)){
            //增加进度
            System.out.println("heat:"+entity.progress);
            craftHeatEnergy(entity);
            setChanged(level,pos,state);
            if(entity.progress>entity.maxProgress){

                entity.resetProgress();
            }
        }else {
            setChanged(level,pos,state);
        }

    }

    private void resetProgress() {
        this.progress=0;
    }

    private static void craftHeatEnergy(CokingFurnaceEntity entity) {
    }

    private static boolean hasPecipe(CokingFurnaceEntity entity) {
    }


}
