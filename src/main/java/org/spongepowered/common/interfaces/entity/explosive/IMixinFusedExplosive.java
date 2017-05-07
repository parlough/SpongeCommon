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
package org.spongepowered.common.interfaces.entity.explosive;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.explosive.FusedExplosive;
import org.spongepowered.api.event.SpongeEventFactory;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.NamedCause;
import org.spongepowered.api.event.entity.explosive.DefuseExplosiveEvent;
import org.spongepowered.api.event.entity.explosive.PrimeExplosiveEvent;
import org.spongepowered.common.event.ShouldFire;

import javax.annotation.Nullable;

public interface IMixinFusedExplosive extends IMixinExplosive {

    int getFuseDuration();

    void setFuseDuration(int fuseTicks);

    int getFuseTicksRemaining();

    void setFuseTicksRemaining(int fuseTicks);

    default boolean shouldPrime(@Nullable Cause cause) {
        if (ShouldFire.PRIME_EXPLOSIVE_EVENT_PRE) {
            PrimeExplosiveEvent.Pre event = SpongeEventFactory.createPrimeExplosiveEventPre(
                    cause != null ? cause : Cause.of(NamedCause.source(this)), (FusedExplosive) this);
            return !Sponge.getEventManager().post(event);
        }
        return true;
    }

    default void postPrime(@Nullable Cause cause) {
        if (ShouldFire.PRIME_EXPLOSIVE_EVENT_POST) {
            PrimeExplosiveEvent.Post event = SpongeEventFactory
                    .createPrimeExplosiveEventPost(cause != null ? cause: Cause.of(NamedCause.source(this)), (FusedExplosive) this);
            Sponge.getEventManager().post(event);
        }
    }

    default boolean shouldDefuse(@Nullable Cause cause) {
        if (ShouldFire.DEFUSE_EXPLOSIVE_EVENT_PRE) {
            DefuseExplosiveEvent.Pre event = SpongeEventFactory.createDefuseExplosiveEventPre(
                    cause != null ? cause : Cause.of(NamedCause.source(this)), (FusedExplosive) this);
            return !Sponge.getEventManager().post(event);
        }
        return true;
    }

    default void postDefuse(@Nullable Cause cause) {
        if (ShouldFire.DEFUSE_EXPLOSIVE_EVENT_POST) {
            DefuseExplosiveEvent.Post event = SpongeEventFactory.createDefuseExplosiveEventPost(
                    cause != null ? cause : Cause.of(NamedCause.source(this)), (FusedExplosive) this);
            Sponge.getEventManager().post(event);
        }
    }

}
