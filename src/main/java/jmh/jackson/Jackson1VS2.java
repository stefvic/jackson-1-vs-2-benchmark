package jmh.jackson;

import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.map.ObjectMapper;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

/**
 * @author erabii
 */
@BenchmarkMode(value = { Mode.AverageTime })
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
public class Jackson1VS2 {

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(Jackson1VS2.class.getSimpleName())
				.jvmArgs("-Xms10000m", "-Xmx10000m").shouldFailOnError(true).build();
		new Runner(opt).run();
	}

	private static ObjectMapper jackson1 = new ObjectMapper();

	private static com.fasterxml.jackson.databind.ObjectMapper jackson2 = new com.fasterxml.jackson.databind.ObjectMapper();

	private static com.fasterxml.jackson.databind.ObjectMapper jackson2AfterBurner = new com.fasterxml.jackson.databind.ObjectMapper();

	static {
		jackson2AfterBurner.registerModule(new AfterburnerModule());
	}

	@Fork(1)
	@Benchmark
	public Object jackson1Ser(InputSer input) throws Exception {
		return jackson1.writeValueAsString(input.users);
	}

	@Fork(1)
	@Benchmark
	public Object jackson2Ser(InputSer input) throws Exception {
		return jackson2.writeValueAsString(input.users);
	}

	@Fork(1)
	@Benchmark
	public Object jackson2SerAfterBurner(InputSer input) throws Exception {
		return jackson2AfterBurner.writeValueAsString(input.users);
	}
	
	@Fork(1)
	@Benchmark
	public Object jackson1Deser(InputDeser input) throws Exception {
		return jackson1.readValue(input.usersJson, input.jackson1TypeRef);
	}

	@Fork(1)
	@Benchmark
	public Object jackson2Deser(InputDeser input) throws Exception {
		return jackson2.readValue(input.usersJson, input.jackson2TypeRef);
	}

	@Fork(1)
	@Benchmark
	public Object jackson2DeserAfterBurner(InputDeser input) throws Exception {
		return jackson2AfterBurner.readValue(input.usersJson, input.jackson2TypeRef);
	}

}
