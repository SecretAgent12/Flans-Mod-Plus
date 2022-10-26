# Config reference
For each type, the table will list each config and potentially its' purpose and default values.

### [InfoType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/types/InfoType.java)
| Keyword | Type | Default | Purpose |
|----------------|------|----------------------------|----------------------------------------------------------------|
| Model | String | ~ | The name of the model file |
| ModelScale | Float | 1 | The scale to transform |
| Name | String | ~ | The name of the model (Names are set in the lang file, not here) |
| Description | String | ~ | The item description |
| ShortName | String | ~ | The item's shortname |
| Colour / Color | Int Int Int | 255 255 255 | The colour of the item (Not sure where this is actually used?) |
| Icon | String | ~ | The texture path of the item icon |
| RecipeOutput | Integer | 1 | The number of items produced when crafted |
| Recipe | RecipeFormat (See line 20) | ~ | Recipe for the item |
| ShapelessRecipe | List of recipe items? | ~ | Shapeless recipe for the item |
| SmeltableFrom | String | ~ | The shortname of the item that it may be smelted from |
| CanDrop | Bool | true | Whether the item can be.. dropped? |

Recipe format: 
```
A ItemTypeOne B ItemTypeTwo
ABA
BAB
   
```

### [PaintableType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/paintjob/PaintableType.java) extends [InfoType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/types/InfoType.java)
| Keyword | Type | Default | Purpose |
|-------------|------------------------------------------------------------------|---------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Paintjob | iconName textureName [dyeName dyeAmount (dyeDamage)] | ~ | Sets up a new paintjob with the dyes required dyeDamage is optional and I'm not entirely sure how that works. The dye setup in [] can be repeated. (you don't need to add the []) |
| AdvPaintjob | displayName iconName textureName [dyeName dyeAmount (dyeDamage)] | ~ | Creates an advanced paintjob |
| AddPaintableToTables | bool | true | Whether any (other than default) skins should be added to the gun mod table/paintable table. |
| AddPaintableToTables | textureName true/false | true | Whether an individual texture should be added to the tables. |
### [GunType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/guns/GunType.java) extends [PaintableType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/paintjob/PaintableType.java)
| Keyword | Type | Default | Purpose |
|-----------------------------|------------|----------------|-------------------------------------------------------------------------------------------|
| Damage | Float | 0 | Multiplies the damage of the bullet |
| MeleeDamage | Float | 1 | The damage caused by punching with the gun |
| MeleeDamageDriveableModifier | Float | 1 | Multiplier for melee damage when the entity is a DriveableEntity. |
| CounterRecoilForce | Float | 0.8 | Constant to determine how quickly the gun returns to centre after firing. Higher = longer |
| CounterRecoilForceSneaking | Float | 0.7 | ~ |
| CounterRecoilForceSprinting | Float | 0.9 | ~ |
| HipFireWhileSprinting | Boolean | true (can be changed in flansmod.cfg) | Whether the gun can fire when a player is sprinting and in hip position |
| CanForceReload | Bool | true | Whether R can be used to reload the gun |
| AllowRearm | Bool | true | Ammo bags give ammo when right clicked with the gun |
| ReloadTime | Integer | ~ | Ticks taken to reload the gun |
| Recoil | Float | 0 | Upwards view recoil |
| FancyRecoil | String | ~ | Fancy Recoil System, when using this parameter, the rest of the recoil parameters will be ignored. there is no network synchronization with the server. template to use - FancyRecoil 1.9 horizontal=2 recovery=1 recoveryScope=1 fall=1.1 increase=1.1 sneak=0.9 speed=1.1 |
| RecoilYaw | Float | 0 | Sideways view recoil |
| RandomRecoilRange | Float | 0.5 | Maximum divergence of the recoil vertically |
| RandomRecoilYawRange | Float | 0.5 | ~ Horizontally |
| DecreaseRecoil | Float | 0 | DEPRECATED DO NOT USE! Amount to subtract from recoil when sneaking. WILL result in negative recoil. |
| DecreaseRecoilYaw | Float | 0 | DEPRECATED DO NOT USE! Divisor for yaw recoil when sneaking. |
| RecoilSneakingMultiplier | Float | Backwards Compatibility (DecreaseRecoil 0.5) | Multiplies recoil when sneaking. 1 = no change |
| RecoilSprintingMultiplier | Float | 1 | Multiplies recoil when sprinting. 1 = no change |
| RecoilSneakingMultiplierYaw | Float | 0.8 | Multiplies horizontal recoil when sneaking. 1 = no change |
| RecoilSprintingMultiplierYaw | Float | 1.2 | Multiplies horizontal recoil when sprinting. 1 = no change |
| Knockback | Float | 0 | Amount of knockback to push the player when shot |
| Accuracy / Spread | Float | 0 | Amount that the bullets spread out. Higher = wider cone |
| SprintSpreadMultiplier | Float | 1.75 | Multiplier for spread applied when player is sprinting. |
| SneakSpreadMultiplier | Float | 0.63 | Multiplier for spread applied when player is sneaking. |
| ADSSpreadModifier | Float | -1 | Modifier for spread when ADS. -1 means use the FlansMod.cfg default (0.2). Anything other overrides it. |
| ADSSpreadModifierShotgun | Float | -1 | Modifier for spread when ADS. -1 means use the FlansMod.cfg default (0.2). Anything other overrides it. For shotguns/multibullet guns. |
| NumBullets | Integer | 1 | The number of bullets created by each shot |
| AllowNumBulletsByBulletType | Bool | false | The NumBullets of the ammo overrides the guns NumBullets. |
| AllowSpreadByBullet | Bool | false | The Spread of the ammo overrides the guns Spread. |
| CanLockAngle | Integer | 5 | The angle between entity and player view that a launcher can lock on with |
| LockOnSoundTime | Integer | 0 |  |
| LockOnToDriveables | Bool |  | Whether the launcher can lock on to planes, vehicles, mechas |
| LockOnToVehicles | Bool | false | ~ |
| LockOnToPlanes | Bool | false |  |
| LockOnToMechas | Bool | false |  |
| LockOnToPlayers | Bool | false |  |
| LockOnToLivings | Bool | false |  |
| ConsumeGunOnUse | Bool | false | Gun deletes when shot. (E.g. Panzerfaust) |
| ShowCrosshair | Bool | false | Show a crosshair when holding the gun |
| DropItemOnShoot | Bool | false | Drop the item when gun has been shot onto the floor |
| NumBurstRounds | Integer | 3 | Number of bullets to fire per burst in burst fire mode |
| MinigunStartSpeed | Float | 15 | Required speed of revolution for minigun to start firing |
| ItemUseAction | EnumAction | EnumAction.bow | The type of action MC thinks you're doing. |
| MaxRangeLockOn | Integer | 80 | Max range in... blocks? That lockon will work. |
| ShootDelay | Float | 0 | Ticks between shots (Supposedly outdated?) |
| RoundsPerMin | Float | 0 | Fire rate of the gun in rounds per minute. 1200 is the max (60 seconds*20 ticks). |
| SoundLength | Integer | ~ | Length of the shoot sound for looping |
| DistortSound | Bool | true | Whether to distort the sound played |
| IdleSoundRange | Integer | 50 | Block range that players can hear the idle sound (minigun spin) |
| MeleeSoundRange | Integer | 50 | ~ |
| ReloadSoundRange | Integer | 50 | ~ |
| GunSoundRange | Integer | 50 | ~ |
| ShootSound | String | ~ |  |
| BulletInsertSound | String | ~ | Only works for guns with an animation type of SHOTGUN, STRIKER, CUSTOMSHOTGUN, CUSTOMSTRIKER, CUSTOMRIFLE |
| ActionSound | String | ~ |  |
| LastShootSound | String | ~ |  |
| SuppressedShootSound | String | ~ |  |
| ReloadSound | String | ~ |  |
| EmptyReloadSound | String | ~ |  |
| EmptyClickSound | String | ~ | Plays the first time the player tries to shoot with no ammo. |
| EmptyClickSoundRepeated | String | ~ | Plays when the player tries to shoot with no ammo (after first time if click if defined) |
| IdleSound | String | ~ |  |
| IdleSoundLength | String | ~ |  |
| MeleeSound | String | ~ |  |
| WarmupSound | String | ~ |  |
| WarmupSoundLength | String | ~ |  |
| LoopedSound / SpinSound | String | ~ |  |
| LoopedSoundLength / SpinSoundLength | String | ~ |  |
| CooldownSound | String | ~ |  |
| LockOnSound | String | ~ |  |
| DistantSound | String | ~ | Sound to use for players outside of GunSoundRange but within DistantSoundRange |
| DistantSoundRange | Int | 100 | Range to play distant shooting sound around the gun. |
| Mode | String | FULLAUTO | The mode of the gun. (fullauto, minigun, burst, semiauto) |
| Scope | String | ~ | None = NoScope, otherwise scopeTexture |
| AllowNightVision | Bool | false | Gives night vision effect when scoped |
| ZoomLevel | Float | 1 | Zoom level of the default scope. It is NOT possible to zoom in *and* have the gun visible, due to a limitation of forge. |
| FOVZoomLevel | Float | 1.5 | The FOV zoom level of the default scope. Keeps the gun visible (if animated properly). |
| HasVariableZoom | Boolean | false | Is it possible to adjust the zoom. P.s. The ZoomLevel and FOVZoomLevel values will not affect the zoom. |
| MinZoom | Float | 1.00 | Minimum zoom level. |
| MaxZoom | Float | 4.00 | Maximum zoom level. |
| ZoomAugment | Float | 1.00 | Zoom in/out augment. ATTENTION! The result of division (÷) MaxZoom by ZoomAugment must be an integer. (Correct example: 8/2=4. NOT correct example: 8/3=2.66...). |
| Deployable | Bool | false | Whether the gun is placeable |
| DeployedModel | String | ~ | The model name of the deployed model |
| CasingModel | String | ~ | The model name of the casings to eject |
| FlashModel | String | ~ | The model name of the muzzle flash |
| CasingTexture | String | ~ | The texture name of the casings to eject |
| FlashTexture | String | ~ | The texture name of the muzzle flash |
| MuzzleFlashParticle | String | flansmod.muzzleflash | Particle to use for muzzle flash effect. |
| MuzzleFlashParticleSize | Float | 1 | Scaling for size of muzzle flash particle. |
| MuzzleFlashParticleShoulderOffset | Vector3F | [0, 0, 0] | Offset relative to the players body. Used to calculate hand position. Uses format [x,y,z] where x, y and z can be floats. |
| MuzzleFlashParticleHandOffset | Vector3F | [0, 0, 0] | Offset relative to the players right arm. Used to calculate gun position. Uses format [x,y,z] where x, y and z can be floats. |
| ShowMuzzleFlashParticle | Boolean | true | Whether to show muzzle flash particles - overrides default set in flansmod.cfg |
| ShowMuzzleFlashParticleFirstPerson | Boolean | false | Whether to show muzzle flash particles to the shooter by default. |
| ModelScale | Float | 1 | The scale to transform by |
| Texture | String | ~ | The name of the texture file to use |
| HitTexture | String | ~ | The name of the hit texture file to use. Will look in the skins folder. |
| DeployedTexture | String | ~ | The name of the deployed texture file to use |
| StandBackDistance | Float | 1.5 | How far behind the player should be behind a deployed gun |
| TopViewLimit | Float | -60 | How far the players view should be limited upwards (Deployed) |
| BottomViewLimit | Float | 30 | How far the players view should be limited downwards (Deployed) |
| SideViewLimit | Float | 45 | How far the players view should be limited sideways (Deployed) |
| PivotHeight | Float | 0.375 | Something to do with deployables |
| Ammo | String | ~ | Add to the list of types of ammo that the gun will take |
| NumAmmoSlots / NumAmmoItemsInGun / LoadIntoGun | Integer | 1 | The number of bullets to take into the gun when loading |
| BulletSpeed | Float | 5 | Speed of the bullet when leaving the gun |
| CanShootUnderwater | Bool | true | Whether the gun may be used underwater |
| CanSetPosition | Bool | false | Whether the gun will move with the target when locked on |
| OneHanded | Bool | false | Whether the gun may be dual wielded |
| SecondaryFunction | EnumSecondaryFunction | ADS_ZOOM | The function of left click (Zoom, melee, custommelee, ads_zoom) |
| UsableByPlayers | Bool | true | Whether the gun may be used by players |
| UsableByMechas | Bool | true | Whether the gun may be used by mechas |
| UseCustomMelee | Bool | false ( kind of ) | Whether to use the custom melee function |
| UseCustomMeleeWhenShot | Bool | false ( kind of ) | Whether to use the custom melee function when shot |
| MeleeTime | Integer | 1 | Time delay between custom melee attacks |
| AddNode | 2x Vec3 ( x y z ) | ~ | Add a node to the animation track for melee/other |
| MeleeDamagePoint / MeleeDamageOffset | Vec3 ( x y z ) | ~ | Add a point where damage should be dealt |
| MoveSpeedModifier / Slowness | Float | 1 | Modifier to player movement speed |
| KnockbackModifier / KnockbackReduction | Float | 0 | Modifier to player knockback |
| AllowAllAttachments | Bool | false | Allow all attachments to be used with the gun |
| AllowAttachments | List of attachments | ~ | Add attachments to gun |
| AllowBarrelAttachments | Bool | false | Allow x attachments to be used with this gun |
| AllowScopeAttachments | Bool | false | Allow x attachments to be used with this gun |
| AllowStockAttachments | Bool | false | Allow x attachments to be used with this gun |
| AllowGripAttachments | Bool | false | Allow x attachments to be used with this gun |
| AllowGadgetAttachments | Bool | false | Allow x attachments to be used with this gun |
| AllowSlideAttachments | Bool | false | Allow x attachments to be used with this gun |
| AllowPumpAttachments | Bool | false | Allow x attachments to be used with this gun |
| AllowAccessoryAttachments | Bool | false | Allow x attachments to be used with this gun |
| NumGenericAttachments | Integer | 0 | The number of generic attachments that can be used |
| Shield | damageAbsorption (origin) X Y Z (dimensions) X Y Z | false | Shield setup for riot shields |
| SwitchDelay | Integer | 0 | The time (in ticks) required to draw a weapon |


