package folk.lemonbook.item.legendaryeras.block;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

//���ܷ�����
public class KineticEnergyGenerator extends Block {

    public KineticEnergyGenerator() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
