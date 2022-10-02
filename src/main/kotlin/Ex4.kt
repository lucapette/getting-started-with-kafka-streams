import com.twitter.Extractor
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder

fun ex4() {
    val streamsBuilder = StreamsBuilder()
    val extractor = Extractor()

    streamsBuilder.stream<String, String>("tweets")
        .flatMapValues { tweet -> extractor.extractHashtags(tweet) }
        .groupBy { _, hashtag -> hashtag }
        .count().toStream()
        .to("tweets.trends.by-topic")

    val kafkaStreams = KafkaStreams(streamsBuilder.build(), streamsConfig("ex4"))

    kafkaStreams.start()
}