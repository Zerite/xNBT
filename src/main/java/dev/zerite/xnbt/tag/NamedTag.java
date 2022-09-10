package dev.zerite.xnbt.tag;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NamedTag {

    @NotNull
    private final String name;

    @NotNull
    private final NBTTag tag;

    public NamedTag(@NotNull String name, @NotNull NBTTag tag) {
        this.name = name;
        this.tag = tag;
    }

    public String name() {
        return name;
    }

    public NBTTag tag() {
        return tag;
    }

    @Override
    public String toString() {
        return "NamedTag('" + name + "'): " + tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamedTag namedTag = (NamedTag) o;
        return name.equals(namedTag.name) && tag.equals(namedTag.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tag);
    }
}
