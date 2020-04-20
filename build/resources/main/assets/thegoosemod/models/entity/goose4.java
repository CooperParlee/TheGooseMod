// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class custom_model extends EntityModel {
	private final RendererModel body;
	private final RendererModel leftWing;
	private final RendererModel rightWing;
	private final RendererModel head;
	private final RendererModel leftLeg;
	private final RendererModel rightLeg;

	public custom_model() {
		textureWidth = 64;
		textureHeight = 64;

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, 20.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 30, 4, -1.0F, -5.0F, 9.0F, 2, 2, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -4.0F, -5.0F, -7.0F, 8, 5, 14, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 19, -4.0F, -6.0F, -7.0F, 8, 1, 12, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 11, -3.0F, -6.0F, 5.0F, 6, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 19, -2.0F, -6.0F, 6.0F, 4, 1, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 6, -3.0F, -5.0F, 7.0F, 6, 4, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 27, -2.0F, -5.0F, 8.0F, 4, 3, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -3.0F, -6.0F, -8.0F, 6, 5, 1, 0.0F, false));

		leftWing = new RendererModel(this);
		leftWing.setRotationPoint(4.0F, 15.0F, 0.0F);
		leftWing.cubeList.add(new ModelBox(leftWing, 31, 31, 0.0F, 0.0F, -5.0F, 1, 4, 9, 0.0F, false));

		rightWing = new RendererModel(this);
		rightWing.setRotationPoint(-4.0F, 15.0F, 0.0F);
		rightWing.cubeList.add(new ModelBox(rightWing, 30, 0, -1.0F, 0.0F, -5.0F, 1, 4, 9, 0.0F, false));

		head = new RendererModel(this);
		head.setRotationPoint(0.0F, 15.0F, -6.0F);
		head.cubeList.add(new ModelBox(head, 28, 19, -2.0F, -9.0F, -2.0F, 4, 8, 4, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 22, -1.0F, -8.0F, -5.0F, 2, 2, 3, 0.0F, false));

		leftLeg = new RendererModel(this);
		leftLeg.setRotationPoint(1.0F, 20.0F, 0.0F);
		leftLeg.cubeList.add(new ModelBox(leftLeg, 10, 22, 0.0F, 0.0F, 0.0F, 1, 4, 0, 0.0F, false));
		leftLeg.cubeList.add(new ModelBox(leftLeg, 28, 0, -1.0F, 4.0F, -2.0F, 3, 0, 2, 0.0F, false));

		rightLeg = new RendererModel(this);
		rightLeg.setRotationPoint(-1.0F, 20.0F, 0.0F);
		rightLeg.cubeList.add(new ModelBox(rightLeg, 10, 26, -1.0F, 0.0F, 0.0F, 1, 4, 0, 0.0F, false));
		rightLeg.cubeList.add(new ModelBox(rightLeg, 28, 2, -2.0F, 4.0F, -2.0F, 3, 0, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
		leftWing.render(f5);
		rightWing.render(f5);
		head.render(f5);
		leftLeg.render(f5);
		rightLeg.render(f5);
	}
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}