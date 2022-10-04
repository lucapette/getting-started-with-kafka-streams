import com.twitter.Extractor
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder

fun ex5() {
    val streamsBuilder = StreamsBuilder()
    val extractor = Extractor()

    val descriptions = streamsBuilder.table<String, String>("hashtags")

    val hashtagsCount = streamsBuilder.stream<String, String>("tweets")
        .flatMapValues { tweet -> extractor.extractHashtags(tweet) }
        .groupBy { _, hashtag -> hashtag }
        .count()

    hashtagsCount.join(descriptions) { count, description ->
        "$description - $count"
    }.toStream().to("tweets.trends.by-topic-description")

    val kafkaStreams = KafkaStreams(streamsBuilder.build(), streamsConfig("ex5"))

    kafkaStreams.start()
}