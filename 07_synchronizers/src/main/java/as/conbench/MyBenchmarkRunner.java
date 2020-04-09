package as.conbench;

public class MyBenchmarkRunner implements BenchmarkRunner {

    public MyBenchmarkRunner() {
    }


    @Override
    public void runBenchmark(BenchmarkDescriptor desc) throws InstantiationException, IllegalAccessException {
        System.out.println(desc.nTimes);
        System.out.println(desc.testClass);
        System.out.println(desc.testMethods);


        this.warmup(desc);
        this.runCombinations(desc);

    }

    private void runCombinations(BenchmarkDescriptor desc) {
    }


    private void warmup(BenchmarkDescriptor desc) throws IllegalAccessException, InstantiationException {
        desc.testClass.newInstance();
    }
    
    
}
