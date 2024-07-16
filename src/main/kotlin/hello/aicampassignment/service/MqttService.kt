package hello.aicampassignment.service

import hello.aicampassignment.model.Mqtt
import hello.aicampassignment.model.Url
import org.springframework.integration.annotation.MessagingGateway
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class MqttService(
    val mqttRepository: MqttRepository
) {

    fun save(mqtt: Mqtt): Mqtt = mqttRepository.save(mqtt)

    fun findByClientId(clientId: String): List<Mqtt>
        = mqttRepository.findByClientId(clientId)

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    interface MqttGateway {
        fun sendToMqtt(@Payload data: String)
    }
}