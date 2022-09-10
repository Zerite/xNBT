package dev.zerite.xnbt.tag;

public class NamedTag {

    private final String name;

    private final NBTTag tag;

    public NamedTag(String name, NBTTag tag) {
        this.name = name;
        this.tag = tag;
    }

    public String name() {
        return name;
    }

    public NBTTag tag() {
        return tag;
    }

    // todo: equals & hashcode

}