Special note for guns - all animation variables that you can change in the gun's model file can be changed from here as well. Simply:
`animVARIABLENAME` in this style `variableVariableNameA` for variableNameA. anim always lower case, first letters of any other word upper case. For vector3fs, 
`animSomeVariable 0 -4 4`
for animation types:
`animAnimationType BOTTOM_CLIP`

This will override the value set in the gun's model!

### [AAGunType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/guns/AAGunType.java) extends [InfoType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/types/InfoType.java)
| Keyword | Type | Default | Purpose |
|----------------------|----------|------|--------------------------------------------------|
| Model | String | ~ | Location of the model |
| Texture | String | ~ | Name of the texture file |
| ReloadTime | Integer | ~ | Time in ticks that it takes to reload |
| Recoil | Integer | 5 | The recoil |
| Accuracy | Integer | ~ | The accuracy of the gun |
| ShootDelay | Integer | ~ | Delay in ticks between shots |
| ShootSound | String | ~ | Sound to play on shooting |
| ReloadSound | String | ~ | Sound to play on reload |
| FireAlternatiely | Bool | ~ | Alternate firing between shootpoints ? |
| NumBarrels | Integer | ~ | The number of barrels the AAGun has |
| Barrel | ID X Y Z | ~ | Add a barrel with ID at the specified position |
| Health | Integer | ~ | The health of the AAGun |
| TopViewLimit | Float | 75 | The angle which the view is limited to at the top |
| BottomViewLimit | Float | 0 | The angle which the view is limited to at the bottom |
| Ammo | String | ~ | The shortname of the ammo to use |
| GunnerPos | X Y Z | ~ | The position of the gunner on the AAGun |
| TargetMobs | Bool | false | Sentry mode, fire at mobs |
| TargetPlayers | Bool | false | Sentry mode, fire at X |
| TargetVehicles | Bool | false | Sentry mode, fire at X |
| TargetPlanes | Bool | false | Sentry mode, fire at X |
| TargetMechas | Bool | false | Sentry mode, fire at X |
| TargetDriveables | Bool | false | Sentry mode, fire at Planes, Vehicles and Mechas |
| ShareAmmo | Bool | false | Whether all barrels should share the same ammo slot. |
| TargetRange | Float | 10 | Sentry mode, max distance that it will target |
| CanShootHomingMissile | Bool | false | Whether homing missiles may be shot from the AAGun |
| CountExplodeAfterShoot | Integer | -1 | How long after shot should the missile explode. -1 = never/when hit target |
| IsDropThis | Bool | true | Whether it drops item when destroyed? |

