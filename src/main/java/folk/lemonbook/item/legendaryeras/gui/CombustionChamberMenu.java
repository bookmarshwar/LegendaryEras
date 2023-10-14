package folk.lemonbook.item.legendaryeras.gui;

import folk.lemonbook.item.legendaryeras.entity.CombustionChamberEntity;
import folk.lemonbook.item.legendaryeras.init.BlockInit;
import folk.lemonbook.item.legendaryeras.init.MenuInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class CombustionChamberMenu  extends AbstractContainerMenu {
    public  final CombustionChamberEntity entity;
    private  final Level level;
    private  final ContainerData data;

    public CombustionChamberMenu(int id, Inventory inventory, FriendlyByteBuf extraData){
        this(id,inventory,inventory.player.level.getBlockEntity(extraData.readBlockPos()),new SimpleContainerData(2));
    }

    public CombustionChamberMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(MenuInit.COMBUSTION_CHAMBER_MENU.get(),id);
        checkContainerSize(inv,3);//检查机器是否为三个槽位
        this.entity=(CombustionChamberEntity) entity;
        this.level =inv.player.level;
        this.data=data;
        //处理玩家物品
        addPlayerInv(inv);
        addPlayerHotbar(inv);
        //绘制menu
        this.entity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler->{
            this.addSlot(new SlotItemHandler(handler,0,12,15));
            this.addSlot(new SlotItemHandler(handler,1,86,15));
            this.addSlot(new SlotItemHandler(handler,2,86,60));

        });
        addDataSlots(data);
    }

    private void addPlayerHotbar(Inventory inv) {
        for(int i=0;i<9;++i){
            this.addSlot(new Slot(inv,i,8+i*18,144));
        }
    }

    private void addPlayerInv(Inventory inv) {//将玩家背包绘制到机器ui
        for(int i=0;i<3;++i){
            for(int l=0;l<9;++l){
                this.addSlot(new Slot(inv,l+i*9+9,8+l*18,86+i*18));
            }
        }
    }

    public boolean isCrafting(){
        return data.get(0)>0;
    }
    public int getScaleProgress(){
        int progress=this.data.get(0);
        int maxProgress=this.data.get(1);
        int progressArrowSize=26;

        return maxProgress !=0 && progress!=0 ?progress *progressArrowSize/maxProgress :0;
    }
    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level,entity.getBlockPos()),player, BlockInit.COMBUSTION_CHAMBER.get());
    }
}
