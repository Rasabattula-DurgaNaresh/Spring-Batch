Spring Batch Partitioning example
----------------------------
In Spring Batch, “Partitioning” is “multiple threads to process a range of data each”. For example, assume you have 100 records in a table, which has “primary id” assigned from 1 to 100, and you want to process the entire 100 records.

Normally, the process starts from 1 to 100, a single thread example. The process is estimated to take 10 minutes to finish.

Bash
Single Thread - Process from 1 to 100
In “Partitioning”, we can start 10 threads to process 10 records each (based on the range of ‘id’). Now, the process may take only 1 minute to finish.

Bash
Thread 1 - Process from 1 to 10
Thread 2 - Process from 11 to 20
Thread 3 - Process from 21 to 30
......
Thread 9 - Process from 81 to 90
Thread 10 - Process from 91 to 100
To implement “Partitioning” technique, you must understand the structure of the input data to process, so that you can plan the “range of data” properly.

1. Tutorial
In this tutorial, we will show you how to create a “Partitioner” job, which has 10 threads, each thread will read records from the database, based on the provided range of ‘id’.

Tools and libraries used
---------------------
- Maven 3
- Eclipse 4.2 or STS
- JDK 1.8
- Spring Core 4.2.4.RELEASE
- Spring Batch 3.0.6.RELEASE
- MySQL Java Driver 5.1.38
P.S Assume “users” table has 100 records.


First, create a Partitioner implementation, puts the “partitioning range” into the ExecutionContext. Later, you will declare the same fromId and tied in the batch-job XML file.

In this case, the partitioning range is look like the following :

Bash
Thread 1 = 1 - 10
Thread 2 = 11 - 20
Thread 3 = 21 - 30
......
Thread 10 = 91 - 100


Batch Jobs
----------------

Review the batch job XML file, it should be self-explanatory. Few points to highlight :

For partitioner, grid-size = number of threads.
For pagingItemReader bean, a jdbc reader example, the #{stepExecutionContext[fromId, toId]} values will be injected by the ExecutionContext in rangePartitioner.
For itemProcessor bean, the #{stepExecutionContext[name]} values will be injected by the ExecutionContext in rangePartitioner.
For writers, each thread will output the records in a different csv files, with filename format - users.processed[fromId]}-[toId].csv.