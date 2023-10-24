package folk.lemonbook.item.legendaryeras.entity;

import folk.lemonbook.item.legendaryeras.gui.CokingFurnaceMenu;
import folk.lemonbook.item.legendaryeras.init.EntityInit;
import folk.lemonbook.item.legendaryeras.init.ItemInit;
import folk.lemonbook.item.legendaryeras.machine.CombustionChamber;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

//焦化炉
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
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot){
                case 0->true;
                default ->super.isItemValid(slot,stack);
            };
        }

    };
    private final Map<Direction, LazyOptional<WrappedHandler>> directionLazyOptionalMap= Map.of(
            Direction.DOWN,LazyOptional.of(()->new WrappedHandler(itemStackHandler,(i)->i==0,(i,s)->itemStackHandler.isItemValid(0,s))),
            Direction.NORTH, LazyOptional.of(()->new WrappedHandler(itemStackHandler,(i)->i==0,(i,s)->itemStackHandler.isItemValid(0,s))),
            Direction.SOUTH, LazyOptional.of(()->new WrappedHandler(itemStackHandler,(i)->i==0,(i,s)->itemStackHandler.isItemValid(0,s))),
            Direction.EAST, LazyOptional.of(()->new WrappedHandler(itemStackHandler,(i)->i==0,(i,s)->itemStackHandler.isItemValid(0,s))),
            Direction.WEST, LazyOptional.of(()->new WrappedHandler(itemStackHandler,(i)->i==0,(i,s)->itemStackHandler.isItemValid(0,s))),
            Direction.UP, LazyOptional.of(()->new WrappedHandler(itemStackHandler,(i)->i==0,(i,s)->itemStackHandler.isItemValid(0,s)))
    );
    public int progress=0;
    public int maxProgress=100;
    protected  final ContainerData data ;
    @Override
    public Component getDisplayName() {
        return Component.literal("焦化炉");
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
            entity.progress++;
            System.out.println(entity.progress);
            setChanged(level,pos,state);
            if(entity.progress>=entity.maxProgress){
                craftItem(entity);
                entity.resetProgress();
            }
        }else {
            setChanged(level,pos,state);
        }

    }

    private void resetProgress() {
        this.progress=0;
    }
    private static void craftItem(CokingFurnaceEntity entity) {
        if(hasPecipe(entity)){
            entity.itemStackHandler.extractItem(0,1,false);
            entity.itemStackHandler.setStackInSlot(1,new ItemStack(ItemInit.COKING_COAL.get(),entity.itemStackHandler.getStackInSlot(1).getCount()+1));
            entity.resetProgress();
        }
    }

    private static boolean hasPecipe(CokingFurnaceEntity entity) {//建立合成表
        SimpleContainer inventory =new SimpleContainer(entity.itemStackHandler.getSlots());
        for(int i=0;i<entity.itemStackHandler.getSlots();++i){
            inventory.setItem(i,entity.itemStackHandler.getStackInSlot(i));
        }
        boolean firstSlot=entity.itemStackHandler.getStackInSlot(0).getItem()== Items.COAL;//检查是否是煤炭
        return  firstSlot && canInsert_amount(inventory)&&canInsert_item(inventory,new ItemStack(ItemInit.COKING_COAL.get(),1));
    }

    private static boolean canInsert_amount(SimpleContainer inventory) {
        //判断是否可插入
        return  inventory.getItem(1).getMaxStackSize()> inventory.getItem(1).getCount();
    }
    private static boolean canInsert_item(SimpleContainer inventory, ItemStack itemStack) {
        return inventory.getItem(1).getItem()==itemStack.getItem()||inventory.getItem(1).isEmpty();
    }//判断容器是否为空或者同一物品可堆叠

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
        return new CokingFurnaceMenu(id,inv,this,this.data);
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            if(side==null){
                return lazyOptional.cast();
            }
            if(directionLazyOptionalMap.containsKey(side)){
                Direction direction=this.getBlockState().getValue(CombustionChamber.FACING);
                if (side==Direction.UP||side==Direction.DOWN){
                    return directionLazyOptionalMap.get(side).cast();
                }
                return switch (direction){
                    default -> directionLazyOptionalMap.get(side.getOpposite()).cast();
                    case EAST -> directionLazyOptionalMap.get(side.getClockWise()).cast();
                    case SOUTH -> directionLazyOptionalMap.get(side).cast();
                    case WEST -> directionLazyOptionalMap.get(side.getCounterClockWise()).cast();
                };
            }
            return lazyOptional.cast();
        }
        return super.getCapability(cap);
    }
}
