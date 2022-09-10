package dev.zerite.xnbt.jmh;

import org.openjdk.jmh.profile.JavaFlightRecorderProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {
        Runner runner = new Runner(new OptionsBuilder()
            .include("WriteBenchmark.*")
            .addProfiler(JavaFlightRecorderProfiler.class)
            .build());

        runner.run();
    }

}
