package folk.lemonbook.item.legendaryeras.machine;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
//��ȡ���Բ������ܵĻ����Ӷ���������
public class PotentialEnergyConverter extends Block {

    public PotentialEnergyConverter() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
