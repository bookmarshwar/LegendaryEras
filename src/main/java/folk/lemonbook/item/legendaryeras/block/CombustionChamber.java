package folk.lemonbook.item.legendaryeras.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

//ȼ����,�ṩ����
public class CombustionChamber extends Block {
    //����Ч��,
    private float heat_efficiency=1f;
    //��Ч��������
    public CombustionChamber() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
