/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.common.interfaces.entity.player;

import com.flowpowered.math.vector.Vector3d;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.profile.property.ProfileProperty;
import org.spongepowered.api.scoreboard.Scoreboard;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.common.interfaces.entity.IMixinSkinnable;
import org.spongepowered.common.entity.player.tab.SpongeTabList;
import org.spongepowered.common.interfaces.world.IMixinWorldServer;
import org.spongepowered.common.world.border.PlayerOwnBorderListener;

import java.util.Collection;

import javax.annotation.Nullable;

public interface IMixinEntityPlayerMP extends IMixinEntityPlayer, IMixinSkinnable {

    default boolean usesCustomClient() {
        return false;
    }

    User getUserObject();

    void forceRecreateUser();

    void setVelocityOverride(@Nullable Vector3d velocity);

    void sendBlockChange(BlockPos pos, IBlockState state);

    MessageChannel getDeathMessageChannel();

    void initScoreboard();

    void removeScoreboardOnRespawn();

    void setScoreboardOnRespawn(Scoreboard scoreboard);

    IMixinWorldServer getMixinWorld();

    void refreshXpHealthAndFood();

    void restorePacketItem(EnumHand hand);

    void setPacketItem(ItemStack itemstack);

    void refreshExp();

    PlayerOwnBorderListener getWorldBorderListener();

    void setHealthScale(double scale);

    double getHealthScale();

    float getInternalScaledHealth();

    boolean isHealthScaled();

    void setHealthScaled(boolean scaled);

    void refreshScaledHealth();

    void injectScaledHealth(Collection<IAttributeInstance> set, boolean b);

    void updateDataManagerForScaledHealth();

    boolean hasForcedGamemodeOverridePermission();

    void setContainerDisplay(Text displayName);

    void refreshSkinOnRespawn();

    void refreshSkinOnSpawnToOthers();

    com.mojang.authlib.GameProfile getCustomSkinProfile();

    void onSpawnToPlayers(Collection<Player> players);

    boolean isRespawning();

    void setRespawning(boolean respawning);

    SpongeTabList removeTabList();


}
