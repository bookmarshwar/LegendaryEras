package folk.lemonbook.item.legendaryeras.block;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

//����ת����,������ת��Ϊ����
public class KineticEnergyConverter extends Block {

    public KineticEnergyConverter() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
