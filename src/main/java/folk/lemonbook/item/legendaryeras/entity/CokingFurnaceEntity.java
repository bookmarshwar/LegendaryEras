package folk.lemonbook.item.legendaryeras.entity;

import folk.lemonbook.item.legendaryeras.gui.CombustionChamberMenu;
import folk.lemonbook.item.legendaryeras.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;
//����¯
public class CokingFurnaceEntity extends BlockEntity implements MenuProvider {
    private  LazyOptional<IItemHandler> lazyOptional =LazyOptional.empty();
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
        return Component.literal("����¯");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        return null;
    }

    public  void drops(){
        //�ƻ�ʱ��Ʒ����
        SimpleContainer inventory =new SimpleContainer((itemStackHandler.getSlots()));
        for(int i=0;i<itemStackHandler.getSlots();++i){
            inventory.setItem(i,itemStackHandler.getStackInSlot(i));

        }
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }

    public  static  void tick(Level level, BlockPos pos, BlockState state, CokingFurnaceEntity entity){
        if(level.isClientSide){
            return;
        }//���ڷ����ִ��
        if(hasPecipe(entity)){
            //���ӽ���
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

    private static boolean hasPecipe(CokingFurnaceEntity entity) {//�����ϳɱ�
        SimpleContainer inventory =new SimpleContainer(entity.itemStackHandler.getSlots());
        for(int i=0;i<entity.itemStackHandler.getSlots();++i){
            inventory.setItem(i,entity.itemStackHandler.getStackInSlot(i));
        }
        boolean firstSlot=entity.itemStackHandler.getStackInSlot(0).getItem()== Items.COAL;//����Ƿ���ú̿
        return  firstSlot && canInsert_amount(inventory)&&canInsert_item(inventory,new ItemStack(Items.COAL,1));
    }

    private static boolean canInsert_amount(SimpleContainer inventory) {
        //�ж��Ƿ�ɲ���
        return  inventory.getItem(1).getMaxStackSize()> inventory.getItem(1).getCount();
    }
    private static boolean canInsert_item(SimpleContainer inventory, ItemStack itemStack) {
        return inventory.getItem(1).getItem()==itemStack.getItem()||inventory.getItem(1).isEmpty();
    }//�ж������Ƿ�Ϊ�ջ���ͬһ��Ʒ�ɶѵ�

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory",itemStackHandler.serializeNBT());
        nbt.putInt("Coking.progress",this.progress);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
        this.progress=nbt.getInt("Coking.progress");
        super.load(nbt);
    }
    @Override
    public void onLoad() {
        super.onLoad();
        lazyOptional= LazyOptional.of(()->itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyOptional.invalidate();
    }
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new menu(id,inv,this,this.data);
    }
}