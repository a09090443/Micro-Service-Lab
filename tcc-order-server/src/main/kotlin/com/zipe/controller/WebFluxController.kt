package com.zipe.controller

import org.springframework.http.MediaType
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.concurrent.ThreadLocalRandom
import java.util.function.Function


@RestController
@RequestMapping(value = ["/"])
class WebFluxController {

    @GetMapping(value = ["/flux"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun flux(): Any? {
        return Flux.interval(Duration.ofSeconds(1)) // 1
            .map<Any>(
                Function<Long, Any> { seq: Long ->
                    ServerSentEvent.builder<Int?>() // 2
                        .event("random")
                        .id(seq.toString())
                        .data(ThreadLocalRandom.current().nextInt())
                        .build()
                }
            )
    }
}
