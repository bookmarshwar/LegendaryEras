package folk.lemonbook.item.legendaryeras.machine;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

//�����
//������ת��Ϊ����
//ת��Ӱ�췢������,����Ӱ�춯��ת���ܱ���
//����ѹ��ģ��,1.5f���ʷ���,-0.1f�������(��ʱ��д
public class Generator extends Block {

    public Generator() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
