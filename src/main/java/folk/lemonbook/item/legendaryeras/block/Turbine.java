package folk.lemonbook.item.legendaryeras.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
//���ֻ�
public class Turbine extends Block {
    public Turbine() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
