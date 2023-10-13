package folk.lemonbook.item.legendaryeras.machine;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.energy.IEnergyStorage;

//蓄电池

public class Battery extends Block implements  IEnergyStorage {
    private int energy_limit=100000000;//J
    private int energy=0;//
    private boolean can_extract=true;//可以输出
    private boolean can_receive=true;
    public Battery() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        //接收能量输入,参数为允许接收的最大能量和是否模拟判断
        //返回实际接收的能量
        int remain = getMaxEnergyStored() - getEnergyStored();  //剩余空间
        int actualReceive = Math.min(maxReceive, remain);      //实际接收量
        if (simulate) return actualReceive;                    //模拟判断
        energy += actualReceive;                               //添加能量到存储
        return actualReceive;                                 //返回接收量

    }
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        //输出存储能量,参数同上
        //返回实际输出的能量
        int actualExtract =Math.min(maxExtract,energy);
        if(simulate) return actualExtract;
        energy-=actualExtract;
        return actualExtract;
    }

    @Override
    public int getEnergyStored() {
        //获取当前存储的RF能量
        return  energy;
    }

    @Override
    public int getMaxEnergyStored() {
        //获取最大RF存储上限
        return  energy_limit;
    }

    @Override
    public boolean canExtract() {
        //判断方块是否允许输出能量
        //返回true或false
        return  can_extract;
    }

    @Override
    public boolean canReceive() {
        //判断方块是否允许接收能量输入
        //返回true或false
        return can_receive;
    }

}



