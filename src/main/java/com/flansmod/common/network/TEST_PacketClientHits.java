package com.flansmod.common.network;

import com.flansmod.client.debug.EntityDebugDot;
import com.flansmod.common.PenetrableBlock;
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.PenetrationLoss;
import com.flansmod.common.guns.raytracing.*;
import com.flansmod.common.sync.ShotData;
import com.flansmod.common.vector.Vector3f;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.flansmod.client.gui.GuiTeamSelect;
import com.flansmod.common.FlansMod;
import com.flansmod.common.teams.PlayerClass;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class TEST_PacketClientHits extends PacketBase {
    public ArrayList<ShotData> shotData = new ArrayList<>();
    public Random rand = new Random();

    public TEST_PacketClientHits() {
    }

    public TEST_PacketClientHits(ArrayList<ShotData> shotData) {
        this.shotData = shotData;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeInt(shotData.size());
        for (ShotData sd : shotData) {
//            if (sd.hit instanceof DriveableHit) {
//                DriveableHit phit = (DriveableHit) sd.hit;
//
//                data.writeInt(phit.hitbox.player.dimension);
//                data.writeInt(phit.hitbox.player.getEntityId());
//                data.writeInt(sd.bullet.getEntityId());
//                data.writeFloat(phit.intersectTime);
//
//                phit.hitbox.axes.getMatrix().writeToByteBuf(data);
//
//                data.writeFloat(phit.hitbox.axes.getYaw());
//                data.writeFloat(phit.hitbox.axes.getPitch());
//                data.writeFloat(phit.hitbox.axes.getRoll());
//
//                data.writeFloat(phit.hitbox.rP.x);
//                data.writeFloat(phit.hitbox.rP.y);
//                data.writeFloat(phit.hitbox.rP.z);
//
//                data.writeFloat(phit.hitbox.o.x);
//                data.writeFloat(phit.hitbox.o.y);
//                data.writeFloat(phit.hitbox.o.z);
//
//                data.writeFloat(phit.hitbox.d.x);
//                data.writeFloat(phit.hitbox.d.y);
//                data.writeFloat(phit.hitbox.d.z);
//
//                data.writeFloat(phit.hitbox.vel.x);
//                data.writeFloat(phit.hitbox.vel.y);
//                data.writeFloat(phit.hitbox.vel.z);
//
//                data.writeInt(phit.hitbox.type.ordinal());
//            }
//
//            else
            if (sd.hit instanceof PlayerBulletHit) {
                PlayerBulletHit phit = (PlayerBulletHit) sd.hit;

                /** Writing type of hit
                 * 1 - DriveableHit
                 * 2 - PlayerBulletHit
                 * 3 - EntityHit
                 * 4 - BlockHit
                 */
                data.writeByte(2);

                data.writeInt(sd.bullet.dimension);
                data.writeInt(phit.hitbox.player.getEntityId());
                data.writeInt(sd.bullet.getEntityId());
                data.writeFloat(phit.intersectTime);

                phit.hitbox.axes.getMatrix().writeToByteBuf(data);

                data.writeFloat(phit.hitbox.axes.getYaw());
                data.writeFloat(phit.hitbox.axes.getPitch());
                data.writeFloat(phit.hitbox.axes.getRoll());

                data.writeFloat(phit.hitbox.rP.x);
                data.writeFloat(phit.hitbox.rP.y);
                data.writeFloat(phit.hitbox.rP.z);

                data.writeFloat(phit.hitbox.o.x);
                data.writeFloat(phit.hitbox.o.y);
                data.writeFloat(phit.hitbox.o.z);

                data.writeFloat(phit.hitbox.d.x);
                data.writeFloat(phit.hitbox.d.y);
                data.writeFloat(phit.hitbox.d.z);

                data.writeFloat(phit.hitbox.vel.x);
                data.writeFloat(phit.hitbox.vel.y);
                data.writeFloat(phit.hitbox.vel.z);

                data.writeInt(phit.hitbox.type.ordinal());
            } else if (sd.hit instanceof EntityHit) {
                data.writeByte(3);

                EntityHit ehit = (EntityHit) sd.hit;
                data.writeInt(sd.bullet.dimension);
                data.writeInt(ehit.entity.getEntityId());
                data.writeInt(sd.bullet.getEntityId());
                data.writeFloat(ehit.intersectTime);
            } else if (sd.hit instanceof BlockHit) {
                data.writeByte(4);

                BlockHit bhit = (BlockHit) sd.hit;
                data.writeInt(sd.bullet.dimension);
                data.writeInt(sd.bullet.getEntityId());
                BlockHit.writeMOPToByteBuf(bhit.raytraceResult, data);
                data.writeFloat(bhit.intersectTime);
            }

        }


    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {


        System.out.println("SERVER RECIVED HITS PACKET - " + shotData.size());
        // Чтение количества объектов hits
        int hitsSize = data.readInt();

        // Итерация по каждому объекту
        for (int i = 0; i < hitsSize; i++) {
            byte typeOfHit = data.readByte();
            if (typeOfHit == 1) {
                System.out.println("driveable hit recived");
            } else if (typeOfHit == 2) {
                int dim = data.readInt();
                int playerID = data.readInt();
                int bulletID = data.readInt();
                float phitIntersectTime = data.readFloat();

                RotatedAxes axes = new RotatedAxes();
                axes.getMatrix().readFromByteBuf(data);
                axes.setAngles(data.readFloat(), data.readFloat(), data.readFloat());

                Vector3f rp = new Vector3f(data.readFloat(), data.readFloat(), data.readFloat());
                Vector3f o = new Vector3f(data.readFloat(), data.readFloat(), data.readFloat());
                Vector3f d = new Vector3f(data.readFloat(), data.readFloat(), data.readFloat());
                Vector3f vel = new Vector3f(data.readFloat(), data.readFloat(), data.readFloat());

                EnumHitboxType type = EnumHitboxType.values()[data.readInt()];

                World world = MinecraftServer.getServer().worldServerForDimension(dim);
                EntityPlayerMP player = (EntityPlayerMP) world.getEntityByID(playerID);
                EntityBullet bullet = (EntityBullet) world.getEntityByID(bulletID);

                PlayerHitbox pbox = new PlayerHitbox(player, axes, rp, o, d, vel, type);
                PlayerBulletHit phit = new PlayerBulletHit(pbox, phitIntersectTime);

                shotData.add(new ShotData(phit, bullet));
            } else if (typeOfHit == 3) {
                int dim = data.readInt();
                int entityID = data.readInt();
                int bulletID = data.readInt();
                System.out.println("BULLET ID = " + bulletID);
                float intersectTime = data.readFloat();
                World world = MinecraftServer.getServer().worldServerForDimension(dim);
                Entity entity = world.getEntityByID(entityID);
                EntityBullet bullet = (EntityBullet) world.getEntityByID(bulletID);
                EntityHit ehit = new EntityHit(entity, intersectTime);
                shotData.add(new ShotData(ehit, bullet));
            } else if (typeOfHit == 4) {

                int dim = data.readInt();
                int bulletID = data.readInt();
                MovingObjectPosition mop =
                        BlockHit.readMOPFromByteBuf(data);
                float intersectTime = data.readFloat();
                World world = MinecraftServer.getServer().worldServerForDimension(dim);
                EntityBullet bullet = (EntityBullet) world.getEntityByID(bulletID);
                BlockHit bhit = new BlockHit(mop, intersectTime);
                shotData.add(new ShotData(bhit, bullet));
            }
        }
        System.out.println("SERVER READ HITS PACKET - " + shotData.size());
    }

    // Hitmarker information on the server side.
    public boolean lastHitHeadshot = false;
    public float lastHitPenAmount = 1F;

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        boolean showCrosshair = false;
        lastHitPenAmount = 0F;
        lastHitHeadshot = false;
        for (ShotData sd : shotData) {
            if (sd.hit instanceof PlayerBulletHit) {
                if (sd.bullet == null) {
                    return;
                }
                PlayerBulletHit phit = (PlayerBulletHit) sd.hit;
//                phit.hitbox.hitByBullet(sd.bullet,1);

                float prevPenetratingPower = sd.bullet.penetratingPower;
                sd.bullet.penetratingPower = phit.hitbox.hitByBullet(sd.bullet, sd.bullet.penetratingPower);
                sd.bullet.penetrationLosses.add(new PenetrationLoss((prevPenetratingPower - sd.bullet.penetratingPower), PenetrationLoss.PenetrationLossType.PLAYER));
                if (FlansMod.DEBUG)
                    playerEntity.worldObj.spawnEntityInWorld(new EntityDebugDot(playerEntity.worldObj, new Vector3f(sd.bullet.posX + sd.bullet.motionX * phit.intersectTime, sd.bullet.posY + sd.bullet.motionY * phit.intersectTime, sd.bullet.posZ + sd.bullet.motionZ * phit.intersectTime), 1000, 1F, 0F, 0F));
            } else if (sd.hit instanceof EntityHit) {

                if (sd.bullet == null) {
                    return;
                }

                if (sd.bullet.type.entityHitSoundEnable)
                    PacketPlaySound.sendSoundPacket(sd.bullet.posX, sd.bullet.posY, sd.bullet.posZ, sd.bullet.type.hitSoundRange, sd.bullet.dimension, sd.bullet.type.hitSound, true);

                if (!sd.bullet.worldObj.isRemote) {
                    if (sd.bullet.owner instanceof EntityPlayer) {
                        showCrosshair = true;
                        lastHitPenAmount = 1F;
                    }
                }

                EntityHit entityHit = (EntityHit) sd.hit;
                float d = sd.bullet.getDamageAffectedByPenetration();

                if (entityHit.entity instanceof EntityLivingBase) {
                    d *= sd.bullet.type.damageVsLiving;
                    if (entityHit.entity != sd.bullet.owner)
                        FlansMod.proxy.spawnParticle("reddust", entityHit.entity.posX, entityHit.entity.posY, entityHit.entity.posZ, 0, 0, 0);

                } else {
                    d *= sd.bullet.type.damageVsEntity;
                }

                if (entityHit.entity.attackEntityFrom(sd.bullet.getBulletDamage(false), d) && entityHit.entity instanceof EntityLivingBase) {
                    EntityLivingBase living = (EntityLivingBase) entityHit.entity;
                    for (PotionEffect effect : sd.bullet.type.hitEffects) {
                        living.addPotionEffect(new PotionEffect(effect));
                    }
                    //If the attack was allowed, we should remove their immortality cooldown so we can shoot them again. Without this, any rapid fire gun become useless
                    living.arrowHitTimer++;
                    living.hurtResistantTime = living.maxHurtResistantTime / 2;
                }
                if (sd.bullet.type.setEntitiesOnFire)
                    entityHit.entity.setFire(20);
                sd.bullet.penetratingPower -= 1F;

                sd.bullet.penetrationLosses.add(new PenetrationLoss(1F, PenetrationLoss.PenetrationLossType.ENTITY));

                if (FlansMod.DEBUG) {
                    sd.bullet.worldObj.spawnEntityInWorld(new EntityDebugDot(sd.bullet.worldObj, new Vector3f(sd.bullet.posX + sd.bullet.motionX * entityHit.intersectTime, sd.bullet.posY + sd.bullet.motionY * entityHit.intersectTime, sd.bullet.posZ + sd.bullet.motionZ * entityHit.intersectTime), 1000, 1F, 1F, 0F));
                    FlansMod.log(entityHit.entity.toString() + ": d=" + d + ": damage=" + sd.bullet.damage + ": type.damageVsEntity=" + sd.bullet.type.damageVsEntity);
                }
            } else if (sd.hit instanceof BlockHit) {

                if (sd.bullet == null) {
                    return;
                }

                BlockHit blockHit = (BlockHit) sd.hit;
                MovingObjectPosition raytraceResult = blockHit.raytraceResult;
                Vec3 hitVec = raytraceResult.hitVec;

                //If the hit wasn't an entity hit, then it must've been a block hit
                int xTile = raytraceResult.blockX;
                int yTile = raytraceResult.blockY;
                int zTile = raytraceResult.blockZ;


                if (FlansMod.DEBUG)
                    sd.bullet.worldObj.spawnEntityInWorld(new EntityDebugDot(sd.bullet.worldObj, new Vector3f(hitVec.xCoord, hitVec.yCoord, hitVec.zCoord), 1000, 0F, 1F, 0F));

                Block block = sd.bullet.worldObj.getBlock(xTile, yTile, zTile);
                Material mat = block.getMaterial();

                if (FlansMod.enableBlockPenetration) {
                    boolean penetrableBlockFound = false;

                    for (PenetrableBlock penetrableBlock : FlansMod.penetrableBlocks) {
                        if (block != penetrableBlock.getBlock()) continue;

                        int metadata = penetrableBlock.getMetadata();
                        if (metadata != -1 && metadata != sd.bullet.worldObj.getBlockMetadata(xTile, yTile, zTile))
                            continue;

                        float hardness = penetrableBlock.getHardness() * (sd.bullet.type.getBlockPenetrationModifier() > 0 ? (1F / sd.bullet.type.getBlockPenetrationModifier()) : 1F);

                        sd.bullet.penetratingPower -= hardness;
                        if (sd.bullet.penetratingPower < 0) break;

                        FlansMod.proxy.playBlockBreakSound(xTile, yTile, zTile, block, sd.bullet.dimension);
                        if (penetrableBlock.breaks()) sd.bullet.worldObj.setBlockToAir(xTile, yTile, zTile);

                        sd.bullet.penetrationLosses.add(new PenetrationLoss(hardness, PenetrationLoss.PenetrationLossType.BLOCK));

                        penetrableBlockFound = true;
                    }
                    //The block was penetrated, so the bullet can keep going
                    if (penetrableBlockFound) continue;
                }

                if (sd.bullet.type.hitSoundEnable)
                    //If the bullet breaks glass, and can do so according to FlansMod, do so.
                    if (sd.bullet.type.breaksGlass && mat == Material.glass) {
                        if (TeamsManager.canBreakGlass) {
                            sd.bullet.worldObj.setBlockToAir(xTile, yTile, zTile);
                            FlansMod.proxy.playBlockBreakSound(xTile, yTile, zTile, block, sd.bullet.dimension);
                        }
                    }
                if (sd.bullet.type.hitSoundEnable) {
                    String hitToUse = null;
                    if (sd.bullet.type.hitSound != null) {
                        hitToUse = sd.bullet.type.hitSound;
                    } else if (block.equals(Blocks.brick_block)) {
                        hitToUse = "impact_bricks";
                        //worldObj.playSoundEffect(posX, posY, posZ, FlansModResourceHandler.getSound("impact_bricks").toString(), 0.5F, 1);
                    } else if (mat == Material.ground || mat == Material.grass || mat == Material.sand || mat == Material.clay || mat == Material.tnt) {
                        hitToUse = "impact_dirt";
                        //worldObj.playSoundEffect(posX, posY, posZ, FlansModResourceHandler.getSound("impact_dirt").toString(), 0.5F, 1);
                    } else if (mat == Material.glass || mat == Material.redstoneLight || mat == Material.ice || mat == Material.packedIce) {
                        hitToUse = "impact_glass";
                        //worldObj.playSoundEffect(posX, posY, posZ, FlansModResourceHandler.getSound("impact_glass").toString(), 0.5F, 1);
                    } else if (mat == Material.iron || mat == Material.anvil) {
                        hitToUse = "impact_metal";
                        //worldObj.playSoundEffect(posX, posY, posZ, FlansModResourceHandler.getSound("impact_metal").toString(), 0.5F, 1);
                    } else if (mat == Material.rock) {
                        hitToUse = "impact_rock";
                        //worldObj.playSoundEffect(posX, posY, posZ, FlansModResourceHandler.getSound("impact_rock").toString(), 0.5F, 1);
                    } else if (mat == Material.wood) {
                        hitToUse = "impact_wood";
                        //worldObj.playSoundEffect(posX, posY, posZ, FlansModResourceHandler.getSound("impact_wood").toString(), 0.5F, 1);
                    }
                    PacketPlaySound.sendSoundPacket(sd.bullet.posX, sd.bullet.posY, sd.bullet.posZ, sd.bullet.type.hitSoundRange, sd.bullet.dimension, hitToUse, true);
                }


                if (sd.bullet.worldObj.isRemote) {
                    if (block.getMaterial() != Material.air && sd.bullet.type.explosionRadius <= 30 && sd.bullet.type.blockHitFXScale > 0) {
                        // Calculate the number of block particles proportionally to explosionRadius
                        double scalingFactor = Minecraft.getMinecraft().gameSettings.fancyGraphics ? 10 : 2;
                        ; // Adjust this value based on your desired particle density
                        int numBlockParticles = (int) (Math.pow((sd.bullet.type.explosionRadius + 1), 1.5) * scalingFactor + 20);

                        double velocityFactor = Math.sqrt(sd.bullet.type.explosionRadius + 1) * sd.bullet.type.blockHitFXScale * 0.5;

                        for (int i = 0; i < numBlockParticles; i++) {
                            // First particle
                            FlansMod.proxy.spawnParticle(
                                    "blockdust_" + Block.getIdFromBlock(block) + "_" + sd.bullet.worldObj.getBlockMetadata(xTile, xTile, xTile),
                                    raytraceResult.hitVec.xCoord + ((double) this.rand.nextFloat() - 0.3D) * (double) sd.bullet.width * 0.05D,
                                    raytraceResult.hitVec.yCoord + ((double) this.rand.nextFloat() - 0.3D) * (double) sd.bullet.width * 0.05D,
                                    raytraceResult.hitVec.zCoord + ((double) this.rand.nextFloat() - 0.3D) * (double) sd.bullet.width * 0.05D,
                                    -sd.bullet.motionX * (0.0011D + this.rand.nextGaussian() * 0.008D) * velocityFactor, // Adjusted horizontal velocity
                                    Math.abs(0.305D + this.rand.nextDouble() * 0.125D) * velocityFactor, // Adjusted vertical velocity
                                    -sd.bullet.motionZ * (0.0011D + this.rand.nextGaussian() * 0.008D) * velocityFactor // Adjusted horizontal velocity
                            );

                            // Second particle
                            FlansMod.proxy.spawnParticle(
                                    "blockcrack_" + Block.getIdFromBlock(block) + "_" + sd.bullet.worldObj.getBlockMetadata(xTile, xTile, xTile),
                                    raytraceResult.hitVec.xCoord + ((double) this.rand.nextFloat() - 0.6D) * (double) sd.bullet.width * 0.75D,
                                    raytraceResult.hitVec.yCoord + ((double) this.rand.nextFloat() - 0.6D) * (double) sd.bullet.width * 0.75D,
                                    raytraceResult.hitVec.zCoord + ((double) this.rand.nextFloat() - 0.6D) * (double) sd.bullet.width * 0.75D,
                                    -sd.bullet.motionX * (0.415D + this.rand.nextGaussian() * 0.1D) * velocityFactor, // Adjusted horizontal velocity
                                    -sd.bullet.motionY * (0.425D + Math.abs(this.rand.nextGaussian() * 0.1D)) * velocityFactor, // Adjusted vertical velocity
                                    -sd.bullet.motionZ * (0.415D + this.rand.nextGaussian() * 0.1D) * velocityFactor // Adjusted horizontal velocity
                            );

                        }
                    }
                }

                if (sd.bullet.type.bounciness > 0) {
                    Vector3f origin = new Vector3f(sd.bullet.posX, sd.bullet.posY, sd.bullet.posZ);
                    Vector3f motion = new Vector3f(sd.bullet.motionX, sd.bullet.motionY, sd.bullet.motionZ);
                    Vector3f hitPos = new Vector3f(hitVec);
                    Vector3f preHitVel = Vector3f.sub(hitPos, origin, null);
                    Vector3f postHitVel = Vector3f.sub(motion, preHitVel, null);

                    Vector3f surfaceNormal;

                    int sideHit = blockHit.raytraceResult.sideHit;
                    switch (sideHit) {
                        case 0:
                            surfaceNormal = new Vector3f(0, -1, 0);
                            break;
                        case 1:
                            surfaceNormal = new Vector3f(0, 1, 0);
                            break;
                        case 2:
                            surfaceNormal = new Vector3f(0, 0, -1);
                            break;
                        case 3:
                            surfaceNormal = new Vector3f(0, 0, 1);
                            break;
                        case 5:
                            surfaceNormal = new Vector3f(1, 0, 0);
                            break;
                        case 4:
                        default:
                            surfaceNormal = new Vector3f(-1, 0, 0);
                            break;
                    }

                    if (motion.lengthSquared() < 0.1F * sd.bullet.initialSpeed) {
                        sd.bullet.setPosition(hitVec.xCoord, hitVec.yCoord, hitVec.zCoord);
                        sd.bullet.setDead();
                    } else {
                        float lambda = postHitVel.length() / motion.length();

                        float normalProjection = Vector3f.dot(surfaceNormal, postHitVel);
                        Vector3f normal = (Vector3f) (new Vector3f(surfaceNormal)).scale(-normalProjection); // massively scale down the normal collision

                        Vector3f orthog = Vector3f.add(postHitVel, normal, null);

                        normal.scale(sd.bullet.type.bounciness / 3);
                        orthog.scale(sd.bullet.type.bounciness);

                        postHitVel = Vector3f.add(orthog, normal, null);

                        Vector3f totalVel = Vector3f.add(preHitVel, postHitVel, null);

                        sd.bullet.setPosition(sd.bullet.posX + totalVel.x,
                                sd.bullet.posY + totalVel.y,
                                sd.bullet.posZ + totalVel.z);
                        sd.bullet.setVelocity(postHitVel.x / lambda, postHitVel.y / lambda, postHitVel.z / lambda);
                    }
                } else {
                    sd.bullet.setPosition(hitVec.xCoord, hitVec.yCoord, hitVec.zCoord);
                    sd.bullet.setDead();
                }
                break;
            }
            if (sd.bullet.penetratingPower <= 0F || (sd.bullet.type.explodeOnImpact && sd.bullet.ticksInAir > 1)) {
                sd.bullet.setPosition(sd.bullet.posX + sd.bullet.motionX * sd.hit.intersectTime, sd.bullet.posY + sd.bullet.motionY * sd.hit.intersectTime, sd.bullet.posZ + sd.bullet.motionZ * sd.hit.intersectTime);
                sd.bullet.setDead();
                break;
            }
            if (showCrosshair && sd.bullet.owner instanceof EntityPlayerMP) {
                FlansMod.getPacketHandler().sendTo(new PacketHitMarker(lastHitHeadshot, lastHitPenAmount, false), (EntityPlayerMP) sd.bullet.owner);
            }
        }
    }

    /**
     * Handle a request from the server to display a team / class selection window
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void handleClientSide(EntityPlayer clientPlayer) {
        FlansMod.log("Skip on client");
    }
}
