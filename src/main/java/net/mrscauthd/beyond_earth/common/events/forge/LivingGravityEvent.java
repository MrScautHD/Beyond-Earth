package net.mrscauthd.beyond_earth.common.events.forge;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;

public class LivingGravityEvent extends LivingEvent {

    private Level level;
    private Attribute attribute;
    private AttributeInstance attributeInstance;

    public LivingGravityEvent(LivingEntity entity, Level level, Attribute attribute, AttributeInstance attributeInstance) {
        super(entity);
        this.level = level;
        this.attribute= attribute;
        this.attributeInstance = attributeInstance;
    }

    public Level getLevel() {
        return level;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public AttributeInstance getAttributeInstance() {
        return attributeInstance;
    }
}
