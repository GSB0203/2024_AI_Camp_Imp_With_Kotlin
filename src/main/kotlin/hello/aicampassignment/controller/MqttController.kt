package hello.aicampassignment.controller

import hello.aicampassignment.dto.MqttRequest
import hello.aicampassignment.service.MqttService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mqtt")
class MqttController (
    private val mqttGateway: MqttService.MqttGateway
) {

    @GetMapping("/ping")
    fun ping() {
        mqttGateway.sendToMqtt("Hello World!")
    }

    @PostMapping("/publish")
    fun proxy(
        @RequestBody
        request: MqttRequest
    ) {
        mqttGateway.sendToMqtt(request.message)
    }
}