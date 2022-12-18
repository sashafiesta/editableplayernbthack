package com.defthedev.editableplayernbthack.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.UUID;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.commands.data.EntityDataAccessor;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityDataAccessor.class)
public class MixinEntityDataAccessor {
    @Final
    @Shadow
    private Entity entity;
    /**
     * @author A
     */
    @Inject(at = @At(value = "HEAD"), method = "setData", cancellable = true)
    public void setDataInject(CompoundTag p_139519_, CallbackInfo ci) throws CommandSyntaxException {
        UUID uuid = this.entity.getUUID();
        this.entity.load(p_139519_);
        this.entity.setUUID(uuid);
        ci.cancel();
    }
}