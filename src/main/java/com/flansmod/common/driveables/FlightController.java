package com.flansmod.common.driveables;

import com.flansmod.common.FlansMod;
import com.flansmod.common.vector.Vector3f;

public class FlightController {
	//Aircraft control paramaters
	public float throttle;
	public float yawControl;
	public float pitchControl;
	public float rollControl;
	public EnumPlaneMode mode;

	//Physics parameters
	public float gravity = 1;
	public float drag = 0;
	public float thrust = 0;
	public float lift = 0;
	public Vector3f angularMomentum = new Vector3f(0,0,0);

	public float fuelConsumptionMultiplier = 2F;

	public void UpdateParams(EntityPlane plane)
	{
		throttle = plane.throttle;
		yawControl = plane.flapsYaw;
		pitchControl = (plane.flapsPitchLeft + plane.flapsPitchRight) / 2F;
		rollControl = (plane.flapsPitchRight - plane.flapsPitchLeft) / 2F;
		mode = plane.mode;
	}

	public void fly(EntityPlane plane)
	{
		PlaneType type = plane.getPlaneType();
		DriveableData data = plane.getDriveableData();
		UpdateParams(plane);
		SetAxes(plane);

		if (throttle > 0) {
			thrust = 0.01F * ((plane.isUnderWater() ? type.maxThrottleInWater : type.maxThrottle) + (data.engine == null ? 0 : data.engine.engineSpeed));
		} else {
			thrust = 0.01F * (type.maxNegativeThrottle + (data.engine == null ? 0 : data.engine.engineSpeed));
		}
		gravity = 0.98F / 10F;
		drag = 1F - (0.05F * type.drag);

		boolean doSpeedKick = false;
		if (plane.collisionHardness > 0.2F) {
			thrust = 0F;
			throttle = 0F;
			drag = 0.8F - ((float)plane.getSpeedXYZ()) < 0 ? 0 : 0.8F - ((float) plane.getSpeedXYZ());
			doSpeedKick = plane.getSpeedXYZ() > 0.1;
			plane.collisionHardness = 0F;
		}
		
		switch (mode) {
		case PLANE: {
			PlaneModeFly(plane);
			break;
		}
		case HELI: {
			HeliModeFly(plane);
			break;
		}

		// TODO: Look into other flight controllers
		case SIXDOF:
			break;
		case VTOL:
			break;

		}

		if (doSpeedKick) {
			// double speed = plane.getSpeedXYZ() * 2F;
			// plane.motionX -= plane.axes.getXAxis().x * speed;
			// plane.motionY -= plane.axes.getXAxis().y * speed;
			// plane.motionZ -= plane.axes.getXAxis().z * speed;
			plane.motionX = 0F;
			plane.motionZ = 0F;
		}

	}

	public void SetAxes(EntityPlane plane)
	{
		PlaneType type = plane.getPlaneType();
		//Set angles
		float pitchSensitivity = 0;
		float yawSensitivity = 0;
		float rollSensitivity = 0;

		switch (mode) {
			case PLANE: {
				float xSpeed = Vector3f.dot(plane.getLocalVelocity(), plane.axes.getXAxis());
				// Need some kind of max speed, here.
				float controlProportion = xSpeed/type.maxThrottle;
				FlansMod.log("XSpeed " + xSpeed);

				if (plane.wheels[0] != null && plane.wheels[1] != null && plane.wheels[0].onGround && plane.wheels[1].onGround) {
					yawSensitivity = controlProportion/4F;
				} else {
					yawSensitivity = controlProportion/8F;
				}

				pitchSensitivity = controlProportion/8F;
				rollSensitivity = controlProportion/8F;
				break;
			}
			case HELI: {
				// 
				break;
			}
		}

		float yaw = yawControl * (yawControl > 0 ? type.turnLeftModifier : type.turnRightModifier) * yawSensitivity;
		float pitch = pitchControl * (pitchControl > 0 ? type.lookUpModifier : type.lookDownModifier) * pitchSensitivity;
		float roll = rollControl * (rollControl > 0 ? type.rollLeftModifier : type.rollRightModifier) * rollSensitivity;

		//Damage modifiers
		if(mode == EnumPlaneMode.PLANE) {
			if(!plane.isPartIntact(EnumDriveablePart.tail)) {
				yaw = 0;
				pitch = 0;
			}
			if(!plane.isPartIntact(EnumDriveablePart.leftWing))
				roll -= 2F * plane.getSpeedXZ();
			if(!plane.isPartIntact(EnumDriveablePart.rightWing))
				roll += 2F * plane.getSpeedXZ();
		} else if(mode == EnumPlaneMode.HELI) {
			if(!plane.isPartIntact(EnumDriveablePart.tail)) {
				yaw = 10*throttle;
			}
		}

		angularMomentum.x = moveToTarget(angularMomentum.x, yaw, 1);
		angularMomentum.y = moveToTarget(angularMomentum.y, pitch, 1);
		angularMomentum.z = moveToTarget(angularMomentum.z, roll, 1);

		LimitAngularMomentum(angularMomentum, 20);

		plane.axes.rotateLocalYaw(angularMomentum.x);
		plane.axes.rotateLocalPitch(angularMomentum.y);
		plane.axes.rotateLocalRoll(-angularMomentum.z);
		
		angularMomentum.scale(0.99F);
	}
	
