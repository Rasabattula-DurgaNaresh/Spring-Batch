package com.github.daggerok.metrics.app

import mu.KLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
class PaymentsReportResource(
    private val paymentsReportRepository: PaymentsReportRepository,
    private val asyncLauncher: JobLauncher,
    private val paymentsReportJob: Job,
) {

    @GetMapping("/api")
    fun getReports(): MutableList<PaymentsReport> =
        paymentsReportRepository.findAll()

    @PostMapping("/api/launch-payments-report")
    fun launchUsersPaymentsReport() =
        JobParametersBuilder()
            .addString("dateTime", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
            .toJobParameters()
            .let { asyncLauncher.run(paymentsReportJob, it) }
            .also { logger.info { "execute job ${it.jobId} with parameters ${it.jobParameters.toProperties()}" } }
            .run { jobId }

    private companion object : KLogging()
}
