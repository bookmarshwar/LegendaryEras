package folk.lemonbook.item.legendaryeras.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

//������¯,��ˮת��Ϊ��ѹ����
public class SteamBoiler extends Block {
    private int heat_energy =0;//���� //����ʱΪ����
    public SteamBoiler() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
