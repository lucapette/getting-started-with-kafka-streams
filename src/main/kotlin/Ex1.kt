import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder

fun ex1() {
    val streamsBuilder = StreamsBuilder()

    streamsBuilder
        .stream<String, String>("tweets")
        .mapValues { tweet -> tweet.uppercase() }
        .to("tweets.shouting")

    val kafkaStreams = KafkaStreams(streamsBuilder.build(), streamsConfig("ex1"))

    kafkaStreams.start()
}