### [AttachmentType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/guns/AttachmentType.java) extends [PaintableType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/paintjob/PaintableType.java)
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| AttachmentType | String | EnumAttachmentType.generic | The type of attachment |
| Model | String | ~ | The model name of the model to use |
| ModelScale | Float | 1 | Scale to transform |
| Texture | String | ~ | The texture file to use |
| Silencer | Bool | false | Whether this attachment should muffle bullet sounds |
| DisableMuzzleFlash | Bool | false | Whether this attachment should remove any muzzle flash from rendering |
| FlashLight | Bool | false | Whether this attachment is a flashlight |
| FlashLightRange | Float | 10 | Number of blocks range of the flashlight |
| FlashLightStrength | Float | 12 | Light level produced (between 0 and 15) |
| ModeOverride | String | ~ | Override the fire mode of a gun with a valid EnumFireMode (fullauto, minigun, burst, semiauto) |
| SecondaryMode | Bool | false | Whether this attachment is an underbarrel attachment |
| SecondaryAmmo | String | ~ | Add an ammo type for the underbarrel gun |
| SecondaryDamage | Float | 1 | The damage multiplier of the underbarrel gun |
| SecondarySpread / Accuracy | Float | 1 | The inaccuracy of the underbarrel |
| SecondaryBulletSpeed | Float | 5 | Speed of the bullet when leaving the underbarrel |
| SecondaryShootDelay | Integer | 1 | Ticks to wait between firing the underbarrel |
| SecondaryReloadTime | Integer | 1 | Ticks taken to reload the underbarrel |
| SecondaryNumBullets | Integer | 1 | Number of bullets created by the underbarrel when fired |
| LoadSecondaryIntoGun | integer| 1 | Number of bullet stacks in magazine? (Has something to do with underbarrel) |
| SecondaryFireMode | String | SEMIAUTO | The EnumFireMode of the underbarrel (fullauto, minigun, burst, semiauto) |
| SecondaryShootSound | String | ~ | The sound to play when the underbarrel is shot |
| SecondaryReloadSound | String | ~ | ~ |
| ModeSwitchSound | String | ~ | The sound to play when toggling between primary and underbarrel gun |
| MeleeDamageMultiplier | Float | 1 | The multiplier to the X stat of the gun |
| MovementSpeedMultiplier / MoveSpeedModifier | Float | 1 | Modifier for player movement speed, stacks on gun's modifier |
| DamageMultiplier | Float | 1 | The multiplier to the X stat of the gun |
| SpreadMultiplier | Float | 1 | The multiplier to the X stat of the gun |
| RecoilMultiplier | Float | 1 | The multiplier to the X stat of the gun |
| RecoilControlMultiplier | Float | 1 | The multiplier to the X stat of the gun |
| RecoilControlMultiplierSneaking | Float | 1 | The multiplier to the X stat of the gun |
| RecoilControlMultiplierSprinting | Float | 1 | The multiplier to the X stat of the gun |
| BulletSpeedMultiplier | Float | 1 | The multiplier to the X stat of the gun |
| ReloadTimeMultiplier | Float | 1 | The multiplier to the X stat of the gun |
| MovementSpeedMultiplier | Float | 1 | The multiplier to the X stat of the gun |
| ZoomLevel | Float | 1 | The zoom of the scope |
| FOVZoomLevel | Float | 1 | The FOV zoom of the scope |
| ZoomOverlay | String | false/none | None = No overlay. Anything else will become the overlay image |
| HasNightVision | Bool | false | Whether to apply night vision |

### [ShootableType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/guns/ShootableType.java) extends [InfoType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/types/InfoType.java)
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| Model | String | ~ | Sets the model of the Shootable |
| Texture | String | ~ | Sets the texture of the Shootable |
| StackSize / NaxStackSize | Integer | 1 | The maximum number of X that can be stacked |
| DropItemOnShoot | String | null | Items dropped on various events |
| DropItemOnReload | String | null | Items dropped on various events |
| DropItemOnHit | String | null | Items dropped on various events |
| Bounciness | Float | (Grenade: 0.9), (Bullet 0) | When grenade or bullet hits an entity or block, the velocity will multiply by this amount |
| RoundsPerItem | Integer | 1 | The number of rounds fired by a gun per item |
| FallSpeed | Float | 1 | Speed at which the projectile should fall after being fired |
| ThrowSpeed / ShootSpeed | Float | 1 | The speed at which the projectile should be launched. 0 Will give it no velocity and drop instantly. |
| HitBoxSize | Float | 0.5 | The size of the hitbox. Units are probably /16ths |
| Damage | Float | 1 | Amount of damage to do when hit. Sets all values for all types below. |
| DamageVsLiving | Float | 1 | Amount of damage to do when hit. |
| DamageVsPlayer | Float | 1 | Amount of damage to do when hit. |
| DamageVsEntity | Float | 1 | Amount of damage to do when hit. |
| DamageVsVehicles | Float | 1 | Amount of damage to do when hit. |
| DamageVsPlanes | Float | 1 | Amount of damage to do when hit. |
| IgnoreArmorProbability | Float | 0 | Probability that a hit will ignore armourm 0=no chance, 1=always |
| IgnoreArmorDamageFactor | Float | 0 | If a hit ignores armour, additional multiplier for damage. 1=normal damage |
| BreaksGlass | Bool | false | Whether the projecticle breaks glass on impact. |
| Fuse | Integer | 0 | Ticks until a grenade explodes. 0 = explode on impact |
| DespawnTime | Integer | 0 | Time until the grenade despawns, without exploding. 0 = no despawn |
| ExplodeOnImpact / DetonateOnImpact | Bool | false | Whether to explode when hitting a block or entity |
| FireRadius / Fire | Float | 0 | The radius in which fire will be placed on impact |
| ExplosionRadius / Explosion | Float | 0 | The radius of the explosion to create on impact |
| ExplosionPower | Float | 1 | Multiplier for power of the explosive. (ability to take out blocks) 1 = vanilla. <1 will just reduce radius. |
| ExplosionBreaksBlocks | Bool | true | Whether the impact explosion breaks blocks |
| ExplosionsDamageVsLiving | Float | 1 | The damage to do to X from the explosion |
| ExplosionsDamageVsLiving | Float | 1 | The damage to do to X from the explosion |
| ExplosionsDamageVsPlane | Float | 1 | The damage to do to X from the explosion |
| ExplosionsDamageVsVehicle | Float | 1 | The damage to do to X from the explosion |
| DropItemOnDetonate | String | null | The item to drop when the projectile explodes |
| DetonateSound | String | ~ | The sound to play on detonation. |
| HasSubmunitions | Bool | false | Whether there are other projectiles by this one. |
| Submunition | String | ~ | Set the submunition |
| NumSubmunitions | Integer | 0 | Number of submunitions to create |
| SubmunitionDelay | Integer | ~ | Delay to create submunitions after impact / firing? |
| FlareParticleCount | Integer | 0 | Number of smoke particles to create |
| DebrisParticleCount | Integer | 0 | Number of debris particles to create |
| TrailParticles / SmokeTrail | Bool | false | Whether to create a trail of particles. |
| TrailParticleType | String | smoke | The type of particle to emity for the trail |
| HasLight | Boolean | false | Bullet should have full brightness. (Currently implemented for bullets only) |
| HasDynamicLight | Boolean | false | Bullet should emit light. |

### [BulletType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/guns/BulletType.java) extends [ShootableType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/guns/ShootableType.java)
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| FlakParticles | Integer | 0 | The number of flak particles produced when exploding |
| FlakParticleType | String | largesmoke | The type of flak particles to spawn |
| SetEntitiesOnFire | Bool | false | Whether to set entities on fire upon collision |
| HitSoundEnable | Boolean | false | Whether to play the hit sound |
| HitSound | String | ~ | The hit sound to play if enabled |
| HitSoundRange | Float | ~ | The range that the hit sound will play |
| Penetration / PenetratingPower | Float | 1 | Penetration power |
| PenetrationDecay | Float | 0 | Penetration to remove each tick, e.g. 0.01 -> 1% loss each tick |
| DragInAir | Float | 0.99 | Drag coefficient (Possibly 1 - )? Between 0 and 1 |
| DragInWater | Float | 0.80 | Drag coefficient in water (Possibly 1 - )? Between 0 and 1 |
| NumBullets | Integer | -1 | Number of bullets to fire per shot. -1 is "let gun decide"? |
| Accuracy / Spread | Float | -1 | Bullet accuracy. -1 is "let gun decide" |
| LivingProximityTrigger | Float | -1 | If greater than 0, the bullet will explode with this radius of a living entity |
| DriveableProximityTrigger | Float | -1 | If greater than 0, the bullet will explode with this radius of a Driveable |
| DamageToTriggerer | Float | 0 | How much damage to do to the entity who triggered it |
| PrimeDelay / TriggerDelay | Integer | 0 | Time after firing which the bullet may not detonate |
| NumExplodeParticles | Integer | 0 | Number of explosion particles to create |
| ExplodeParticles | String | largesmoke | The type of explosion particles to create |
| SmokeTime | Integer | 0 | Time for smoke to remain after explosion |
| SmokeParticles | String | explode | The type of particles to use for smoke |
| SmokeEffect | String | ~ | Add a potion effect to the smoke |
| SmokeRadius | Float | 5 | The radius that the smoke affects |
| VLS / HasDeadZone | Bool | false | Whether this is a vertical launch system/guided thing |
| VLS time / DeadZoneTime | Integer | 0 | How long it takes before guidance kicks in |
| FixedTrackDirection | Boolean | false | Whether.. it can only go in a straight line? |
| GuidedTurnRadius | Float | 3 | The turning radius of the guided missile |
| GuidedPhaseSpeed | Float | 2 | The speed during guiding? |
| GuidedPhaseTurnSpeed | Float | 0.2 | Speed of turning of guided missile? |
| BostParticle | String | ~ | The particle type for "boost phase" ie when it isn't guided yet |
| Torpedo | Boolean | false | Whether this is a torpedo or not |
| Bomb | ~ | ~ | Add this to set this as a BOMB |
| Shell | ~ | ~ | Add this to set this as a SHELL |
| Missile | ~ | ~ | Add this to set this as a MISSILE |
| WeaponType | String | ~ | Set weapon type (MISSILE, BOMB, SHELL, MINE, GUN, NONE) |
| LockOnToDriveables | Bool | false | Lock on to vehicles, planes and mechas. |
| LockOnToVehicles | Bool | false | Lock on to X |
| LockOnToPlanes | Bool | false | Lock on to X |
| LockOnToMechas | Bool | false | Lock on to X |
| LockOnToPlayers | Bool | false | Lock on to X |
| LockOnToLivings | Bool | false | Lock on to X |
| MaxLockOnAngle | Float | 45 | Maximum angle for locking on to a target |
| LockOnForce / TurningForce | Float | 1 | The force locking the bullet onto the target |
| MaxDegreeOfLockOnMissile | Integer | 20 | The highest degree that the missile will keep its target when locked on |
| TickStartHoming | Integer | 5 | How many ticks before the missile starts to home |
| EnableSACLOS | Boolean | false | Enable Wire/Radio guiding, (Semi-Active Guidance). Source must maintain lock for projctile to track |
| MaxDegreeOfSACLOS | Integer | 5 | Max angle around look vector that projectile will track within. If outside, tracking is lost. |
| MaxRangeOfMissile | Integer | 150 | Number of blocks that a missile can be guided for |
| CanSpotEntityDriveable | Boolean | false | ? |
| ShootForSettingPos | Boolean | false | ? |
| ShootForSettingPosHeight| Integer | 100 | ? |
| IsDoTopAttack | Boolean | false | Whether to hit from the top of the entitity it's locked on to |
| PotionEffect | String | ~ | The effects done to the entity hit |
| ManualGuidance | Boolean | false | Whether this is manually guided |
| LaserGuidance | Boolean | false | Whether this is laser guided |
| LockOnFuse | Integer | 10 | Ticks before explosion when locked on? |
| MaxRange | Integer | -1 | Maximum distance for something. -1 is infinite. |
| FancyDescription | Boolean | true | Whether to display information about the bullet/mag in the description, like a gun. |
| KnockbackModifier | Float | 1 | Modifier for knockback. Smaller = less knockback, bigger = more knockback, 1 = default knockback. Applies players hit by the bullet only. |
| BulletSpeedMultiplier | Float | 1 | Multiplier for bullet speed. Stacks with gun bullet speed. |

