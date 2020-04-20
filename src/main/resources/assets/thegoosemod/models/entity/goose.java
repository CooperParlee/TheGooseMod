private ModelRenderer bb_main;
private ModelRenderer beak;
private ModelRenderer body;

public EntityGooseModel() {
	textureWidth = 64;
	textureHeight = 64;

	bb_main = new ModelRenderer(this);
	bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
	bb_main.cubeList.add(new ModelRenderer.ModelBox(bb_main, 13, 14, -4.0F, -5.0F, -4.0F, 6, 3, 1, 0.0F, false));
	bb_main.cubeList.add(new ModelRenderer.ModelBox(bb_main, 0, 11, -4.0F, -5.0F, 3.0F, 6, 3, 1, 0.0F, false));

	beak = new ModelRenderer(this);
	beak.setRotationPoint(0.0F, 24.0F, 0.0F);
	beak.cubeList.add(new ModelRenderer.ModelBox(beak, 0, 0, -8.0F, -10.0F, -1.0F, 1, 1, 2, 0.0F, false));
	beak.cubeList.add(new ModelRenderer.ModelBox(beak, 20, 20, -7.0F, -11.0F, -1.0F, 2, 6, 2, 0.0F, false));

	body = new ModelRenderer(this);
	body.setRotationPoint(0.0F, 24.0F, 0.0F);
	body.cubeList.add(new ModelRenderer.ModelBox(body, 0, 0, -6.0F, -5.0F, -3.0F, 12, 5, 6, 0.0F, false));
	body.cubeList.add(new ModelRenderer.ModelBox(body, 0, 15, -7.0F, -5.0F, -2.0F, 1, 4, 4, 0.0F, false));
	body.cubeList.add(new ModelRenderer.ModelBox(body, 10, 18, 6.0F, -5.0F, -2.0F, 1, 3, 4, 0.0F, false));
}

@Override
public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	bb_main.render(f5);
	beak.render(f5);
	body.render(f5);
}
public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
	modelRenderer.rotateAngleX = x;
	modelRenderer.rotateAngleY = y;
	modelRenderer.rotateAngleZ = z;
}
