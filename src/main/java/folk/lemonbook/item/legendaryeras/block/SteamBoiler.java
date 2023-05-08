package folk.lemonbook.item.legendaryeras.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

//蒸气锅炉,将水转换为高压蒸气
public class SteamBoiler extends Block {
    private int heat_energy =0;//热能 //创建时为零嘛
    public SteamBoiler() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
