package hello.aicampassignment.controller

import hello.aicampassignment.config.MqttConfig
import hello.aicampassignment.config.MqttConfig.Companion.SUB_CLIENT_ID
import hello.aicampassignment.dto.ListMqttResponse
import hello.aicampassignment.dto.MqttRequest
import hello.aicampassignment.model.Mqtt
import hello.aicampassignment.service.MqttService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/mqtt")
class MqttController (
    private val mqttService: MqttService,
    private val mqttGateway: MqttService.MqttGateway
) {

    @GetMapping("/ping")
    fun ping() {
        mqttGateway.sendToMqtt("Hello World!")
    }

    @PostMapping("/publish")
    fun publishToMqtt(
        @RequestBody
        request: MqttRequest
    ):Mqtt {
        val mqtt = Mqtt(
            clientId = SUB_CLIENT_ID,
            message = request.message,
            createdAt = LocalDateTime.now()
        )
        mqttGateway.sendToMqtt("[${SUB_CLIENT_ID}]: ${request.message}")
        return mqttService.save(mqtt)
    }

    @GetMapping("/api/messages")
    fun listByClientId(
        @RequestParam clientId: String,
    ) = ListMqttResponse.of(mqttService.findByClientId(clientId))
}