package ru.otus.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.config.JobConfig;
import ru.otus.homework.service.LibraryService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBatchTest
public class ImportLibraryJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private LibraryService libraryService;

    @BeforeEach
    public void setUp() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    public void testJob() throws Exception {
        final Job job = jobLauncherTestUtils.getJob();
        assertThat(job).isNotNull()
                .extracting(Job::getName)
                .isEqualTo(JobConfig.MIGRATE_LIBRARY_JOB);

        final JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertThat(jobExecution.getExitStatus()).isEqualByComparingTo(ExitStatus.COMPLETED);
        assertThat(libraryService.getAll()).hasSize(1);
    }
}
