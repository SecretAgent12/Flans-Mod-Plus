package com.flansmod.client.model;

import net.minecraft.client.model.ModelBase;

import com.flansmod.client.tmt.ModelRendererTurbo;

public class ModelAttachment extends ModelBase 
{
	public ModelRendererTurbo[] attachmentModel = new ModelRendererTurbo[0];
	public ModelRendererTurbo[] ammoModel = new ModelRendererTurbo[0];

	public EnumAnimationType secondaryAnimType = EnumAnimationType.NONE;
	public float tiltGunTime = 0.25F, unloadClipTime = 0.25F, loadClipTime = 0.25F, untiltGunTime = 0.25F;
	/** For rifles and shotguns. Currently a generic reload animation regardless of how full the internal magazine already is */
	public float numBulletsInReloadAnimation = 1;
	/** For end loaded projectiles */
	public float endLoadedAmmoDistance = 1F;
	/** For big scopes, so that the player actually looks through them properly */
	public float renderOffset = 0F;
	/** Visual recoil of the gun when firing */
	public float RecoilSlideDistance = 2F / 16F;
	public float RotateSlideDistance = -8F;
	
	public void renderAttachment(float f)
	{
		for(ModelRendererTurbo model : attachmentModel)
			if(model != null)
				model.render(f);
	}

	public void renderAttachmentAmmo(float f)
	{
		for(ModelRendererTurbo model : ammoModel)
			if(model != null)
				model.render(f);
	}

	public void flipAll()
	{
		for (ModelRendererTurbo anAttachmentModel : attachmentModel)
		{
			anAttachmentModel.doMirror(false, true, true);
			anAttachmentModel.setRotationPoint(anAttachmentModel.rotationPointX, -anAttachmentModel.rotationPointY, -anAttachmentModel.rotationPointZ);
		}

		for (ModelRendererTurbo anAmmoModel : ammoModel)
		{
			anAmmoModel.doMirror(false, true, true);
			anAmmoModel.setRotationPoint(anAmmoModel.rotationPointX, -anAmmoModel.rotationPointY, -anAmmoModel.rotationPointZ);
		}
	}
}
