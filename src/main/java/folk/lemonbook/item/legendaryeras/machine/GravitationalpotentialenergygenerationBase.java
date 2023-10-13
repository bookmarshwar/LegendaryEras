package folk.lemonbook.item.legendaryeras.machine;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
//重力势能发生底座
public class GravitationalpotentialenergygenerationBase extends Block {

    public GravitationalpotentialenergygenerationBase() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
