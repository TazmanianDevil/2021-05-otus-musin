package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static ru.otus.homework.config.JobConfig.MIGRATE_LIBRARY_JOB;

@ShellComponent
@RequiredArgsConstructor
public class BatchCommands {

    private final Job importUserJob;

    private final JobLauncher jobLauncher;
    private final JobOperator jobOperator;
    private final JobExplorer jobExplorer;

    //http://localhost:8080/h2-console/

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "sm-jl")
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution execution = jobLauncher.run(importUserJob, new JobParametersBuilder()
                .toJobParameters());
        System.out.println(execution);
    }

    @ShellMethod(value = "startMigrationJobWithJobOperator", key = "sm-jo")
    public void startMigrationJobWithJobOperator() throws Exception {
        Long executionId = jobOperator.start(MIGRATE_LIBRARY_JOB, "time, " + System.currentTimeMillis());
        System.out.println(jobOperator.getSummary(executionId));
    }

    @ShellMethod(value = "restart ", key = "r")
    public void restartMigrationJobWithJobOperator() throws Exception {
        JobInstance instance = jobExplorer.getLastJobInstance(MIGRATE_LIBRARY_JOB);
        if (instance != null) {
            Long executionId = jobOperator.restart(instance.getInstanceId());
            System.out.println(jobOperator.getSummary(executionId));
        }
    }

    @ShellMethod(value = "showInfo", key = "i")
    public void showInfo() {
        System.out.println(jobExplorer.getJobNames());
        System.out.println(jobExplorer.getLastJobInstance(MIGRATE_LIBRARY_JOB));
    }
}
