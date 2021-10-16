package com.onlinejudge.javasandbox.JavaWorker;

import com.onlinejudge.javasandbox.models.Submission;
import lombok.Data;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Data
@Service
//@PropertySource("classpath:config-${spring.profiles.active}.properties")
public class Worker {
    @Value("asadf")
    private String fileName;

    @Value("javac")
    private String javac;

    @Value("java")
    private String java;

    @Value("1024")
    private Integer outputMaxLength;

    @Value("1000")
    private Integer watchdogTimeout;

    // empty policy file path
    private String policyFile;

    public void save(String cwd, Submission submission) throws IOException {
        // save code
        Files.write(Paths.get(cwd + File.separator + fileName + ".java"), submission.getCode().getBytes());
        // save empty policy file under the same directory
//        policyFile = cwd + File.separator + "policy";
//        Files.createFile(Paths.get(policyFile));
    }

    public void compile(String cwd, Submission submission) throws RuntimeException {
        ByteArrayOutputStream stderr = new ByteArrayOutputStream();
        ExecuteWatchdog watchdog = new ExecuteWatchdog(watchdogTimeout);

        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(cwd));
        executor.setStreamHandler(new PumpStreamHandler(null, stderr, null));
        executor.setWatchdog(watchdog);

        CommandLine cmd = new CommandLine(javac);
        cmd.addArgument("-J-Duser.language=en");  // force using English
        cmd.addArgument("-classpath");
        cmd.addArgument(cwd);
        cmd.addArgument(fileName + ".java");

        try {
            executor.execute(cmd);
        } catch (IOException e) {
            if (watchdog.killedProcess()) {
                submission.setVerdict(Submission.STATUS_CE);
//                submission.setError("Compile Time Exceeded");
            } else {
                submission.setVerdict(Submission.STATUS_CE);
//                submission.setError("Compile error");
            }
            throw new RuntimeException("Compile Aborted.");
        }

    }
//
//    public void run(String cwd, Problem problem, List<TestCase> testCases, Submission submission) throws RuntimeException {
//        CommandLine cmd = new CommandLine(java);
//        cmd.addArgument("-Djava.security.manager");
//        cmd.addArgument("-Djava.security.policy==" + policyFile);
//        cmd.addArgument("-Xmx" + problem.getMemoryLimit() + "m");
//        cmd.addArgument("-classpath");
//        cmd.addArgument(cwd);
//        cmd.addArgument(fileName);
//        logger.info("Sandbox cmd:\t" + cmd.toString());
//
//        long cost = 0;
//        for (TestCase testCase : testCases) {
//            ByteArrayInputStream stdin = new ByteArrayInputStream(testCase.getInput().getBytes());
//            ByteArrayOutputStream stdout = new ByteArrayOutputStream(), stderr = new ByteArrayOutputStream();
//            ExecuteWatchdog watchdog = new ExecuteWatchdog(problem.getRuntimeLimit());
//
//            DefaultExecutor executor = new DefaultExecutor();
//            executor.setWorkingDirectory(new File(cwd));
//            executor.setStreamHandler(new PumpStreamHandler(stdout, stderr, stdin));
//            executor.setWatchdog(watchdog);
//
//            long startTime = System.nanoTime();
//            try {
//                executor.execute(cmd);
//            } catch (IOException e) {
//                if (watchdog.killedProcess()) {
//                    submission.setStatus(Submission.STATUS_TLE);
//                    submission.setError("Time Limit Exceeded");
//                } else {
//                    submission.setStatus(Submission.STATUS_RE);
//                    submission.setError(stderr.toString());
//                }
//                logger.warn("Runtime error:\t" + e.toString());
//                throw new RuntimeException("Execution Aborted.");
//            }
//            cost += System.nanoTime() - startTime;
//
//            String o = stdout.toString().trim();
//            if (!o.equals(testCase.getOutput())) {
//                submission.setStatus(Submission.STATUS_WA);
//                submission.setInput(testCase.getInput());
//                submission.setOutput(o.length() > outputMaxLength ? o.substring(0, outputMaxLength) + "..." : o);
//                submission.setExpected(testCase.getOutput());
//                logger.warn("Input: " + testCase.getInput());
//                logger.warn("Output: " + o.substring(0, Math.min(outputMaxLength, o.length())));
//                logger.warn("Expected: " + testCase.getOutput());
//                throw new RuntimeException("Wrong Answer.");
//            }
//        }
//
//        submission.setRuntime(cost / 1000000);
//        submission.setMemory(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() / (1024 * 1024));
//        submission.setStatus(Submission.STATUS_AC);
//    }
}