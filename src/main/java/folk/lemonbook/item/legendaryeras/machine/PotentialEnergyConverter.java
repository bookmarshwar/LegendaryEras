package folk.lemonbook.item.legendaryeras.machine;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
//读取可以产生势能的机器从而生成势能
public class PotentialEnergyConverter extends Block {

    public PotentialEnergyConverter() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
