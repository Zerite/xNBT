package dev.zerite.xnbt.tag;

import java.io.DataOutput;
import java.io.IOException;

public interface NBTTag {

    int getType();

    void write(DataOutput output) throws IOException;

}