	public float moveToTarget(float current, float target, float speed)
	{	
		
		float pitchToMove = (float)((Math.sqrt(target*target)) - Math.sqrt((current*current)));
		for(; pitchToMove > 180F; pitchToMove -= 360F) {}
		for(; pitchToMove <= -180F; pitchToMove += 360F) {}
		
		if(pitchToMove == speed)
			return target;
		
		if(current > target) {
			current = current - speed;
		} else if(current < target) {
			current = current + speed;
		}
		
		return current;
	}

	public void LimitAngularMomentum(Vector3f vec, float angle)
	{
		//Limit X
		if(vec.x > angle)
			vec.x = angle;
		if(vec.x < -angle)
			vec.x = -angle;

		//Limit y
		if(vec.y > angle)
			vec.y = angle;
		if(vec.y < -angle)
			vec.y = -angle;

		//Limit z
		if(vec.z > angle)
			vec.z = angle;
		if(vec.z < -angle)
			vec.z = -angle;

	}

	public void PlaneModeFly(EntityPlane plane)
	{
		PlaneType type = plane.getPlaneType();
		DriveableData data = plane.getDriveableData();

		int numProps = 0;
		int numPropsWorking = 0;



		float flap = angularMomentum.length();
		drag -=flap/100;
		throttle -= - flap/500;
		//Count the number of working propellers
		for(Propeller prop : type.propellers)
			if(plane.isPartIntact(prop.planePart))
				numPropsWorking++;
		numProps = type.propellers.size();

		//Got no propellers. Derp.
		if(numProps == 0)
			return;

		//Apply forces
		Vector3f forwards = (Vector3f)plane.axes.getXAxis().normalise();

		//Sanity limiter
		float lastTickSpeed = (float)plane.getSpeedXYZ();
		if(lastTickSpeed > 2F) {
			lastTickSpeed = 2F;
		}
		// This basically limits plane speed to 2F.

		float newSpeed = lastTickSpeed + thrust * 2F;

		//Calculate the amount to alter motion by
		float proportionOfMotionToCorrect = 2F * Math.abs(throttle);
		if(proportionOfMotionToCorrect < 0F)
			proportionOfMotionToCorrect = 0F;
		if(proportionOfMotionToCorrect > 1.5F)
			proportionOfMotionToCorrect = 1.5F;


		//Apply lift
		int numWingsIntact = 0;
		if(plane.isPartIntact(EnumDriveablePart.rightWing)) numWingsIntact++;
		if(plane.isPartIntact(EnumDriveablePart.leftWing)) numWingsIntact++;

		lift = (float)plane.getSpeedXYZ() * (float)plane.getSpeedXYZ() * numWingsIntact / 2F;
		Vector3f up2 = (Vector3f)plane.axes.getYAxis().normalise();
		lift *= Math.sqrt(up2.y*up2.y);
		if(lift > gravity){
			// This literally stops the plane teleporting itself to brazil, vertically....
			lift = gravity;
		}
	
		//Cut out some motion for correction
		plane.motionX *= 1F - proportionOfMotionToCorrect;
		plane.motionY *= 1F - proportionOfMotionToCorrect;
		plane.motionZ *= 1F - proportionOfMotionToCorrect;
	

		//Add the corrected motion
		plane.motionX += proportionOfMotionToCorrect * newSpeed * forwards.x;
		plane.motionY += proportionOfMotionToCorrect * newSpeed * forwards.y;
		plane.motionZ += proportionOfMotionToCorrect * newSpeed * forwards.z;
		
		plane.motionY += lift;

		// Wheels are actually broken, only two of them will be 'on the ground'.
		// If we don't do this, the gravity will keep being applied, giving a speed of 0.3 with no motion.
		if (plane.wheels[0] != null && plane.wheels[1] != null && plane.wheels[0].onGround && plane.wheels[1].onGround) {
			plane.motionY = -0.01;
		} else {
			plane.motionY -= gravity;
		}

		if(!plane.isPartIntact(EnumDriveablePart.rightWing) && !plane.isPartIntact(EnumDriveablePart.rightWing)){
			plane.motionY += -1;
		}

		//Apply drag
		plane.motionX *= drag;
		if(plane.posY - plane.prevPosY < 0)
		{
			plane.motionY *= drag < 1? 0.999: 1;
		}
		else
		{
			plane.motionY *= drag;
		}
		plane.motionZ *= drag;

		plane.lastPos = new Vector3f(plane.motionX, plane.motionY, plane.motionZ);
		if (!plane.driverIsCreative()) {
			if (data.fuelInTank >= thrust * fuelConsumptionMultiplier * data.engine.fuelConsumption) {
				data.fuelInTank -= thrust * fuelConsumptionMultiplier * data.engine.fuelConsumption;
			} else {
				data.fuelInTank = 0;
			}
		}

		
		
		// Turbulence at high speed
		if(plane.getSpeedXYZ() > 2){
			plane.axes.rotateLocalPitch(((float)Math.random()-0.5F)/4);
			plane.axes.rotateLocalYaw(((float)Math.random()-0.5F)/4);
			plane.axes.rotateLocalRoll(((float)Math.random()-0.5F)/4);
		}

	}

