// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class custom_model extends EntityModel {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer legs;
	private final ModelRenderer legR;
	private final ModelRenderer legL;
	private final ModelRenderer wings;
	private final ModelRenderer wingL;
	private final ModelRenderer wingR;

	public custom_model() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 20.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 30, 4, -1.0F, -5.0F, 9.0F, 2, 2, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -4.0F, -5.0F, -7.0F, 8, 5, 14, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 19, -4.0F, -6.0F, -7.0F, 8, 1, 12, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 11, -3.0F, -6.0F, 5.0F, 6, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 19, -2.0F, -6.0F, 6.0F, 4, 1, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 6, -3.0F, -5.0F, 7.0F, 6, 4, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 27, -2.0F, -5.0F, 8.0F, 4, 3, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -3.0F, -6.0F, -8.0F, 6, 5, 1, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -5.0F, -6.0F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 28, 19, -2.0F, -9.0F, -2.0F, 4, 8, 4, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 22, -1.0F, -8.0F, -5.0F, 2, 2, 3, 0.0F, false));

		legs = new ModelRenderer(this);
		legs.setRotationPoint(0.0F, 4.0F, -1.0F);
		body.addChild(legs);

		legR = new ModelRenderer(this);
		legR.setRotationPoint(-1.0F, -4.0F, 1.0F);
		legs.addChild(legR);
		legR.cubeList.add(new ModelBox(legR, 10, 26, -1.0F, 0.0F, 0.0F, 1, 4, 0, 0.0F, false));
		legR.cubeList.add(new ModelBox(legR, 28, 2, -2.0F, 4.0F, -2.0F, 3, 0, 2, 0.0F, false));

		legL = new ModelRenderer(this);
		legL.setRotationPoint(1.0F, -4.0F, 1.0F);
		legs.addChild(legL);
		legL.cubeList.add(new ModelBox(legL, 10, 22, 0.0F, 0.0F, 0.0F, 1, 4, 0, 0.0F, false));
		legL.cubeList.add(new ModelBox(legL, 28, 0, -1.0F, 4.0F, -2.0F, 3, 0, 2, 0.0F, false));

		wings = new ModelRenderer(this);
		wings.setRotationPoint(0.0F, 5.0F, 0.0F);
		body.addChild(wings);

		wingL = new ModelRenderer(this);
		wingL.setRotationPoint(4.0F, -10.0F, 0.0F);
		wings.addChild(wingL);
		wingL.cubeList.add(new ModelBox(wingL, 31, 31, 0.0F, 0.0F, -5.0F, 1, 4, 9, 0.0F, false));

		wingR = new ModelRenderer(this);
		wingR.setRotationPoint(-4.0F, -10.0F, 0.0F);
		wings.addChild(wingR);
		wingR.cubeList.add(new ModelBox(wingR, 30, 0, -1.0F, 0.0F, -5.0F, 1, 4, 9, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}