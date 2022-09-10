package dev.zerite.xnbt.jmh;

import dev.zerite.xnbt.XNBT;
import org.jglrxavpok.hephaistos.nbt.CompressedProcesser;
import org.jglrxavpok.hephaistos.nbt.NBTException;
import org.jglrxavpok.hephaistos.nbt.NBTReader;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Fork(value = 1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
public class XNBTBenchmark {

    @State(Scope.Benchmark)
    public static class TestData {
        byte[] helloWorld;
        byte[] bigTest;

        @Setup
        public void readData() throws IOException {
            helloWorld = Files.readAllBytes(Path.of("test/hello_world.nbt"));
            bigTest = Files.readAllBytes(Path.of("test/bigtest_raw.nbt"));
        }
    }

    //@Benchmark
    //public void helloWorldXNBT(TestData data, Blackhole b) throws IOException {
    //    b.consume(XNBT.readUncompressed(new ByteArrayInputStream(data.helloWorld)));
    //}
    //
    //@Benchmark
    //public void helloWorldHephaistos(TestData data, Blackhole b) throws Exception {
    //    b.consume(new NBTReader(data.helloWorld).read());
    //}
    //
    @Benchmark
    public void bigTestXNBT(TestData data, Blackhole b) throws IOException {
        b.consume(XNBT.readUncompressed(new ByteArrayInputStream(data.bigTest)));
    }
    //
    @Benchmark
    public void bigTestHephaistos(TestData data, Blackhole b) throws Exception {
        b.consume(new NBTReader(data.bigTest).read());
    }

    //@Benchmark
    //public void longArrayXNBT(TestData data, Blackhole b) throws IOException {
    //    b.consume(XNBT.readUncompressed(new ByteArrayInputStream(data.longArray)));
    //}

    //@Benchmark
    //public void longArrayHephaistos(TestData data, Blackhole b) throws Exception {
    //    b.consume(new NBTReader(data.longArray).read());
    //}

}
