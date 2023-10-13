package folk.lemonbook.item.legendaryeras.machine;

import net.minecraft.world.level.block.AbstractBannerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

//涡轮机,输入蒸气,产生动能
public class Turbine extends Block {

    public Turbine() {
        super(AbstractBannerBlock.Properties.of(Material.STONE).strength(4f, 1200f));

    }



}
//燃烧室->蒸气锅炉->涡轮机->发电机(火力发电机)