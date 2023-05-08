package folk.lemonbook.item.legendaryeras.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

//发电机
//将动能转换为电能
//转子影响发电上限,定子影响动能转电能比例
//动能压缩模块,1.5f倍率发电,-0.1f倍率损耗
public class Generator extends Block {

    public Generator() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
