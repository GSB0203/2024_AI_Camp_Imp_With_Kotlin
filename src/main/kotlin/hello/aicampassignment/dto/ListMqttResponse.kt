package hello.aicampassignment.dto

import hello.aicampassignment.model.Mqtt

data class ListMqttResponse(
    val mqtts: List<MqttDto>
) {
    companion object {
        fun of(mqtts1: List<Mqtt>): ListMqttResponse {
            return ListMqttResponse(
                //mqtts = mqtts1.map {
                //    MqttDto.of(it)
                //}
                mqtts = mqtts1.map(MqttDto::of)
            )
        }
    }
}
