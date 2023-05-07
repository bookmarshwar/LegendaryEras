package folk.lemonbook.item.legendaryeras.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class PotentialEnergyGenerator extends Block {

    public PotentialEnergyGenerator() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