### [GrenadeType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/guns/GrenadeType.java) extends [ShootableType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/guns/ShootableType.java)
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| MeleeDamage | Integer | 1 | The damage caused by getting hit with the grenade |
| ThrowDelay | Integer | 0 | Time wait between grenade throws |
| ThrowSound | String | ~ | Sound to play when throwing grenade |
| DropItemOnThrow | String | ~ | Whether to drop an item when grenade is thrown |
| CanThrow | Bool | true | Whether the grenade can be thrown with right click |
| PenetratesEntities | Boolean | false | Whether it can go through entities |
| PenetratesBlocks | Boolean | false | Whether it can go through blocks |
| BounceSound | String | ~ | Sound on bound |
| Sticky | Bool | false | Whether the grenade should stick to surfaces |
| LivingProximityTrigger | Float | -1 | If greater than 1, explode when within this radius of a living |
| VehicleProximityTrigger | Float | -1 | If greater than 1, explode when within this radius of a vehicle |
| DamageToTriggerer | Float | 0 | Damage given to the entity who triggered it |
| DetonateWhenShot | Boolean | false | When this entity is shot, it will explode |
| PrimeDelay / TriggerDelay | Integer | 0 | Detonation can only happen after this time in ticks |
| StickToThrower | Boolean | false | Whether the grenade can stick to the person who threw it |
| StickToEntity | Boolean | false | Sticks to X |
| StickToDriveable | Boolean | false | Sticks to X |
| StickToEntityAfter | Boolean | false | ? |
| AllowStickSound | Boolean | false | Whether to play the stick sound. Only triggers on StickEntityAfter type of sticking. |
| StickSoundRange | Integer | 10 | The range to play the stick sound. Only triggers on StickEntityAfter type of sticking. |
| StickSound | String | ~ | The sound to play when sticking |
| NumExplodeParticles | Integer | 0 | Number of explosion particles to create |
| ExplodeParticles | String | largesmoke | The type of explosion particles to create |
| SmokeTime | Integer | 0 | Time for smoke to remain after explosion |
| SmokeParticles | String | explode | The type of particles to use for smoke |
| SmokeEffect | String | ~ | Add a potion effect to the smoke |
| SmokeRadius | Float | 5 | The radius that the smoke affects |
| SpinWhenThrown | Boolean | true | Whether the grenade should spin after thrown |
| Remote | Boolean | false | Whether the grenade can be detonated using a remote detonator |
| FlashBang | Boolean | false | Whether to use flashbang effects|
| FlashTime | Integer | 200 | Number of ticks that the flash lasts for |
| FlashRange | Integer | 8 | The number of blocks radius the flash effects affect |
| FlashSoundEnable | Boolean | false | Enable the flash sound |
| FlashSoundRange | Integer | 16 | The radius of blocks around to play the flash sound |
| FlashSound | String | ~ | The flash sound to play |
| FlashDamageEnable | Boolean | false | Whether to deal those in the flash range damage |
| FlashDamage | Float | ~ | The damage to deal to those in the flash range |
| FlashEffects | Boolean | false | Whether to enable flash effects |
| FlashEffectsID | Integer | ~ | The ID of the flash effects to use |
| FlashEffectsDuration | Integer | ~ | The number of ticks the flash effects last for |
| FlashEffectionsLevel | Integer | ~ | The intensity of flash effects |
| MotionSensor | Boolean | false | Enable motion sensor functionality |
| MotionSensorRange | Float | 5 | The radius in whenceforthward the motion sensor will work |
| MotionSoundRange | Float | 20 | The sound radius when motion is detected |
| MotionSound | String | ~ | The sound to play when motion is detected |
| MotionTime | Integer | 20 | Something in ticks? |
| DeployableBag | Boolean | false | Whether this is actually a deployable bag, and not an explodey thing |
| NumUses | Integer | 1 | The number of times the bag may be used before it runs out |
| HealAmount | Float | 0 | Amount to heal players by who use this bag |
| AddPotionEffect / PotionEffect | String | ~ | Add effect |
| NumClips | Integer | 0 | The number of ammo clips players get when using the ammo bag (multiplied by numBulletsInGun)|

### [MechaItemType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/driveables/mechas/MechaItemType.java) extends [InfoType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/types/InfoType.java)
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| Model | String | ~ | The model name |
| Texture | String | ~ | The texture name |
| Type | String | ~ | The type of upgrade. (upgrade, tool, armUpgrade, legUpgrade, headUpgrade, shoulderUpgrade, feetUpgrade, hipsUpgrade, nothing) |
| ToolType | String | sword | If it's a tool, the type of tool (pickaxe, axe, shovel, shears, sword) |
| Speed | Float | ~ | The speed of the tool |
| ToolHardness | Float | 1 | The maximum block hardness that can be broken |
| Reach | Float | 1 | Mecha reach multiplier |
| AutoFuel | Boolean | false | Whether this is an autofueler upgrade |
| Armour | Float | 0 | Reduction of incoming damage to the mecha |
| CoalMultiplier | Float | 1 | Coal pickup multiplier |
| DetectSound | String | ~ | Plays a sound on diamond detection? |
| DiamondDetect | Boolean | false | Whether this is a diamond detector |
| DiamondMultiplier | Float | 1 | Diamond pickup multiplier |
| EmeralMultiplier| Float | 1| Emerald pickup multiplier |
| FlameBurst | Boolean | false | ? |
| Floatation | Boolean | false | Whether this is a floatation device |
| ForceBlockFallDamage | Boolean | false | Possibly to deflect fall damage? |
| ForceDark | Boolean | false | ? |
| InfiniteAmmo | Boolean | false | Whether this is an infinite ammo box |
| IronMultiplier | Float | 1 | Iron pickup multiplier |
| IronRefine | Boolean | false | Automatically turn iron ore into ingots when mined |
| ItemVacuum | Boolean | false | Pickup mined items and put into inventory rather than dropping. |
| LightLevel | Integer | 0 | Light level to emit (0-15) |
| Nanorepair | Boolean | false | Slowly repair mecha |
| NanorepairAmount | Float | 1 | The amount to repair the mecha per tick if nanorepair is on |
| RedstoneMultiplier | Float | 1 | Redstone pickup multiplier |
| RocketPack | Boolean | false | Do rocket type stuff |
| SoundEffect | String | ~ | Sound effect for rocket firing |
| Soundtime | Float | 0 | Sound effect time (I presume for rocket firing) |
| SpeedMultiplier | Float | 1 | Multiplier for mecha movement speed |
| StopMechaFallDamage | Boolean | false | Stop mecha taking damage from falling |
| WasteCompact | Boolean | false | Delete certain items when picked up to save inventory space |

