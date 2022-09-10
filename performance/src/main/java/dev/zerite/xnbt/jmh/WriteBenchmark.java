package dev.zerite.xnbt.jmh;

import dev.zerite.xnbt.XNBT;
import dev.zerite.xnbt.tag.NBTTag;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTReader;
import org.jglrxavpok.hephaistos.nbt.NBTWriter;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Fork(value = 1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
public class WriteBenchmark {

    @State(Scope.Benchmark)
    public static class TestData {
        NBTTag xNbtData;
        NBT hephaistosData;

        @Setup
        public void readData() throws Exception {
            byte[] bigTest = Files.readAllBytes(Path.of("test/bigtest_raw.nbt"));
            xNbtData = XNBT.readUncompressed(new ByteArrayInputStream(bigTest));
            hephaistosData = new NBTReader(bigTest).read();
        }
    }

    @Benchmark
    public void xNbtWrite(TestData data, Blackhole b) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XNBT.writeNamedTag(new DataOutputStream(baos), "Level", data.xNbtData);
        b.consume(baos);
    }

    @Benchmark
    public void hephaistosWrite(TestData data, Blackhole b) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new NBTWriter(baos).writeNamed("Level", data.hephaistosData);
        b.consume(baos);
    }

}
