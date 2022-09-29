import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import java.util.Properties

val examples = mutableMapOf("ex1" to ::ex1)

fun main() {
    examples["ex1"]!!()
}

fun streamsConfig(appId: String): StreamsConfig {
    val props = Properties()
    props[StreamsConfig.APPLICATION_ID_CONFIG] = appId
    props[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
    props[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.StringSerde::class.java
    props[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.StringSerde::class.java

    props[StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG] = 1
    props[StreamsConfig.COMMIT_INTERVAL_MS_CONFIG] = 100

    return StreamsConfig(props)
}