### [DriveableType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/driveables/DriveableType.java) extends [PaintableType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/paintjob/PaintableType.java)

PSA: On some versions of FlansMod, you MUST define your seat and drivers before adding guns, setting up traverse sounds or anything related to the vehicle's seats. You do this by ensuring Passenger definitions are higher in the file.
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| VehicleGunModelScale | Float | 1 | The scale to transform the vehicle guns |
| Model | String | ~ | Model name |
| VehicleGunReloadTick | Integer | 15214541 | The tick to reload vehicle gun sounds. 9 days 19:18:47.050 ? Very cursed. |
| Texture | String | ~ | Texture file to use |
| IsExplosionWhenDestroyed | Boolean | false | Make an explosion when the vehicle is destroyed |
| DeathExplosionPower | Float | 1 | Power of explosion when the vehicle is destroyed |
| DeathExplosionRadius | Float | 4 | Radius of explosion when the vehicle is destroyed |
| DeathExplosionBreaksBlocks | Boolean | false | Whether death explosion breaks blocks or not |
| DeathFireRadius | Float | 1 | Radius of fire to be created when vehicle is destroyed |
| DeathExplosionDamageVsLiving | Float | 1 | Damage of death explosion against living |
| DeathExplosionDamageVsPlayer | Float | 1 | Damage of death explosion against players |
| DeathExplosionDamageVsPlane | Float | 1 | Damage of death explosion against planes |
| DeathExplosionDamageVsVehicle | Float | 1| Damage of death explosion against vehicles |
| FallDamageFactor | Float | 1 | Multiplier for fall damage in some way |
| MaxThrottle | Float | 1 | Maximum throttle in generic units? |
| MaxNegativeThrottle | Float | 0 | Maximum negative throttle, aka reversing |
| ClutchBrake | Float | 0 | Clutch braking modifier |
| WorksUnderwater | Boolean | False | Whether this driveable can still work when submerged |
| MaxThrottleInWater | Float | 0.5 | Maximum throttle when the vehicle is in water |
| MaxDepth | Integer | 3 | Blocks of water deep before the vehicle stops moving |
| Drag | Float | 1 | Coefficient of drag |
| TurretOrigin | (Float) X Y Z | 0 0 0 | The origin of the turret |
| TurretOriginOffset | (Float) X Y Z | 0 0 0 | The offset of the turret from the origin? |
| CollisionPoint / AddCollisionPoint | (float) X Y Z partName | | Points for block based collision detection |
| CollisionDamageEnable | Boolean | false | Damage the vehicle when collision points collide with blocks |
| CollisionDamageThrottle | Float | 0 | Damage to cause to colliding entities |
| CollisionDamageTimes | Float | 0 | when to cause damage to colliding entities based on throttle |
| CanLockAngle | Float | 10 | Maximum angle on which the ? can lock on to an entity |
| LockOnSoundTime | Integer | 60 | Time to play the lock on sound |
| LockOnToDriveables | Boolean | false | Lock on to Vehicles, plans and mechas |
| LockOnToVehicles | Bool | false | ~ |
| LockOnToPlanes | Bool | false |  |
| LockOnToMechas | Bool | false |  |
| LockOnToPlayers | Bool | false |  |
| LockOnToLivings | Bool | false |  |
| LockOnSoundRange | Integer | 5 | The radius to play the lock on sound |
| CanRoll | Boolean | true | Whether the vehicle can roll |
| HasFlare | Boolean | false | Whether the vehicle has a flare function |
| FlareDelay | Integer | 200 | Ticks between flare launches |
| TimeFlareUsing | Integer | 1 | Ticks when the flare is active |
| PlaceableOnLand | Boolean | true | Whether the driveable can be placed on land |
| PlaceableOnWater | Boolean | true | Whether the driveable can be placed on water |
| PlaceableOnSponge | Boolean | true | Whether the driveable can be placed on sponge? |
| FloatOnWater | Boolean | false | Whether the driveable floats on water |
| Boat | ~ | false | placeableOnLand = false, placeableOnWater = true, floatOnWater = true, wheelStepHeight = 0 |
| Buoyancy | Float | 0.0165 | Upwards force per wheel when a driveable is on water |
| FloatOffset | Float | 0 | Offset in blocks for floating above/below wheel positions. |
| CanMountEntity | Boolean | false | Sets vanilla CanMountEntity NBT value. True allows this entity to mount another. (Horse, other vehicle e.t.c). |
| EngineStartTime | Float | 0 | Time between the vehicle being placed, and it being driveable. |
| Wheel / WheelPosition | (Integer) ID (Float) X Y Z (partName) | ~ | Add a wheel position with ID. partName is optional and will default to coreWheel unless stated. |
| WheelRadius / WheelStepHeight | Float | 1 | The wheel radius. Allows travelling up blocks when > 1 or root2 (Not sure which) |
| WheelSpringStrength / SpringStrength | Float | 0.5 | Strength of suspension? |
| TrackFrames | Integer | 2 | The number of animation frames for track animation |
| Harvester | Boolean | false | Whether to enable the harvester |
| CollectHarvest | Boolean | false | Whether to pickup items that are harvested |
| DropHarvest | Boolean | false | Whether to drop the items that are harvested |
| HarvestBox | (float)(size) X Y Z (position) X Y Z | 0 0 0 0 0 0 | The cuboid to harvest blocks inside |
| HarvestMaterial | String | ~ | Add material to be harvested |
| HarvestToolType | String | ~ | See later section on HarvestTools|
| CargoSlots | Integer | ~ | The number of cargo slots the driveable has |
| BombSlots / MineS;ots | Integer | ~ | The number of slots for bombs in the driveable |
| MissileSlots / ShellSlots | Integer | ~ | The number of slots for missiles in the driveable |
| RestrictAmmunitionInput | Bool | false | Whether to only allow bullet types/grenade types in gun/bomb/missile slots in the vehicle. |
| FuelTankSize | Integer | 100 | Size of fuel tank. -1 means that the vehicle does not need fuel, and will ignore engine start times. |
| BulletDetectionRadius | Integer | 5 | The radius (in blocks) to check for bullets |
| AddAmmo | String | ~ | Add an ammo type by shortname |
| AllowAllAmmo / AcceptAllAmmo | Boolean | false | Allow any type of ammunition to be shot (Please dont do this) |
| Primary | String | None | Set the primary to weapon type (MISSILE, BOMB, SHELL, MINE, GUN, NONE) |
| Secondary | String | None | Set the secondary to weapon type (MISSILE, BOMB, SHELL, MINE, GUN, NONE) |
| ShootDelayPrimary | Float | -1 | The number of ticks to wait between shooting. -1 is use gun RPM or 1. |
| ShootDelaySecondary | Float | -1 | The number of ticks to wait between shooting. -1 is use gun RPM or 1. |
| RoundsPerMinPrimary | Float | ~ | Sets ShootDelay using RPM. Max is 1200 |
| RoundsPerMinSecondary | Float | ~ | Sets ShootDelay using RPM Max is 1200 |
| DamageMultiplierPrimary | Float | 1 | Multiplies projectile damage |
| DamageMultiplierSecondary | Float | 1 | Multiplies projectile damage |
| PlaceTimePrimary | Integer | 5| ? |
| PlaceTimeSecondary | Integer | 5 | ? |
| ReloadTimePrimary | Integer | 0 | Ticks taken to reload the primary gun |
| ReloadTimeSecondary | Integer | 0 | Ticks taken to reload the secondary gun |
| AlternatePrimary | Boolean | false | Whether to alternate barrels or shoot all at once |
| AlternateSecondary | Boolean | false | Whether to alternate barrels or shoot all at once |
| ModePrimary | String | FULLAUTO | Firemode from (fullauto, minigun, burst, semiauto) |
| ModeSecondary | String | FULLAUTO | Firemode from (fullauto, minigun, burst, semiauto) |
| BulletSpeed | Float | 3 | Bullet speed for primary or secondary? |
| BulletSpread | Float | 0 | Bullet spread for primary or secondary? |
| RangingGun | Boolean | false | ? |
| GunLength | Float | 0 | Supposedly for backwards compatibility |
| RecoilDistance | Float | 5 | ? |
| RecoilTime | Float | 5 | ? |
| ShootPointPrimary | (float) X Y Z (partName) (gunName) | ~ | Add a shootpoint. Other ways of entering this are available. partName and gunName are entirely optional. Will default to core if not defined. |
| ShootPointSecondary | (float) X Y Z (partName) (gunName) | ~ | Add a shootpoint. Other ways of entering this are available. partName is entirely optional. Will default to core if not defined. |
| EnableReloadTime | Boolean | flase | Enable reload time? |
| ShootParticlesPrimary | particleName (float) X Y Z | ~ | Add shoot particles |
| ShootParticlesSecondary | particleName (float) X Y Z | ~ | Add shoot particles |
| SetPlayerInvisible | Boolean | false | Whether riding players should have invis effect |
| IT1 | Boolean | false | Whether this uses IT1 reloads. I bet you it doesn't ;) |
| FixedPrimary | Boolean | false | Fixes the gun to PrimaryAngle relative to the turret. Otherwise, free to aim. |
| PrimaryAngle | (float) X Y Z | 0 0 0 | The angle at which the primary should be fixed at. Expressed as a vector direction. |
| FixedSecondary | Boolean | false | Fixes the gun to SecondaryAngle relative to the turret. Otherwise, free to aim. |
| SecondaryAngle | (float) X Y Z | 0 0 0 | The angle at which the secondary should be fixed at. Expressed as a vector direction. |
| AddGun | (float) X Y Z partName gunName (offset)(float) (X Y Z) | ~ | Add gun. Offset is not required. | 
| BombPosition | X Y Z (offset)(X Y Z) | ~ | Sets primary to BOMB, adds a bomb position. Offset is not required. | 
| BarrelPosition | X Y Z (offset)(X Y Z) | ~ | Adds a new barrel position and changes primary to SHELL. Offset is not required. | 
| ShootDelay | Integer | 1 | Duplicate way of setting shoot delay for secondary... would not reccomend. | 
| ShellDelay / BombDelay | Integer | 1 | Duplicate way of setting shoot delay for primary... would not reccomend. | 
| AddRecipeParts | partName quantity itemName| ~ | Add a part to the recipe. Damaged parts can be added, but I don't know how to do that. | 
| AddDye | quantity dyeName | ~ | Add a dye to the recipe. I am unsure if it is a name or an ID. | 
| SetupPart | partName health (int) x y z height width depth (penetration) | ~ | Add a collision box with the respective part to the driveables collision boxes. Penetration is a float, and not required| 
| Driver / Pilot | (int) X Y Z (partname) (minYaw) (maxYaw) (minPitch) (maxPitch) | ~ | Set driver position. Part will default to core. | 
| DriverPart | String | Core | Set part that the driver is on/in | 
| DriverGun / PilotGun | String | ~ | Set gun name that the pilot controls | 
| DriverGunOrigin | X Y Z | 0 0 0 | Set the place where bullets spawn from for driver gun. | 
| RotatedDriverOffset | X Y Z | ? | ? | 
| RotatedPassengerOffset | ID X Y Z | ? | ? | 
| DriverAimSpeed | X Y Z | ? | The speed of which the driver can aim | 
| PassengerAimSpeed | ID X Y Z | ? | The speed of which the passenger can aim | 
| DriverLegacyAiming | Boolean | ? | Something to do with legacy. Don't touch probably | 
| PassengerLegacyAiming | ID Boolean | ? | Something to do with legacy. Don't touch probably | 
| DriverTraverseSounds | Boolean | ? | Play sounds while rotating to aim | 
| PassengerTraverseSounds | SeatID Boolean | ? | Play sounds while rotating to aim | 
| Passenger | (int) X Y Z (partname) (minYaw) (maxYaw) (minPitch) (maxPitch) (gunType) (gunName) | ~ | Add a passenger with or without guns and viewing limits. | 
| GunOrigin | ID X Y Z | ~ | Set the gun origin for the gun with specified ID | 
| YOffset | Float | 0 | Change the level of Y somehow for a model. |
| CameraDistance | Float | 5 | Third person camera distance in blocks | 
| StartSoundRange | Integer | 50 | Radius in blocks to play the starting sound | 
| StartSoundLength | Integer | ~ | The length in ticks of the starting sound | 
| EngineSoundRange | Integer | 50 | Radius in blocks to play the engine sound | 
| EngineSoundLength | Integer | ~ | The length in ticks of the engine sound | 
| IdleSoundLength | Integer | 50 | The length in ticks of the starting sound This may be a mistake and actually be IdelSoundRange. Who knows? | 
| BackSoundRange | Integer | 50 | Radius in blocks to play the back sound | 
| BackSoundLength | Integer | ~ | The length in ticks of the starting sound | 
| SoundTime | Integer | 0 | The length in ticks of the ? sound | 
| YawSoundLength | Integer | ~ | The length in ticks of the sound | 
| PitchSoundLength | Integer | ~ | The length in ticks of the sound | 
| PassengerYawSoundLength | SeatID soundLength | ~ | The length in ticks of the sound | 
| PassengerPitchSoundLength | SeatID soundLength | ~ | The length in ticks of the sound |
| ExitSoundLength | Integer | 50 | Length (in ticks) of the exit sound | 
| ExitSound | string | ~ | The sound to play when a player exits a vehicle | 
| StartSound | String | ~ | The name of the sound to play | 
| EngineSound | String | ~ | The name of the sound to play | 
| IdleSound | String | ~ | The name of the sound to play | 
| BackSound | String | ~ | The name of the sound to play | 
| YawSound | String | ~ | The name of the sound to play | 
| PitchSound | String | ~ | The name of the sound to play | 
| PassengerYawSound | SeatID String | ~ | The name of the sound to play | 
| PassengerPitchSound | SeatID String | ~ | The name of the sound to play | 
| ShootMainSound | String | ~ | The name of the sound to play | 
| ShootReloadSound | String | ~ | The name of the sound to play | 
| ShootSecondarySound / ShootSoundSecondary | String | ~ | The name of the sound to play | 
| PlaceSoundPrimary | String | ~ | The name of the sound to play | 
| PlaceSoundSecondary | String | ~ | The name of the sound to play | 
| ReloadSoundPrimary | String | ~ | The name of the sound to play | 
| ReloadSoundSecondary | String | ~ | The name of the sound to play | 
| LockedOnSound | String | ~ | The name of the sound to play | 
| LockOnSound | String | ~ | The name of the sound to play | 
| LockingOnSound | String | ~ | The name of the sound to play | 
| FlareSound | String | ~ | The name of the sound to play | 
| FancyCollision | Boolean | false | Enable some fancy collision type |
| AddCollisionMesh | [posX,posY,posZ] [sizeX,sizeY,sizeZ] [p1X,p1Y,p1Z] .. [p8X,p8Y,p8Z] | ~ | Add collision shapebox to core. p1..p8 are the corners of the shapebox. Positive = expand out, negative = shrink corner in. |
| AddCollisionMeshRaw | posX posY posZ sizeX sizeY sizeZ p1X p1Y p1Z .. p8X p8Y p8Z | ~ | Add collision shapebox to core, but with no brackets. p1..p8 are the corners of the shapebox. Positive = expand out, negative = shrink corner in. |
| AddTurretCollisionMesh | [posX,posY,posZ] [sizeX,sizeY,sizeZ] [p1X,p1Y,p1Z] .. [p8X,p8Y,p8Z] | ~ | Add collision shapebox to turret. p1..p8 are the corners of the shapebox. Positive = expand out, negative = shrink corner in. |
| AddTurretCollisionMeshRaw | posX posY posZ sizeX sizeY sizeZ p1X p1Y p1Z .. p8X p8Y p8Z | ~ | Add collision shapebox to turret but with no brackets. p1..p8 are the corners of the shapebox. Positive = expand out, negative = shrink corner in. |
| LeftLinkPoint | X Y Z | ~ | Add link point for track animation |
| RightLinkPoint | X Y Z | ~ | Add link point for track animation |
| TrackLinkLength | Float | 0 | Set link length for track animation |
| OnRadar | Boolean | false | For ICBM mod radar |
| AddParticle / AddEmitter | (string)effectType (int)emitRate (float X,Y,Z) origin extent velocity (float) minThrottle maxThrottle minHealth maxHealth partName | ~ | Add an emitter rendering particles from origin to extent |

