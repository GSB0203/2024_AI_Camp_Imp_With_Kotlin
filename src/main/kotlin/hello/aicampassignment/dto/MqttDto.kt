package hello.aicampassignment.dto

import hello.aicampassignment.model.Mqtt
import java.time.LocalDateTime

data class MqttDto(
    var clientId: String,
    val message: String,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun of(mqtt: Mqtt): MqttDto {
            return MqttDto(
                clientId = mqtt.clientId,
                message = mqtt.message,
                createdAt = mqtt.createdAt
            )
        }
    }
}