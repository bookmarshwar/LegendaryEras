package folk.lemonbook.item.legendaryeras.machine;

import net.minecraft.world.level.block.AbstractBannerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

//���ֻ�,��������,��������
public class Turbine extends Block {

    public Turbine() {
        super(AbstractBannerBlock.Properties.of(Material.STONE).strength(4f, 1200f));

    }



}
//ȼ����->������¯->���ֻ�->�����(���������)