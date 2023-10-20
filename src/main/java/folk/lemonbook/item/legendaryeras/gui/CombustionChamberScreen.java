package folk.lemonbook.item.legendaryeras.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import folk.lemonbook.item.legendaryeras.Main;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CombustionChamberScreen extends AbstractContainerScreen<CombustionChamberMenu> {
    private  static  final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID,"textures/gui/dbian2.png");
    public CombustionChamberScreen(CombustionChamberMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.setShaderColor(1.0F,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0,TEXTURE);
        imageWidth=160;
        imageHeight=150;
        int x=(width-imageWidth)/2;
        int y= (height-imageHeight)/2;
        this.blit(pPoseStack,x,y,10,10,imageWidth,imageHeight);
        renderProgressArrow(pPoseStack,x,y);
    }

    private void renderProgressArrow(PoseStack pPoseStack, int x, int y) {
        if(menu.isCrafting()){
            blit(pPoseStack,x+105,y+33,176,0,8, menu.getScaleProgress());
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int x, int y, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, x, y, delta);
        renderTooltip(pPoseStack,x,y);
    }

    @Override
    protected void init() {
        super.init();

    }

}
