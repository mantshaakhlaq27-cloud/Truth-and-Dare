package com.example.truthordareapp

object DataProvider {

    // Easy Truths
    val easyTruths = listOf(
        "What's your favorite color?",
        "What's your favorite food?",
        "Who is your best friend?",
        "What's your favorite movie?",
        "What's your favorite song?",
        "What do you like to do for fun?",
        "What's your favorite animal?",
        "What's your dream job?",
        "What makes you happy?",
        "What's your favorite game?"
    )

    // Medium Truths
    val mediumTruths = listOf(
        "What's your biggest fear?",
        "Who was your first crush?",
        "What's the most embarrassing thing you've done?",
        "Have you ever lied to your best friend?",
        "What's your biggest secret?",
        "What's the worst grade you've ever received?",
        "What's the last lie you told?",
        "What's your most unusual talent?",
        "Who is your secret crush in this group?",
        "What's something you've never told your parents?"
    )

    // Hard Truths
    val hardTruths = listOf(
        "What's your deepest, darkest secret?",
        "Have you ever cheated on someone?",
        "What's the meanest thing you've ever done?",
        "What's your most embarrassing moment ever?",
        "Have you ever stolen anything valuable?",
        "What's something you did that you still feel guilty about?",
        "Who in this room do you trust the least?",
        "What's the biggest lie you've ever told?",
        "Have you ever sabotaged someone's relationship?",
        "What's something illegal you've done?"
    )

    // Easy Dares
    val easyDares = listOf(
        "Sing 'Happy Birthday'",
        "Do 10 jumping jacks",
        "Make a funny face",
        "Dance for 15 seconds",
        "Tell a joke",
        "Imitate your favorite animal",
        "Spin around 5 times",
        "Do a cartwheel or try to",
        "Pat your head and rub your belly",
        "Walk backwards for 30 seconds"
    )

    // Medium Dares
    val mediumDares = listOf(
        "Sing a song in a funny voice!",
        "Dance for 30 seconds!",
        "Do 20 push-ups!",
        "Speak in an accent for the next 2 rounds!",
        "Do your best impression of another player!",
        "Do the worm dance move!",
        "Act like a monkey for 1 minute!",
        "Do 30 jumping jacks!",
        "Say the alphabet backwards!",
        "Moonwalk across the room!"
    )

    // Hard Dares
    val hardDares = listOf(
        "Do 50 push-ups without stopping!",
        "Eat a spoonful of hot sauce!",
        "Let someone tickle you for 1 minute!",
        "Speak only in rhymes for the next 3 rounds!",
        "Do a handstand for 20 seconds!",
        "Let the group give you a new hairstyle!",
        "Eat a raw onion slice!",
        "Hold an ice cube until it melts!",
        "Do 100 jumping jacks!",
        "Prank call someone!"
    )

    fun getTruths(difficulty: String): List<String> {
        return when (difficulty) {
            "Easy" -> easyTruths
            "Medium" -> mediumTruths
            "Hard" -> hardTruths
            else -> mediumTruths
        }
    }

    fun getDares(difficulty: String): List<String> {
        return when (difficulty) {
            "Easy" -> easyDares
            "Medium" -> mediumDares
            "Hard" -> hardDares
            else -> mediumDares
        }
    }
}