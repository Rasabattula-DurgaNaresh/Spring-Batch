package com.github.daggerok.payment.client

import com.github.daggerok.payment.api.PaymentDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
    name = "payment-client",
    url = "\${payment-service.url}",
)
interface PaymentClient {

    @GetMapping("/api/payments")
    fun getPayments(): List<PaymentDTO>
}
