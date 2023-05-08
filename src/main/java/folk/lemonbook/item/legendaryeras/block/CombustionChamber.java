package folk.lemonbook.item.legendaryeras.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

//燃烧室,提供热能
public class CombustionChamber extends Block {
    //热能效率,
    private float heat_efficiency=1f;
    //热效增加升级
    public CombustionChamber() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