### HarvestToolTypes:

#### Axe:
* Wood
* Plants
* Vines

#### Pickaxe / Drill:
* Iron
* Anvil
* Rock

#### Spade / Shovel / Excavator:
* Ground
* Grass
* Sand
* Snow
* Clay

#### Hoe / Combine:
* Plants
* Leaves
* Vine
* Cactus
* Gourd (Whatever that is : It's supposedly a general term for fruits)

#### Tank:
* Leaves
* Cactus
* Wood
* Plants

### [MechaType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/driveables/mechas/MechaType.java) extends [DriveableType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/driveables/DriveableType.java)
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| TurnLeftSpeed | Float | 1 | The speed at which the mehca will turn left |
| TurnRightSpeed | Float | 1 | The speed at which the mehca will turn right |
| MoveSpeed | Float | 1 | The forwards and backwards speed of the mecha |
| SquashMobs | Boolean | false | Whether mobs under the wheels will be crushed/killed |
| StepHeight | Integer | 0 | How many blocks may be stepped up when walking. (Without jumping) | 
| JumpHeight | Float | 1 | How high the mecha can jump in blocks. |
| ~ | Float | 1 | jumpVelocity = Sqrt(Abs(9.81 * (jumpHeight + 0.2)/200))|
| RotateSpeed | Float | 10 | Degrees per second/tick that the mecha will rotate at, max |
| StompSound | String | ~ | The sound to play on impace with the ground? |
| StompSoundLength | Integer | 0 | Number of ticks to play the stomp sound for |
| StompSoundRangeLower | Float | 0 | ? |
| StompSoundRangeUpper | Float | 0 | ? |
| LeftArmOrigin | (float) X Y Z | ~ | The origin of X |
| RightArmOrigin | (float) X Y Z | ~ | The origin of X |
| ArmLength | Float | 1 | Length of the arm in 16ths of a block |
| LegLength | Float | 1 | Length of the leg in 16ths of a block |
| HeldItemScale | Float | 1 | The scale to trasform items or tools rendered in the mechas "hands" |
| Height | Float | 3 | The height of the collision box in 16ths of a block |
| Width | Float | 2  | The width of the collision box in 16ths of a block |
| ChassisHeight | Integer | 1 | The height of the body above the ground in 16ths of a block |
| FallDamageMultiplier | Float | 1 | How much fall damage the mecha should recieve (compared to a player?) |
| DamageBlocksFromFalling | Boolean | true | Whether the mecha forces fall damage onto thr blocks around it, destroying them. |
| LegSwingLimit | Float | 2 | ? |
| LimitHeadTurn | Boolean Float | false, 90 | Whether view limits should be applied on the head |
| LegSwingTime | Float | 5 | The speed at which leg movements are animated |
| UpperArmLimit | Float | 90 | The maximum angle of the upper arm? Probably in the XY plane (Z axis) |
| LowerArmLimit | Float | 90 | The maximum angle of the lower arm? Probably in the XY plane (Z axis) |
| LeftHandModifier | (float) X Y Z | 0 0 0 | Modifier for weapons in hand. in 16ths of a block |
| RightHandModifier | (float) X Y Z | 0 0 0 | Modifier for weapons in hand. in 16ths of a block |
| LegNode | (Integer)rotation (Float)lowerBound (Float)upperBound (Integer)speed (Integer)legPart | ~ | Add a leg node |
| LegAnimSpeed | Float | 0 | The speed at which to animate leg movement |
| RestrictInventoryInput | Bool | false | Whether to only allow ammunition and fuel to be put into the inventory by the player (item pickup by mecha with item vacuum still works) |
| AllowMechaToolsInRestrictedInv | Bool | true | Whether to allow mecha addons to be put into the inventory of the mecha |

### [PlaneType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/driveables/PlaneType.java) extends [DriveableType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/driveables/DriveableType.java)
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| Mode | String | PLANE | VTOL / HELI / PlANE |
| NewFlightControl | Boolean | false | Whether to enable new flight physics for planes. Configs marked with § are for the new flight controller only. Not suitable for VTOL planes. |
| MaxThrust | Float | 50 | Maximum thrust of an engine in 10s of kgf § |
| MaxSpeed | Float | 2.0 | Max Speed plane can fly blocks/tick § |
| TakeoffSpeed | Float | 0.5 | Speed where plane can start to fly § |
| TurnLeftSpeed | Float | 1 | Modifier for turn speed |
| TurnRightSpeed | Float | 1 | Modifier for turn speed |
| LookUpSpeed | Float | 1 | Modifier for turn speed |
| LookDownSpeed | Float | 1 | Modifier for turn speed |
| RollLeftSpeed | Float | 1 | Modifier for turn speed |
| RollRightSpeed | Float | 1 | Modifier for turn speed |
| Supersonic | Boolean | false | Whether plane is suited for speeds over 2.0 Blocks/tick § |
| Lift | Float | 1 | Coefficient of lift |
| Mass | float | 1000 | Mass in kg § |
| WingArea | Float| 1 | Area in meters^2 of a single wing § |
| HeliThrottlePull | Boolean | true | Whether throttle will "pull" itself to 50% in a helicopter. | 
| EmptyDrag | Float | 1 | Extra drag given to the plane when there is no driver. |
| ShootDelay | Integer | ~ | Number of ticks between shots of the guns |
| BombDelay | Integer | ~ | Number of ticks between bombs dropping |
| Propeller | (Int)ID (Int)X Y Z part partType | ~ | Add a propeller to the plane |
| HeliPropeller | (Int)ID (Int)X Y Z part partType | ~ | Add a propeller to the plane |
| HeliTailPropeller | (Int)ID (Int)X Y Z part partType | ~ | Add a propeller to the plane |
| HasFlare | Boolean | ~ | Whether the plane is equipped with a flare |
| FlareDelay | Integer | ~ | The number of ticks between flares being deployed |
| TimeFlareUsing | Integer | ~ | The number of ticks the flare is used for |
| PropSoundLength | Integer | ~ | The number of ticks the prop sound plays for |
| PropSound | String | ~ | Set the propeller sound |
| ShootSound | String | ~ | Set the sound played on shoot |
| BombSound | String | ~ | Set the sound played on bomb drop |
| HasGear | Boolean | false | Whether the plane has retractable landing gear |
| HasDoor | Boolean | false | Whether the plane has openable doors |
| HasWing | Boolean | false | Whether the plane has retractable wings |
| FoldWingForLand | Boolean | false | Whether to retract the wings when close enough to the ground |
| FlyWithOpenDoor | Boolean | false | Whether the plane can fly with the door open |
| AutoOpenDoorsNearGround | Boolean | true | Wether to auto deploy landing gear near the ground or not |
| AutoDeployLandingGearNearGround | Boolean | true | Wether to auto open the door near the ground or not |
| RestingPitch | Float | 0 | The angle when parked. Angle that it will spawn in at |
| SpinWithoutTail | Boolean | false | Whether the helicopter should spin if no tail is present |
| Valkyrie | Boolean | false | Whether this plane is a valkyrie |
| WingPosition1 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| WingPosition2 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| WingRotation1 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| WingRotation2 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| WingRate | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| WingRotRate | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| WingWheelPosition1 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| WingWheelPosition2 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| WingWheelRotation1 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| WingWheelRotation2 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| WingWheelRate | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| WingWheelRotRate | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| TailWheelPosition1 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| TailWheelPosition2 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| TailWheelRotation1 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| TailWheelRotation2 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| TailWheelRate | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| TailWheelRotRate | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| DoorPosition1 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| DoorPosition2 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| DoorRotation1 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| DoorRotation2 | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| DoorRate | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| DoorRotRate | (Float)X,Y,Z | 0 0 0 | Animation position for X |
| InflightInventory | Boolean | true | False = true.. yeah. |

### [VehicleType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/driveables/VehicleType.java) extends [DriveableType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/driveables/DriveableType.java)
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| TurnLeftSpeed | Float | 1 | Modifier for turning speed |
| TurnRightSpeed | Float | 1 | Modifier for turning speed |
| SquashMobs | Boolean | false | Whether to squash/kill livings under the wheels |
| FourWheelDrive | Boolean | false | Apply power to all four wheels, or just two |
| Tank / TankMode | Boolean | false | This has tracks instead of wheels |
| Mass | Float | 1000 | The mass of the vehicle, for realistic acceleration. Roughly in KG? |
| UseRealisticAcceleration | Boolean | false | Use a system which uses vehicle mass and engine power to calculate acceleration. |
| ThrottleDecay | Float | 0.0035F | Amount to decrease the throttle by each tick |
| BrakingModifier | Float | 1 | Multiplier for braking force (slowing down in opposite direction to travel). See also: ThrottleDecay |
| Gravity | Float | 0.175 | The rate at which to increase speed downwards when falling. |
| MaxFallSpeed | Float | 0.85 | Terminal velocity of the vehicle. |
| HasDoor | Boolean | false | Whether this has openable doors |
| ShootWithOpenDoor | Boolean | false | Whether the vehicle can NOT shoot while the door is open. True = CANNOT fly with door open False = CAN fly with door open (it's inverted) |
| RotateWheels | Boolean | false | Whether to animate rotating wheels |
| FixTrackLink | Integer | 5 | ? Something to do with track animations |
| FlipLinkFix | Boolean | false | ? Something to do with track animations |
| DoorPosition1 | (float)X,Y,Z | ~ | Config for animations |
| DoorPosition2 | (float)X,Y,Z | ~ | Config for animations |
| DoorRotation1 | (float)X,Y,Z | ~ | Config for animations |
| DoorRotation2 | (float)X,Y,Z | ~ | Config for animations |
| DoorRate | (float)X,Y,Z | ~ | Config for animations |
| DoorRotRate | (float)X,Y,Z | ~ | Config for animations |
| Door2Position1 | (float)X,Y,Z | ~ | Config for animations |
| Door2Position2 | (float)X,Y,Z | ~ | Config for animations |
| Door2Rotation1 | (float)X,Y,Z | ~ | Config for animations |
| Door2Rotation2 | (float)X,Y,Z | ~ | Config for animations |
| Door2Rate | (float)X,Y,Z | ~ | Config for animations |
| Door2RotRate | (float)X,Y,Z | ~ | Config for animations |
| ShootDelay | Integer | ~ | Number of ticks to wait between shots |
| ShellDelay | Integer | ~ | Number of ticks to wait between shell shots |
| ShootSound | String | ~ | Sound to play when shooting | 
| ShellSound | String | ~ | Sound to play when shooting shells | 
| DriftSound | String | ~ | Sound to play when drifting | 
| DriftSoundLength | Integer | ~ | Number of ticks to play drift sound for |
| AddSmokePoint / AddSmokeDispenser | (float position)X,Y,Z (float direction)X,Y,Z (Integer)detTime part | ~ | Add smoke particle emitter to the model | 

### [GunBoxType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/guns/boxes/GunBoxType.java) extends [InfoType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/types/InfoType.java)
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| TopTexture | String | ~ | The path of the texture to display on the block | 
| BottomTexture | String | ~ | The path of the texture to display on the block | 
| SideTexture | String | ~ | The path of the texture to display on the block | 
| Page / SetPage | List of strings | ~ | Set name of pages in the gun box | 
| AddGun | (String)nameOfGun num partname num partname.... | ~ | Adds a gun with recipe to the box | 
| AddAmmo / AddAltAmmo / AddAlternateAmmo | (String)nameOfGun num partname num partname.... | ~ | Adds ammo with recipe to the box | 
| GuiTexture | String | ~ | The path of the texture to use for the GUI background. Defaults if not set |
| GunBoxNameColor | HexString | 404040 | Unsigned hex colour |
| ItemTextColor | HexString | 404040 | Unsigned hex colour |
| ListTextColor | HexString | 404040 | Unsigned hex colour |
| PageTextColor | HexString | FFFFFF| Unsigned hex colour |
| ButtonTextColor | HexString | FFFFFF | Unsigned hex colour |
| ButtonTextHighlight | HexString | FFFFFF | Unsigned hex colour |

### [PartType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/parts/PartType.java) extends [InfoType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/types/InfoType.java)
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| Category | String | ~ | Generic string marking category. In theory is part of a set that I haven't looked for |
| StackSize | Integer | ~ | The maximum stack size of the item |
| EngineSpeed | Float | 1 | Speed modifier to the attached vehicle |
| EnginePower | Float | 10 | Engine power for realistic acceleration |
| FuelConsumption | Float | 1 | Rate at which the engine consumes fuel |
| Fuel | Integer | 0 | If this is a fuel tank, the amount of fuel that it gives |
| PartBoxRecipe | ~ | ~ | Part box is unused |
| WorksWith | List of strings | mecha, plane, vehicle | The types of driveable an engine will work with |
| UseRF / UseRFPower | Boolean | false | Whether to use redstone flux power if it is available |
| RFDrawRate | Integer | 1 | Amount of RF to consume per tick |

### [ToolType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/tools/ToolType.java) extends [InfoType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/types/InfoType.java)
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| Model | String | ~ | The model file to use |
| Texture | String | ~ | Path of the texture file to use |
| Parachute | Boolean | false | Whether to deploy a parachute on use, then consume the item |
| ExplosiveRemote | Boolean | false | Whether this will detonate the most recently placed remote trigger explosive |
| Key | Boolean | false | Whether this item is a key (Unimplmented/De-implemented) |
| Heal / HealPlayers | Boolean | false | Whether to give player health on use |
| Repair / RepairVehicles | Boolean | false | Wether to give driveable health on use |
| HealAmount / RepairAmount | Integer | 0 | Amount of healing to give per click |
| ToolLife / ToolUses | Integer | 0 | Number of teims the tool can be used. 0 is infinite |
| EUPerCharge | Integer | 0 | Unimplemented. Charging tools with IC2 EU |
| RechargeRecipe | list of (Integer)amount (String)itemName[.](integer damage) | ~ | Add recharge recipe. Damage not required |
| DestroyOnEmpty | Boolean | true | Whether to destroy the item when it has been entirely used up |
| Food / Foodness | Integer | 0 | Hunger filled when this item is used |

### [ArmourBoxType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/teams/ArmourBoxType.java) extends [InfoType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/types/InfoType.java)
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| TopTexture | String | ~ | Texture to use for the box |
| BottomTexture | String | ~ | Texture to use for the box |
| SideTexture | String | ~ | Texture to use for the box |
| AddArmor / AddArmour | (String)shortname (String)name NEXT LINE (String) armourType (list) amount shortname | ~ | Add armour of type + recipe |

### [ArmourType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/teams/ArmourType.java) extends [InfoType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/types/InfoType.java)
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| Model | String | ~ | The model file to use |
| Type | String | ~ | Hat/Helmet Chest/Body Legs/Pants Shoes/Boots |
| DamageReduction / Defence | Double | ~ | Damage modifier between 0 and 1 that stacks with other armours. Sets both BulletDefence and OtherDefence |
| BulletDefence | ~ | Double | Damage modifier between 0 and 1 that stacks with other armours. For bullet damage specifically. |
| OtherDefence | ~ | Double | Damage modifier between 0 and 1 that stacks with other armours. For NON-bullet damage specifically. |
| PenetrationResistance | Float | 0 | Resistance to penetration of a bullet. Same units as bullet penetration. | 
| MoveSpeedModifier / Slowness | Float | 1 | Modifier for player movement speed |
| JumpModifier | Float | 1 | Modifier for player jump height |
| KnockbackReduction / KnockbackModifier | Float | 0.2 | Modifier for knockback |
| NightVision | Boolean | false | Whether night vision should be enabled for this armour |
| Invisible | Boolean | false | Whether this gives the user the invisibilty effect |
| NegateFallDamage | Boolean | false | Stop fall damage affecting the player |
| FireResistance | Boolean | false | Give the player fire resistance effects |
| WaterBreathing | Boolean | false | Give the player water breathing effects |
| Overlay | String | null | Location of texture from scopes directory to use |
| SmokeProtection | Boolean | false | Whether smoke effects from grenades affect the player |
| OnWaterWalking | Boolean | false | Gives the player the ability to walk on water |
| Durability | Integer | false / 0 | Set the durability of the armour |
| ArmourTexture / ArmorTexture | String | ~ | Texture from assets/flansmod/armor/(armourTextureName)_armourID.png See next section for armour ID|

#### Armour types

| ID | Keyword | Alternate Keyword |
|---------|------|---------|
| 0 | Hat | Helmet |
| 1 | Chest | Body |
| 2 | Legs | Pants |
| 3 | Shoes | Boots |

I apologise for mistakes in this file. Please PR if you spot any, or modify when things change.


### [Team](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/teams/Team.java) extends [InfoType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/types/InfoType.java) | example - https://pastebin.com/bWrarEpQ
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| Name | String | ~ | Team name |
| ShortName | String | ~ | Team shortname |
| TextColour | String | ~ | This is used for player names and such. TextColour can be one of Black, Blue, Green, Aqua, Red, Purple, Orange, LGrey, Grey, LBlue, LGreen, LAqua, Red, Pink, Yellow, White |
| TeamColour | int, int, int | ~ | TeamColour is a more specific colour for banners, flags and spawn points. It is specified by Red, Green and Blue, each ranging from 0 to 255 |
| Hat | String | ~ | Specify the armour this team should spawn with(usage: Shoes armorShortName)(example Shoes kskHelmet) |
| Chest | String | ~ | Specify the armour this team should spawn with(usage: Shoes armorShortName)(example Shoes kskBody) |
| Legs | String | ~ | Specify the armour this team should spawn with(usage: Shoes armorShortName)(example Shoes kskPants) |
| Shoes | String | ~ | Specify the armour this team should spawn with(usage: Shoes armorShortName)(example Shoes kskBoots) |
| AddClass | String | ~ | Add class for this team(usage: AddClass classShortName)(example AddClass kskSniper1) |

### [PlayerClass](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/teams/PlayerClass.java) extends [InfoType](https://github.com/Unknown025/Flans-Mod-Plus/blob/Ultimate/src/main/java/com/flansmod/common/types/InfoType.java) | example - https://pastebin.com/TmCugK7j
| Keyword | Type | Default | Purpose |
|---------|------|---------|---------|
| Name | String | ~ | Class name |
| ShortName | String | ~ | Class shortname |
| UnlockLevel | int | 0 | Required level to select this class |
| AddItem | String, int | ~ | Add item to class(usage: AddItem itemShortName ammount)(example: AddItem l96ammo 3) |
| AddItem | String, int | ~ | Add gun with attachments to class(usage: AddItem gunShortName+attachmentShotName)(example: AddItem g3+flanMWSilencer+flanMW4xScope) |
| Hat | String | ~ | Optional parameters, used to replace the armor originally specified in the team configuration(usage: Shoes armorShortName)(example Shoes kskHelmet) |
| Chest | String | ~ | Optional parameters, used to replace the armor originally specified in the team configuration(usage: Shoes armorShortName)(example Shoes kskBody) |
| Legs | String | ~ | Optional parameters, used to replace the armor originally specified in the team configuration(usage: Shoes armorShortName)(example Shoes kskPants) |
| Shoes | String | ~ | Optional parameters, used to replace the armor originally specified in the team configuration(usage: Shoes armorShortName)(example Shoes kskBoots) |
