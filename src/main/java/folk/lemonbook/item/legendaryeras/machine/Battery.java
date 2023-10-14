package folk.lemonbook.item.legendaryeras.machine;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.energy.IEnergyStorage;

//����

public class Battery extends Block implements  IEnergyStorage {
    private int energy_limit=100000000;//J
    private int energy=0;//
    private boolean can_extract=true;//�������
    private boolean can_receive=true;
    public Battery() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        //������������,����Ϊ������յ�����������Ƿ�ģ���ж�
        //����ʵ�ʽ��յ�����
        int remain = getMaxEnergyStored() - getEnergyStored();  //ʣ��ռ�
        int actualReceive = Math.min(maxReceive, remain);      //ʵ�ʽ�����
        if (simulate) return actualReceive;                    //ģ���ж�
        energy += actualReceive;                               //����������洢
        return actualReceive;                                 //���ؽ�����

    }
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        //����洢����,����ͬ��
        //����ʵ�����������
        int actualExtract =Math.min(maxExtract,energy);
        if(simulate) return actualExtract;
        energy-=actualExtract;
        return actualExtract;
    }

    @Override
    public int getEnergyStored() {
        //��ȡ��ǰ�洢��RF����
        return  energy;
    }

    @Override
    public int getMaxEnergyStored() {
        //��ȡ���RF�洢����
        return  energy_limit;
    }

    @Override
    public boolean canExtract() {
        //�жϷ����Ƿ������������
        //����true��false
        return  can_extract;
    }

    @Override
    public boolean canReceive() {
        //�жϷ����Ƿ����������������
        //����true��false
        return can_receive;
    }

}



