package folk.lemonbook.item.legendaryeras.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

//Ðîµç³Ø
public class Battery extends Block {
    public long getEnergy() {
        return energy;
    }

    public void setEnergy(long energy) {
        this.energy = energy;
    }

    private long energy_limit=100000000;//J
    private long energy=0;//
    public Battery() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
