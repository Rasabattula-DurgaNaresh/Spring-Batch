package org.springframework.batch;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService {

    private final JobExplorer jobExplorer;

    public BatchService(JobExplorer jobExplorer) {
        this.jobExplorer = jobExplorer;
    }

    @Tool(description = "Get Spring Batch job names")
    public List<String> getJobNames() {
        return jobExplorer.getJobNames();
    }

    @Tool(description = "Get the status of the last Spring Batch job execution for a given job name")
    public String getJobExecutionStatus(@ToolParam(description = "The job name (eg job1)") String jobName) {
        JobInstance lastJobInstance = jobExplorer.getLastJobInstance(jobName);
        return jobExplorer.getLastJobExecution(lastJobInstance).getStatus().toString();
    }

}
