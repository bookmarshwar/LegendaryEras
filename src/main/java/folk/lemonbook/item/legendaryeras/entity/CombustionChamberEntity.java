package folk.lemonbook.item.legendaryeras.entity;

import folk.lemonbook.item.legendaryeras.gui.CombustionChamberMenu;
import folk.lemonbook.item.legendaryeras.init.EntityInit;
import folk.lemonbook.item.legendaryeras.init.ItemInit;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CombustionChamberEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(3) {

        @Override
        protected void onContentsChanged(int slot) {
            for(int i=0;i<itemStackHandler.getSlots();++i){
            System.out.println(i+""+itemStackHandler.getStackInSlot(i));}
            setChanged();
        }
    };
    //开启三个物品槽
    protected  final ContainerData data ;
    private  int progress=0;
    private  int maxProgress=78;

    private  LazyOptional<IItemHandler> lazyOptional =LazyOptional.empty();

    public CombustionChamberEntity(BlockPos pos, BlockState state) {
        super(EntityInit.COMBUSTION_CHAMBER_ENTITY.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CombustionChamberEntity.this.progress;
                    case 1 -> CombustionChamberEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CombustionChamberEntity.this.progress = value;
                    case 1 -> CombustionChamberEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }


    @Override
    public Component getDisplayName() {
        return Component.literal("stand is dabian,so good!");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new CombustionChamberMenu(id,inv,this,this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyOptional.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyOptional=LazyOptional.of(()->itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyOptional.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory",itemStackHandler.serializeNBT());
        nbt.putInt("Combustion.progress",this.progress);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
        this.progress=nbt.getInt("Combustion.progress");
        super.load(nbt);
    }
    public  void drops(){
        //破坏时物品掉出
        SimpleContainer inventory =new SimpleContainer((itemStackHandler.getSlots()));
        for(int i=0;i<itemStackHandler.getSlots();++i){
            inventory.setItem(i,itemStackHandler.getStackInSlot(i));

        }
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }
    public  static  void tick(Level level, BlockPos pos, BlockState state, CombustionChamberEntity entity){
        if(level.isClientSide){
            return;
        }//仅在服务端执行
        if(hasPecipe(entity)){
            //增加进度
            entity.progress++;
            setChanged(level,pos,state);
            if(entity.progress>= entity.maxProgress){
                craftItem(entity);//合成一个物品
            }
        }else {
            entity.resetProgress();
            setChanged(level,pos,state);
        }

    }

    private void resetProgress() {
        this.progress=0;
    }

    private static void craftItem(CombustionChamberEntity entity) {
        if(hasPecipe(entity)){
            entity.itemStackHandler.extractItem(1,1,false);
            entity.itemStackHandler.setStackInSlot(2,new ItemStack(ItemInit.PORTRAIT_SON.get(),entity.itemStackHandler.getStackInSlot(2).getCount()+1));
            entity.resetProgress();
        }
    }

    private static boolean hasPecipe(CombustionChamberEntity entity) {
        SimpleContainer inventory =new SimpleContainer(entity.itemStackHandler.getSlots());
        for(int i=0;i<entity.itemStackHandler.getSlots();++i){
            inventory.setItem(i,entity.itemStackHandler.getStackInSlot(i));
        }
        boolean firstSlot=entity.itemStackHandler.getStackInSlot(1).getItem()==ItemInit.PORTRAIT.get();
        return  firstSlot && canInsert_amount(inventory)&&canInsert_item(inventory,new ItemStack(ItemInit.PORTRAIT_SON.get(),1));
    }

    private static boolean canInsert_item(SimpleContainer inventory, ItemStack itemStack) {
        return inventory.getItem(2).getItem()==itemStack.getItem()||inventory.getItem(2).isEmpty();
    }//判断容器是否为空或者同一物品可堆叠

    private static boolean canInsert_amount(SimpleContainer inventory) {
        //判断是否可插入
        return  inventory.getItem(2).getMaxStackSize()> inventory.getItem(2).getCount();
    }

}
