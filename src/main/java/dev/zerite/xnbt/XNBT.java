package dev.zerite.xnbt;

import dev.zerite.xnbt.tag.*;

import java.io.*;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * High performance NBT parsing library
 *
 * @author DJtheRedstoner
 */
public class XNBT {

    public static NBTTag readCompressed(InputStream is) throws IOException {
        return readUncompressed(new GZIPInputStream(is));
    }

    public static NBTTag readUncompressed(InputStream is) throws IOException {
        return readNamedTag(new DataInputStream(is)).tag();
    }

    public static NamedTag readNamedTag(DataInputStream is) throws IOException {
        byte type = is.readByte();

        if (type == TagType.TAG_End) {
            return new NamedTag("", TagEnd.INSTANCE);
        }

        String name = is.readUTF();

        return new NamedTag(name, readTag(is, type));
    }

    public static NBTTag readTag(DataInputStream is, byte type) throws IOException {
        return switch (type) {
            case TagType.TAG_Byte -> TagByte.read(is);
            case TagType.TAG_Short -> TagShort.read(is);
            case TagType.TAG_Int -> TagInt.read(is);
            case TagType.TAG_Long -> TagLong.read(is);
            case TagType.TAG_Float -> TagFloat.read(is);
            case TagType.TAG_Double -> TagDouble.read(is);
            case TagType.TAG_Byte_Array -> TagByteArray.read(is);
            case TagType.TAG_String -> TagString.read(is);
            case TagType.TAG_List -> TagList.read(is);
            case TagType.TAG_Compound -> TagCompound.read(is);
            case TagType.TAG_Int_Array -> TagIntArray.read(is);
            case TagType.TAG_Long_Array -> TagLongArray.read(is);
            default -> throw new IllegalArgumentException();
        };
    }

    public static void writeNamedTag(DataOutput output, String name, NBTTag tag) throws IOException {
        output.write(tag.getType());
        output.writeUTF(name);
        tag.write(output);
    }

}
