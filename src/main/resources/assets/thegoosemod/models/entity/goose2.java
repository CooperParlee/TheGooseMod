// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class custom_model extends EntityModel {
	private final ModelRenderer Body;
	private final ModelRenderer Head;
	private final ModelRenderer Legs;
	private final ModelRenderer LegR;
	private final ModelRenderer LegL;
	private final ModelRenderer Wings;
	private final ModelRenderer WingL;
	private final ModelRenderer WingR;

	public custom_model() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 20.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 30, 0, -1.0F, -5.0F, 9.0F, 2, 2, 1, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 0, -4.0F, -5.0F, -7.0F, 8, 5, 14, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 19, -4.0F, -6.0F, -7.0F, 8, 1, 12, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 11, -3.0F, -6.0F, 5.0F, 6, 1, 1, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 19, -2.0F, -6.0F, 6.0F, 4, 1, 2, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 6, -3.0F, -5.0F, 7.0F, 6, 4, 1, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 22, -2.0F, -5.0F, 8.0F, 4, 3, 1, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 0, -3.0F, -6.0F, -8.0F, 6, 5, 1, 0.0F, false));

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(Head);
		Head.cubeList.add(new ModelBox(Head, 28, 19, -2.0F, -14.0F, -8.0F, 4, 8, 4, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 26, -1.0F, -12.0F, -10.0F, 2, 1, 2, 0.0F, false));

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 4.0F, -1.0F);
		Body.addChild(Legs);

		LegR = new ModelRenderer(this);
		LegR.setRotationPoint(0.0F, 0.0F, 0.0F);
		Legs.addChild(LegR);
		LegR.cubeList.add(new ModelBox(LegR, 8, 26, -2.0F, -4.0F, 1.0F, 1, 4, 0, 0.0F, false));
		LegR.cubeList.add(new ModelBox(LegR, 6, 13, -3.0F, 0.0F, 0.0F, 3, 0, 1, 0.0F, false));
		LegR.cubeList.add(new ModelBox(LegR, 9, 20, -2.0F, 0.0F, -1.0F, 1, 0, 1, 0.0F, false));

		LegL = new ModelRenderer(this);
		LegL.setRotationPoint(3.0F, 0.0F, 0.0F);
		Legs.addChild(LegL);
		LegL.cubeList.add(new ModelBox(LegL, 10, 22, -2.0F, -4.0F, 1.0F, 1, 4, 0, 0.0F, false));
		LegL.cubeList.add(new ModelBox(LegL, 0, 13, -3.0F, 0.0F, 0.0F, 3, 0, 1, 0.0F, false));
		LegL.cubeList.add(new ModelBox(LegL, 9, 19, -2.0F, 0.0F, -1.0F, 1, 0, 1, 0.0F, false));

		Wings = new ModelRenderer(this);
		Wings.setRotationPoint(0.0F, 5.0F, 0.0F);
		Body.addChild(Wings);

		WingL = new ModelRenderer(this);
		WingL.setRotationPoint(0.0F, -4.0F, 0.0F);
		Wings.addChild(WingL);
		WingL.cubeList.add(new ModelBox(WingL, 31, 31, 4.0F, -6.0F, -5.0F, 1, 4, 9, 0.0F, false));

		WingR = new ModelRenderer(this);
		WingR.setRotationPoint(0.0F, -4.0F, 0.0F);
		Wings.addChild(WingR);
		WingR.cubeList.add(new ModelBox(WingR, 30, 0, -5.0F, -6.0F, -5.0F, 1, 4, 9, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Body.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}