	public void HeliModeFly(EntityPlane plane)
	{
		PlaneType type = plane.getPlaneType();
		DriveableData data = plane.getDriveableData();
		
		int numPropsWorking = 0;
		int numProps = 0;

		//Count the number of working propellers
		for(Propeller prop : type.heliPropellers)
			if(plane.isPartIntact(prop.planePart))
				numPropsWorking++;
		numProps = type.heliPropellers.size();
		gravity = 0.05F;
		//Got no propellers. Derp.
		if(numProps == 0)
			return;

		Vector3f up = (Vector3f)plane.axes.getYAxis().normalise();

		thrust *= numPropsWorking / numProps * 2F;

		float upwardsForce = throttle * thrust + (gravity - thrust / 2F);
		if(throttle < 0.5F)
			upwardsForce = gravity * throttle * 2F;

		if(!plane.isPartIntact(EnumDriveablePart.blades))
		{
			upwardsForce = 0F;
		}

		if(throttle < 0.52F && throttle > 0.48F && up.y >= 0.7F){
			upwardsForce = gravity/up.y;
		}
		if(plane.getPlaneType().mode != EnumPlaneMode.VTOL){
			//Move up
			if(up.y < 0){
				up.y*=-1;
				up.x*=-1;
				up.z*=-1;
			}
		}

		//Throttle - 0.5 means that the positive throttle scales from -0.5 to +0.5. Thus it accounts for gravity-ish
		plane.motionX += upwardsForce * up.x * 0.5F;
		plane.motionY += (upwardsForce * up.y) - gravity;
		plane.motionZ += upwardsForce * up.z * 0.5F;

		//Apply wobble
		//motionX += rand.nextGaussian() * wobbleFactor;
		//motionY += rand.nextGaussian() * wobbleFactor;
		//motionZ += rand.nextGaussian() * wobbleFactor;
		//Apply drag
		plane.motionX *= 1-(1-drag)/5;
		plane.motionY *= drag;
		plane.motionZ *= 1-(1-drag)/5;

		plane.lastPos = new Vector3f(plane.motionX, plane.motionY, plane.motionZ);

		if (!plane.driverIsCreative()) {
			if (data.fuelInTank >= upwardsForce * fuelConsumptionMultiplier * data.engine.fuelConsumption) {
				data.fuelInTank -= upwardsForce * fuelConsumptionMultiplier * data.engine.fuelConsumption;
			} else {
				data.fuelInTank = 0;
			}
		}
	}

}
