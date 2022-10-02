import com.twitter.Extractor

fun ex3() {
    // this code doesn't do anything with streams, just an example part of the article on the website
    val tweets = listOf(
        "incoming tweet #example #kafka",
        "another tweet with an #example hashtag",
        "#kafka is amazing",
    )
    val extractor = Extractor()

    val countByHashtag = mutableMapOf<String, Long>()

    tweets.flatMap { tweet ->
        extractor.extractHashtags(tweet)
    }.forEach { hashtag -> countByHashtag[hashtag] = countByHashtag.getOrDefault(hashtag, 0) + 1 }

    println(countByHashtag)
}