package folk.lemonbook.item.legendaryeras.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

//�����
//������ת��Ϊ����
//ת��Ӱ�췢������,����Ӱ�춯��ת���ܱ���
//����ѹ��ģ��,1.5f���ʷ���,-0.1f�������
public class Generator extends Block {

    public Generator() {
        super(Block.Properties.of(Material.STONE).strength(4f, 1200f));
    }
}
