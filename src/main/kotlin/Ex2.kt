import com.twitter.Extractor
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder

fun ex2() {
    val streamsBuilder = StreamsBuilder()
    val extractor = Extractor()

    streamsBuilder
        .stream<String, String>("tweets")
        .filter { _, v -> extractor.extractMentionedScreennames(v).isEmpty() }
        .mapValues { tweet -> tweet.uppercase() }
        .to("tweets.no-mentions.shouting")

    val kafkaStreams = KafkaStreams(streamsBuilder.build(), streamsConfig("ex2"))

    kafkaStreams.start()
}