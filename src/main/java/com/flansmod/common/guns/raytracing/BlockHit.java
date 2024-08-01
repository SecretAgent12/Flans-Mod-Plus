package com.flansmod.common.guns.raytracing;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class BlockHit extends BulletHit 
{
	public MovingObjectPosition raytraceResult;
	
	public BlockHit(MovingObjectPosition mop, float f) 
	{
		super(f);
		raytraceResult = mop;
	}

	public static void writeMOPToByteBuf(MovingObjectPosition mop, ByteBuf buffer) {
		// Запись типа попадания
		buffer.writeInt(mop.typeOfHit.ordinal());

		// Запись координат блока
		buffer.writeInt(mop.blockX);
		buffer.writeInt(mop.blockY);
		buffer.writeInt(mop.blockZ);

		// Запись стороны попадания
		buffer.writeInt(mop.sideHit);

		// Запись вектора попадания
		writeVec3ToByteBuf(mop.hitVec, buffer);

		// Запись дополнительных данных, если они есть
		if (mop.hitInfo != null) {
			buffer.writeBoolean(true);  // Указываем, что дополнительная информация присутствует
			// Здесь нужно добавить код для записи hitInfo в зависимости от его типа
			// Например:
			// if (mop.hitInfo instanceof SomeType) {
			//     buffer.writeSomeType((SomeType) mop.hitInfo);
			// }
		} else {
			buffer.writeBoolean(false);  // Указываем, что дополнительной информации нет
		}
	}

	private static void writeVec3ToByteBuf(Vec3 vec, ByteBuf buffer) {
		if (vec != null) {
			buffer.writeFloat((float) vec.xCoord);
			buffer.writeFloat((float) vec.yCoord);
			buffer.writeFloat((float) vec.zCoord);
		} else {
			// Если вектор отсутствует, пишем нули
			buffer.writeFloat(0.0f);
			buffer.writeFloat(0.0f);
			buffer.writeFloat(0.0f);
		}
	}

	public static MovingObjectPosition readMOPFromByteBuf(ByteBuf buffer) {
		// Чтение типа попадания
		MovingObjectPosition.MovingObjectType type = MovingObjectPosition.MovingObjectType.values()[buffer.readInt()];

		// Чтение координат блока
		int blockX = buffer.readInt();
		int blockY = buffer.readInt();
		int blockZ = buffer.readInt();

		// Чтение стороны попадания
		int sideHit = buffer.readInt();

		// Чтение вектора попадания
		Vec3 hitVec = readVec3FromByteBuf(buffer);

		// Чтение дополнительных данных
		Object hitInfo = null;
		if (buffer.readBoolean()) {
			// Здесь нужно добавить код для чтения hitInfo в зависимости от его типа
			// Например:
			// if (someCondition) {
			//     hitInfo = buffer.readSomeType();
			// }
		}

		return new MovingObjectPosition(blockX, blockY, blockZ, sideHit, hitVec);
	}

	private static Vec3 readVec3FromByteBuf(ByteBuf buffer) {
		double x = buffer.readFloat();
		double y = buffer.readFloat();
		double z = buffer.readFloat();
		return Vec3.createVectorHelper(x, y, z);
	}
	
}
