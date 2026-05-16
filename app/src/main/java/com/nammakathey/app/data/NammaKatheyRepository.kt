package com.nammakathey.app.data

import com.nammakathey.app.domain.*

class NammaKatheyRepository {
    val districts: List<District> = listOf(
        District("bengaluru", LocalText("ಬೆಂಗಳೂರು", "Bengaluru"), "South", listOf("kempegowda")),
        District("belagavi", LocalText("ಬೆಳಗಾವಿ", "Belagavi"), "North", listOf("kittur-chennamma")),
        District("chitradurga", LocalText("ಚಿತ್ರದುರ್ಗ", "Chitradurga"), "Central", listOf("onake-obavva")),
        District("shivamogga", LocalText("ಶಿವಮೊಗ್ಗ", "Shivamogga"), "Malnad", listOf("keladi-chennamma")),
        District("mysuru", LocalText("ಮೈಸೂರು", "Mysuru"), "South", listOf("m-visvesvaraya")),
        District("kodagu", LocalText("ಕೊಡಗು", "Kodagu"), "Malnad", listOf("field-marshal-cariappa")),
        District("bagalkote", LocalText("ಬಾಗಲಕೋಟೆ", "Bagalkote"), "North", listOf("pulakeshi-ii")),
        District("vijayapura", LocalText("ವಿಜಯಪುರ", "Vijayapura"), "North", listOf("basavanna")),
        District("dharwad", LocalText("ಧಾರವಾಡ", "Dharwad"), "North", listOf("da-ra-bendre")),
        District("hassan", LocalText("ಹಾಸನ", "Hassan"), "South", listOf("gorur-ramaswamy"))
    )

    val heroes: List<Hero> = listOf(
        Hero(
            id = "kempegowda",
            districtId = "bengaluru",
            name = LocalText("ನಾಡಪ್ರಭು ಕೆಂಪೇಗೌಡ", "Nadaprabhu Kempegowda"),
            title = LocalText("ಬೆಂಗಳೂರು ನಿರ್ಮಾತೃ", "Founder of Bengaluru"),
            years = "1510-1569",
            imageUrl = "android.resource://com.nammakathey.app/drawable/kempegowda",
            summary = LocalText("ಬೆಂಗಳೂರು ನಗರವನ್ನು ಯೋಜಿಸಿದ ಮತ್ತು ನಿರ್ಮಿಸಿದ ಮಹಾನ್ ನಾಯಕ.", "The visionary who planned and built the city of Bengaluru."),
            interestingFacts = listOf(
                LocalText("ಅವರು ನಗರದ ನಾಲ್ಕು ದಿಕ್ಕುಗಳಲ್ಲಿ ನಾಲ್ಕು ಗೋಪುರಗಳನ್ನು ನಿರ್ಮಿಸಿದರು.", "He built four towers to mark the boundaries of the city."),
                LocalText("ಬೆಂಗಳೂರಿನಲ್ಲಿ ಅನೇಕ ಕೆರೆಗಳನ್ನು ನಿರ್ಮಿಸಿದ ಕೀರ್ತಿ ಅವರಿಗೆ ಸಲ್ಲುತ್ತದೆ.", "He is credited with building many lakes in Bengaluru.")
            ),
            pages = listOf(
                StoryPage(LocalText("ನಗರದ ಕನಸು", "A Vision for a City"), LocalText("ಕೆಂಪೇಗೌಡರು ಹಸಿರು ಮತ್ತು ಸಮೃದ್ಧ ನಗರದ ಕನಸು ಕಂಡರು.", "Kempegowda dreamed of a city that was green and prosperous.")),
                StoryPage(LocalText("ಪೇಟೆಗಳ ನಿರ್ಮಾಣ", "Building the Markets"), LocalText("ಅವರು ವ್ಯಾಪಾರಕ್ಕಾಗಿ ವಿವಿಧ ಪೇಟೆಗಳನ್ನು (ಮಾರುಕಟ್ಟೆಗಳನ್ನು) ಸ್ಥಾಪಿಸಿದರು.", "He established various 'Petes' (markets) for different trades."))
            ),
            quiz = listOf(
                Question(LocalText("ಕೆಂಪೇಗೌಡರು ಯಾವ ನಗರವನ್ನು ನಿರ್ಮಿಸಿದರು?", "Which city did Kempegowda build?"), listOf(LocalText("ಮೈಸೂರು", "Mysuru"), LocalText("ಬೆಂಗಳೂರು", "Bengaluru"), LocalText("ಹುಬ್ಬಳ್ಳಿ", "Hubballi")), 1, LocalText("ಬೆಂಗಳೂರು ಸರಿಯಾದ ಉತ್ತರ.", "Bengaluru is the correct answer.")),
                Question(LocalText("ನಗರದ ಗಡಿಯನ್ನು ಗುರುತಿಸಲು ಅವರು ಏನು ನಿರ್ಮಿಸಿದರು?", "What did he build to mark city boundaries?"), listOf(LocalText("ಗೋಪುರಗಳು", "Towers"), LocalText("ಕೋಟೆಗಳು", "Forts"), LocalText("ಶಾಲೆಗಳು", "Schools")), 0, LocalText("ಅವರು ನಾಲ್ಕು ಗೋಪುರಗಳನ್ನು ನಿರ್ಮಿಸಿದರು.", "He built four towers.")),
                Question(LocalText("ಬೆಂಗಳೂರು ಯಾವ ವರ್ಷದಲ್ಲಿ ಸ್ಥಾಪನೆಯಾಯಿತು?", "In which year was Bengaluru founded?"), listOf(LocalText("1537", "1537"), LocalText("1947", "1947"), LocalText("1800", "1800")), 0, LocalText("1537 ರಲ್ಲಿ ಬೆಂಗಳೂರು ಸ್ಥಾಪನೆಯಾಯಿತು.", "Bengaluru was founded in 1537."))
            ),
            badge = Badge("badge-kempegowda", LocalText("ನಗರ ನಿರ್ಮಾತೃ", "City Builder"), LocalText("ನೀವು ಬೆಂಗಳೂರಿನ ಇತಿಹಾಸವನ್ನು ಕಲಿತಿದ್ದೀರಿ!", "You learned about the history of Bengaluru!")),
            memorial = Memorial(LocalText("ಕೆಂಪೇಗೌಡ ಪ್ರತಿಮೆ, ಬಿಬಿಎಂಪಿ", "Kempegowda Statue, BBMP"), "Bengaluru Central")
        ),
        Hero(
            id = "kittur-chennamma",
            districtId = "belagavi",
            name = LocalText("ಕಿತ್ತೂರು ಚೆನ್ನಮ್ಮ", "Kittur Chennamma"),
            title = LocalText("ಬೆಳಗಾವಿಯ ವೀರರಾಣಿ", "The Brave Queen of Belagavi"),
            years = "1778-1829",
            imageUrl = "android.resource://com.nammakathey.app/drawable/kittur",
            summary = LocalText("ಬ್ರಿಟಿಷರ ವಿರುದ್ಧ ಹೋರಾಡಿದ ಮೊದಲ ಮಹಿಳಾ ಹೋರಾಟಗಾರ್ತಿ.", "One of the first female rulers to lead an armed rebellion against British rule."),
            interestingFacts = listOf(
                LocalText("ಅವರು ಝಾನ್ಸಿ ರಾಣಿ ಲಕ್ಷ್ಮೀಬಾಯಿಗಿಂತ ಮೊದಲೇ ಬ್ರಿಟಿಷರ ವಿರುದ್ಧ ಹೋರಾಡಿದರು.", "She fought the British even before Rani Laxmibai of Jhansi."),
                LocalText("ಅವರ ದತ್ತು ಪುತ್ರನ ಹಕ್ಕಿಗಾಗಿ ಅವರು ಹೋರಾಟ ನಡೆಸಿದರು.", "She fought for the rights of her adopted son to the throne.")
            ),
            pages = listOf(
                StoryPage(LocalText("ಕಿತ್ತೂರಿನ ರಕ್ಷಣೆ", "Defending Kittur"), LocalText("ಚೆನ್ನಮ್ಮ ಬ್ರಿಟಿಷರ 'ದತ್ತು ಮಕ್ಕಳಿಗೆ ಹಕ್ಕಿಲ್ಲ' ನೀತಿಯನ್ನು ವಿರೋಧಿಸಿದರು.", "Chennamma opposed the British 'Doctrine of Lapse' policy.")),
                StoryPage(LocalText("ಯುದ್ಧದ ಮೈದಾನ", "Battlefield Courage"), LocalText("ಅವರು ಕಿತ್ತೂರು ಕೋಟೆಯನ್ನು ಉಳಿಸಲು ವೀರಾವೇಶದಿಂದ ಹೋರಾಡಿದರು.", "She fought gallantly to save the Kittur Fort."))
            ),
            quiz = listOf(
                Question(LocalText("ಚೆನ್ನಮ್ಮ ಯಾವ ರಾಜ್ಯದ ರಾಣಿ?", "Chennamma was the queen of which state?"), listOf(LocalText("ಕಿತ್ತೂರು", "Kittur"), LocalText("ಮೈಸೂರು", "Mysuru"), LocalText("ಕೊಡಗು", "Kodagu")), 0, LocalText("ಅವರು ಕಿತ್ತೂರಿನ ರಾಣಿ.", "She was the queen of Kittur.")),
                Question(LocalText("ಅವರು ಯಾರ ವಿರುದ್ಧ ಹೋರಾಡಿದರು?", "Whom did she fight against?"), listOf(LocalText("ಬ್ರಿಟಿಷರು", "British"), LocalText("ಮೊಘಲರು", "Mughals"), LocalText("ಪೋರ್ಚುಗೀಸರು", "Portuguese")), 0, LocalText("ಅವರು ಬ್ರಿಟಿಷರ ವಿರುದ್ಧ ಹೋರಾಡಿದರು.", "She fought against the British.")),
                Question(LocalText("ಕಿತ್ತೂರು ಯಾವ ಜಿಲ್ಲೆಯಲ್ಲಿದೆ?", "Kittur is in which district?"), listOf(LocalText("ಧಾರವಾಡ", "Dharwad"), LocalText("ಬೆಳಗಾವಿ", "Belagavi"), LocalText("ಗದಗ", "Gadag")), 1, LocalText("ಕಿತ್ತೂರು ಬೆಳಗಾವಿ ಜಿಲ್ಲೆಯಲ್ಲಿದೆ.", "Kittur is in Belagavi district."))
            ),
            badge = Badge("badge-chennamma", LocalText("ಸ್ವಾತಂತ್ರ್ಯ ಜ್ಯೋತಿ", "Flame of Freedom"), LocalText("ನೀವು ಕಿತ್ತೂರಿನ ವೀರಗಾಥೆಯನ್ನು ಕಲಿತಿದ್ದೀರಿ!", "You learned the heroic saga of Kittur!")),
            memorial = Memorial(LocalText("ಕಿತ್ತೂರು ಕೋಟೆ", "Kittur Fort"), "Belagavi District")
        ),
        Hero(
            id = "onake-obavva",
            districtId = "chitradurga",
            name = LocalText("ಒನಕೆ ಓಬವ್ವ", "Onake Obavva"),
            title = LocalText("ಕೋಟೆಯ ರಕ್ಷಕಿ", "Protector of the Fort"),
            years = "18th Century",
            imageUrl = "android.resource://com.nammakathey.app/drawable/obbava",
            summary = LocalText("ಒನಕೆಯನ್ನು ಹಿಡಿದು ಶತ್ರುಗಳನ್ನು ಸದೆಬಡಿದ ವೀರ ಮಹಿಳೆ.", "A woman who fought enemies with a pestle to save the fort."),
            interestingFacts = listOf(
                LocalText("ಅವರು ಚಿತ್ರದುರ್ಗದ ಕೋಟೆಯಲ್ಲಿ ಕಾವಲುಗಾರನ ಪತ್ನಿಯಾಗಿದ್ದರು.", "She was the wife of a guard at the Chitradurga Fort."),
                LocalText("ಅವರು ಶತ್ರುಗಳು ನುಸುಳುತ್ತಿದ್ದ ಕಿಂಡಿಯನ್ನು ಒಬ್ಬರೇ ಕಾವಲು ಕಾದರು.", "She single-handedly guarded a secret opening through which enemies crept in.")
            ),
            pages = listOf(
                StoryPage(LocalText("ಕಿಂಡಿಯ ಕಾವಲು", "The Secret Passage"), LocalText("ಹೈದರ್ ಅಲಿಯ ಸೈನಿಕರು ಕೋಟೆಯ ಸಣ್ಣ ಕಿಂಡಿಯಿಂದ ಒಳಬರಲು ಪ್ರಯತ್ನಿಸಿದರು.", "Hyder Ali's soldiers tried to enter through a small hole in the fort.")),
                StoryPage(LocalText("ಒನಕೆಯ ಶಕ್ತಿ", "Power of the Pestle"), LocalText("ಓಬವ್ವ ನೀರು ತರಲು ಹೋದಾಗ ಶತ್ರುಗಳನ್ನು ಕಂಡು ಒನಕೆಯಿಂದ ಹೊಡೆದು ಕೊಂದರು.", "While fetching water, Obavva saw the enemies and killed them with a pestle."))
            ),
            quiz = listOf(
                Question(LocalText("ಓಬವ್ವ ಯಾವ ಆಯುಧ ಬಳಸಿದರು?", "Which weapon did Obavva use?"), listOf(LocalText("ಕತ್ತಿ", "Sword"), LocalText("ಬಿಲ್ಲು", "Bow"), LocalText("ಒನಕೆ", "Pestle")), 2, LocalText("ಅವರು ಒನಕೆಯನ್ನು ಬಳಸಿದರು.", "She used a pestle.")),
                Question(LocalText("ಈ ಕಥೆ ಯಾವ ಕೋಟೆಗೆ ಸಂಬಂಧಿಸಿದೆ?", "This story belongs to which fort?"), listOf(LocalText("ಬಾದಾಮಿ", "Badami"), LocalText("ಚಿತ್ರದುರ್ಗ", "Chitradurga"), LocalText("ಬಿದರ", "Bidar")), 1, LocalText("ಇದು ಚಿತ್ರದುರ್ಗದ ಕಥೆ.", "This is a Chitradurga story.")),
                Question(LocalText("ಶತ್ರು ಸೈನ್ಯ ಯಾರದ್ದಾಗಿತ್ತು?", "Whose army were the enemies from?"), listOf(LocalText("ಹೈದರ್ ಅಲಿ", "Hyder Ali"), LocalText("ಬ್ರಿಟಿಷರು", "British"), LocalText("ಪೇಶ್ವೆಗಳು", "Peshwas")), 0, LocalText("ಅವರು ಹೈದರ್ ಅಲಿಯ ಸೈನಿಕರಾಗಿದ್ದರು.", "They were Hyder Ali's soldiers."))
            ),
            badge = Badge("badge-obavva", LocalText("ಧೈರ್ಯದ ದೀಪ", "Lamp of Courage"), LocalText("ನೀವು ಓಬವ್ವರ ಸಾಹಸವನ್ನು ಕಲಿತಿದ್ದೀರಿ!", "You learned about Obavva's bravery!")),
            memorial = Memorial(LocalText("ಒನಕೆ ಓಬವ್ವ ಕಿಂಡಿ", "Onake Obavva Kindi"), "Chitradurga Fort")
        ),
        Hero(
            id = "keladi-chennamma",
            districtId = "shivamogga",
            name = LocalText("ಕೆಳದಿ ಚೆನ್ನಮ್ಮ", "Keladi Chennamma"),
            title = LocalText("ಧರ್ಮದ ರಕ್ಷಕಿ", "Protector of Dharma"),
            years = "1671-1696",
            imageUrl = "android.resource://com.nammakathey.app/drawable/keladi",
            summary = LocalText("ಔರಂಗಜೇಬನ ವಿರುದ್ಧ ಹೋರಾಡಿ ರಾಜಾರಾಮನಿಗೆ ಆಶ್ರಯ ನೀಡಿದ ರಾಣಿ.", "A queen who gave refuge to Rajaram and fought Aurangzeb."),
            interestingFacts = listOf(
                LocalText("ಅವರು ಶಿವಾಜಿಯ ಮಗ ರಾಜಾರಾಮನಿಗೆ ಮೊಘಲರಿಂದ ರಕ್ಷಣೆ ನೀಡಿದರು.", "She protected Shivaji's son Rajaram from the Mughals."),
                LocalText("ಅವರು ಔರಂಗಜೇಬನ ಬೃಹತ್ ಸೈನ್ಯವನ್ನು ಸೋಲಿಸಿದರು.", "She defeated the massive army sent by Aurangzeb.")
            ),
            pages = listOf(
                StoryPage(LocalText("ಆಶ್ರಯ ನೀಡಿದ ರಾಣಿ", "The Queen of Refuge"), LocalText("ರಾಜಾರಾಮನು ಮೊಘಲರಿಂದ ತಪ್ಪಿಸಿಕೊಂಡು ಬಂದಾಗ ಚೆನ್ನಮ್ಮ ಆಶ್ರಯ ನೀಡಿದರು.", "When Rajaram fled from Mughals, Chennamma gave him shelter.")),
                StoryPage(LocalText("ಮೊಘಲರ ಸೋಲು", "Defeating the Mughals"), LocalText("ಮೊಘಲ್ ಸೈನ್ಯ ದಾಳಿ ಮಾಡಿದಾಗ ಚೆನ್ನಮ್ಮ ಮಲೆನಾಡಿನ ಕಾಡಿನಲ್ಲಿ ಅವರನ್ನು ಸೋಲಿಸಿದರು.", "When the Mughal army attacked, Chennamma defeated them in the Malnad forests."))
            ),
            quiz = listOf(
                Question(LocalText("ಚೆನ್ನಮ್ಮ ಯಾರಿಗೆ ಆಶ್ರಯ ನೀಡಿದರು?", "Whom did Chennamma give refuge to?"), listOf(LocalText("ರಾಜಾರಾಮ", "Rajaram"), LocalText("ಅಕ್ಬರ್", "Akbar"), LocalText("ಬ್ರಿಟಿಷರು", "British")), 0, LocalText("ಅವರು ರಾಜಾರಾಮನಿಗೆ ಆಶ್ರಯ ನೀಡಿದರು.", "She gave refuge to Rajaram.")),
                Question(LocalText("ಅವರು ಯಾವ ಮೊಘಲ್ ಚಕ್ರವರ್ತಿಯನ್ನು ಎದುರಿಸಿದರು?", "Which Mughal emperor did she face?"), listOf(LocalText("ಬಾಬರ್", "Babur"), LocalText("ಔರಂಗಜೇಬ", "Aurangzeb"), LocalText("ಶಹಜಹಾನ್", "Shah Jahan")), 1, LocalText("ಅವರು ಔರಂಗಜೇಬನನ್ನು ಎದುರಿಸಿದರು.", "She faced Aurangzeb.")),
                Question(LocalText("ಕೆಳದಿ ಸಂಸ್ಥಾನ ಯಾವ ಭಾಗದಲ್ಲಿದೆ?", "Where was the Keladi kingdom located?"), listOf(LocalText("ಮಲೆನಾಡು", "Malnad"), LocalText("ಕರಾವಳಿ", "Coast"), LocalText("ಮೈದಾನ", "Plains")), 0, LocalText("ಕೆಳದಿ ಮಲೆನಾಡಿನಲ್ಲಿದೆ.", "Keladi is in Malnad.")),
            ),
            badge = Badge("badge-keladi", LocalText("ಅಭಯ ಹಸ್ತ", "Hand of Protection"), LocalText("ನೀವು ಕೆಳದಿಯ ಇತಿಹಾಸವನ್ನು ಕಲಿತಿದ್ದೀರಿ!", "You learned about Keladi's history!")),
            memorial = Memorial(LocalText("ಕೆಳದಿ ಐತಿಹಾಸಿಕ ವಸ್ತುಸಂಗ್ರಹಾಲಯ", "Keladi Museum"), "Shivamogga District")
        ),
        Hero(
            id = "m-visvesvaraya",
            districtId = "mysuru",
            name = LocalText("ಸರ್ ಎಂ. ವಿಶ್ವೇಶ್ವರಯ್ಯ", "Sir M. Visvesvaraya"),
            title = LocalText("ಭಾರತ ರತ್ನ", "The Maker of Modern India"),
            years = "1860-1962",
            imageUrl = "android.resource://com.nammakathey.app/drawable/visvesvaraya",
            summary = LocalText("ಮೈಸೂರಿನ ದಿವಾನರು ಮತ್ತು ಶ್ರೇಷ್ಠ ಇಂಜಿನಿಯರ್.", "Great engineer and former Diwan of Mysore."),
            interestingFacts = listOf(
                LocalText("ಅವರ ಜನ್ಮದಿನವನ್ನು 'ಇಂಜಿನಿಯರ್ಸ್ ಡೇ' ಆಗಿ ಆಚರಿಸಲಾಗುತ್ತದೆ.", "His birthday is celebrated as Engineers' Day in India."),
                LocalText("ಅವರು ಕೃಷ್ಣರಾಜ ಸಾಗರ (KRS) ಅಣೆಕಟ್ಟನ್ನು ನಿರ್ಮಿಸಿದರು.", "He designed and built the Krishna Raja Sagara (KRS) dam.")
            ),
            pages = listOf(
                StoryPage(LocalText("ಶಿಕ್ಷಣ ಮತ್ತು ಸಾಧನೆ", "Education and Achievements"), LocalText("ವಿಶ್ವೇಶ್ವರಯ್ಯನವರು ಕಡು ಬಡತನದಲ್ಲೂ ಶ್ರಮಪಟ್ಟು ಓದಿ ದೊಡ್ಡ ಇಂಜಿನಿಯರ್ ಆದರು.", "Visvesvaraya studied hard despite poverty and became a great engineer.")),
                StoryPage(LocalText("ಆಧುನಿಕ ಮೈಸೂರು", "Modern Mysore"), LocalText("ಅವರು ಮೈಸೂರಿನಲ್ಲಿ ಅನೇಕ ಕೈಗಾರಿಕೆಗಳು ಮತ್ತು ಬ್ಯಾಂಕ್‌ಗಳನ್ನು ಸ್ಥಾಪಿಸಿದರು.", "He established many industries and banks in Mysore."))
            ),
            quiz = listOf(
                Question(LocalText("ಅವರ ಜನ್ಮದಿನವನ್ನು ಏನಾಗಿ ಆಚರಿಸಲಾಗುತ್ತದೆ?", "What is celebrated on his birthday?"), listOf(LocalText("ಶಿಕ್ಷಕರ ದಿನ", "Teachers' Day"), LocalText("ಇಂಜಿನಿಯರ್ಸ್ ದಿನ", "Engineers' Day"), LocalText("ಮಕ್ಕಳ ದಿನ", "Children's Day")), 1, LocalText("ಇಂಜಿನಿಯರ್ಸ್ ದಿನ ಸರಿಯಾದ ಉತ್ತರ.", "Engineers' Day is correct.")),
                Question(LocalText("ಅವರು ನಿರ್ಮಿಸಿದ ಪ್ರಸಿದ್ಧ ಅಣೆಕಟ್ಟು ಯಾವುದು?", "Which famous dam did he build?"), listOf(LocalText("ಕೆಆರ್‌ಎಸ್", "KRS"), LocalText("ಆಲಮಟ್ಟಿ", "Almatti"), LocalText("ತುಂಗಭದ್ರಾ", "Tungabhadra")), 0, LocalText("ಕೆಆರ್‌ಎಸ್ ಅಣೆಕಟ್ಟು ಮೈಸೂರಿನಲ್ಲಿದೆ.", "KRS dam is in Mysore.")),
                Question(LocalText("ಅವರಿಗೆ ದೊರೆತ ಅತ್ಯುನ್ನತ ಪ್ರಶಸ್ತಿ ಯಾವುದು?", "Which highest award did he receive?"), listOf(LocalText("ಪದ್ಮಶ್ರೀ", "Padma Shri"), LocalText("ಭಾರತ ರತ್ನ", "Bharat Ratna"), LocalText("ಜ್ಞಾನಪೀಠ", "Jnanapeetha")), 1, LocalText("ಅವರಿಗೆ ಭಾರತ ರತ್ನ ಪ್ರಶಸ್ತಿ ದೊರೆತಿದೆ.", "He received Bharat Ratna.")),
            ),
            badge = Badge("badge-mv", LocalText("ಆಧುನಿಕ ಶಿಲ್ಪಿ", "Modern Architect"), LocalText("ನೀವು ಸರ್ ಎಂ.ವಿ ಅವರ ಬಗ್ಗೆ ಕಲಿತಿದ್ದೀರಿ!", "You learned about Sir M.V.!")),
            memorial = Memorial(LocalText("ವಿಶ್ವೇಶ್ವರಯ್ಯ ಸ್ಮಾರಕ, ಮುದ್ದೇನಹಳ್ಳಿ", "Visvesvaraya Memorial, Muddenahalli"), "Chikkaballapura (Near Bengaluru)")
        ),
        Hero(
            id = "field-marshal-cariappa",
            districtId = "kodagu",
            name = LocalText("ಫೀಲ್ಡ್ ಮಾರ್ಷಲ್ ಕಾರ್ಯಪ್ಪ", "Field Marshal Cariappa"),
            title = LocalText("ಸೈನ್ಯದ ಹೆಮ್ಮೆ", "Army's Pride"),
            years = "1899-1993",
            imageUrl = "android.resource://com.nammakathey.app/drawable/cariappa",
            summary = LocalText("ಭಾರತೀಯ ಸೇನೆಯ ಮೊದಲ ಕಮಾಂಡರ್-ಇನ್-ಚೀಫ್.", "First Commander-in-Chief of the Indian Army."),
            interestingFacts = listOf(
                LocalText("ಅವರು ಕೊಡಗಿನ ಶನಿವಾರಸಂತೆಯಲ್ಲಿ ಜನಿಸಿದರು.", "He was born in Sanivarsanthe, Kodagu."),
                LocalText("ಅವರನ್ನು ಪ್ರೀತಿಯಿಂದ 'ಚಿಪ್ಪರ್' ಎಂದು ಕರೆಯಲಾಗುತ್ತಿತ್ತು.", "He was affectionately known as 'Kipper'.")
            ),
            pages = listOf(
                StoryPage(LocalText("ಶಿಸ್ತಿನ ಸಿಪಾಯಿ", "A Soldier of Discipline"), LocalText("ಕಾರ್ಯಪ್ಪನವರು ಸಣ್ಣ ವಯಸ್ಸಿನಿಂದಲೇ ಕ್ರೀಡೆและ ಶಿಸ್ತಿನಲ್ಲಿ ಆಸಕ್ತಿ ಹೊಂದಿದ್ದರು.", "Cariappa was interested in sports and discipline from a young age.")),
                StoryPage(LocalText("ಸೇನಾ ನಾಯಕತ್ವ", "Military Leadership"), LocalText("ಅವರು ಸ್ವತಂತ್ರ ಭಾರತದ ಮೊದಲ ಭಾರತೀಯ ಸೇನಾ ಮುಖ್ಯಸ್ಥರಾದರು.", "He became the first Indian chief of the army of independent India."))
            ),
            quiz = listOf(
                Question(LocalText("ಕಾರ್ಯಪ್ಪನವರು ಎಲ್ಲಿ ಜನಿಸಿದರು?", "Where was Cariappa born?"), listOf(LocalText("ಬೆಂಗಳೂರು", "Bengaluru"), LocalText("ಕೊಡಗು", "Kodagu"), LocalText("ಮಂಗಳೂರು", "Mangalore")), 1, LocalText("ಅವರು ಕೊಡಗಿನಲ್ಲಿ ಜನಿಸಿದರು.", "He was born in Kodagu.")),
                Question(LocalText("ಅವರು ಸೈನ್ಯದಲ್ಲಿ ಯಾವ ಹುದ್ದೆ ಅಲಂಕರಿಸಿದ್ದರು?", "Which post did he hold in the army?"), listOf(LocalText("ಸಿಪಾಯಿ", "Soldier"), LocalText("ಕಮಾಂಡರ್-ಇನ್-ಚೀಫ್", "Commander-in-Chief"), LocalText("ಮೇಜರ್", "Major")), 1, LocalText("ಅವರು ಮೊದಲ ಕಮಾಂಡರ್-ಇನ್-ಚೀಫ್ ಆಗಿದ್ದರು.", "He was the first Commander-in-Chief.")),
                Question(LocalText("ಅವರನ್ನು ಏನೆಂದು ಕರೆಯುತ್ತಿದ್ದರು?", "What was his nickname?"), listOf(LocalText("ಚಿಪ್ಪರ್", "Kipper"), LocalText("ಟೈಗರ್", "Tiger"), LocalText("ಲಾಯನ್", "Lion")), 0, LocalText("ಅವರನ್ನು ಚಿಪ್ಪರ್ ಎಂದು ಕರೆಯುತ್ತಿದ್ದರು.", "He was called Kipper.")),
            ),
            badge = Badge("badge-cariappa", LocalText("ಸೇನಾ ಸಿಂಹ", "Army Lion"), LocalText("ನೀವು ಸೈನ್ಯದ ಹೆಮ್ಮೆಯ ಬಗ್ಗೆ ಕಲಿತಿದ್ದೀರಿ!", "You learned about the pride of the army!")),
            memorial = Memorial(LocalText("ಸನ್ನಿ ಸೈಡ್, ಮಡಿಕೇರಿ", "Sunny Side, Madikeri"), "Kodagu District")
        ),
        Hero(
            id = "pulakeshi-ii",
            districtId = "bagalkote",
            name = LocalText("ಇಮ್ಮಡಿ ಪುಲಕೇಶಿ", "Pulakeshi II"),
            title = LocalText("ಚಾಲುಕ್ಯ ಚಕ್ರವರ್ತಿ", "The Chalukya Emperor"),
            years = "610-642 CE",
            imageUrl = "android.resource://com.nammakathey.app/drawable/pulakeshi",
            summary = LocalText("ಬಾದಾಮಿ ಚಾಲುಕ್ಯರ ಅತ್ಯಂತ ಪ್ರಸಿದ್ಧ ರಾಜ.", "The most famous ruler of the Badami Chalukyas."),
            interestingFacts = listOf(
                LocalText("ಅವರು ಉತ್ತರ ಭಾರತದ ಹರ್ಷವರ್ಧನನನ್ನು ನರ್ಮದಾ ನದಿ ತೀರದಲ್ಲಿ ಸೋಲಿಸಿದರು.", "He defeated Harshavardhana of North India on the banks of Narmada."),
                LocalText("ಅವರ ಕೀರ್ತಿಯು ಚೀನಾ ಮತ್ತು ಪರ್ಷಿಯಾದವರೆಗೂ ಹರಡಿತ್ತು.", "His fame reached as far as China and Persia.")
            ),
            pages = listOf(
                StoryPage(LocalText("ಸಮ್ರಾಟನ ವೈಭವ", "Glory of the Emperor"), LocalText("ಪುಲಕೇಶಿಯು ದಕ್ಷಿಣ ಭಾರತದ ಮೇಲೆ ತನ್ನ ಪ್ರಾಬಲ್ಯ ಸಾಧಿಸಿದನು.", "Pulakeshi established his dominance over South India.")),
                StoryPage(LocalText("ಕಲೆ ಮತ್ತು ಶಿಲ್ಪಕಲೆ", "Art and Sculpture"), LocalText("ಅವರ ಕಾಲದಲ್ಲಿ ಬಾದಾಮಿ ಮತ್ತು ಐಹೊಳೆಯ ಸುಂದರ ದೇವಾಲಯಗಳು ನಿರ್ಮಾಣವಾದವು.", "Beautiful temples of Badami and Aihole were built during his reign."))
            ),
            quiz = listOf(
                Question(LocalText("ಪುಲಕೇಶಿ ಯಾವ ವಂಶದ ರಾಜ?", "Pulakeshi was a king of which dynasty?"), listOf(LocalText("ಹೊಯ್ಸಳ", "Hoysala"), LocalText("ಚಾಲುಕ್ಯ", "Chalukya"), LocalText("ಕದಂಬ", "Kadamba")), 1, LocalText("ಅವರು ಚಾಲುಕ್ಯ ವಂಶದವರು.", "He was from the Chalukya dynasty.")),
                Question(LocalText("ಅವರು ಯಾವ ಉತ್ತರ ಭಾರತದ ರಾಜನನ್ನು ಸೋಲಿಸಿದರು?", "Which North Indian king did he defeat?"), listOf(LocalText("ಹರ್ಷವರ್ಧನ", "Harshavardhana"), LocalText("ಅಕ್ಬರ್", "Akbar"), LocalText("ಚಂದ್ರಗುಪ್ತ", "Chandragupta")), 0, LocalText("ಹರ್ಷವರ್ಧನನನ್ನು ಸೋಲಿಸಿದರು.", "He defeated Harshavardhana.")),
                Question(LocalText("ಚಾಲುಕ್ಯರ ರಾಜಧಾನಿ ಯಾವುದಾಗಿತ್ತು?", "What was the capital of Chalukyas?"), listOf(LocalText("ಬೆಂಗಳೂರು", "Bengaluru"), LocalText("ಬಾದಾಮಿ", "Badami"), LocalText("ಮೈಸೂರು", "Mysore")), 1, LocalText("ಬಾದಾಮಿ ಅವರ ರಾಜಧಾನಿ.", "Badami was their capital.")),
            ),
            badge = Badge("badge-pulakeshi", LocalText("ದಕ್ಷಿಣ ಪಥೇಶ್ವರ", "Lord of the South"), LocalText("ನೀವು ಚಾಲುಕ್ಯರ ವೈಭವವನ್ನು ಕಲಿತಿದ್ದೀರಿ!", "You learned about the glory of Chalukyas!")),
            memorial = Memorial(LocalText("ಬಾದಾಮಿ ಗುಹಾಂತರ ದೇವಾಲಯಗಳು", "Badami Cave Temples"), "Bagalkote District")
        ),
        Hero(
            id = "basavanna",
            districtId = "vijayapura",
            name = LocalText("ಬಸವಣ್ಣ", "Basavanna"),
            title = LocalText("ವಿಶ್ವಗುರು", "The Social Reformer"),
            years = "12th Century",
            imageUrl = "android.resource://com.nammakathey.app/drawable/basavanna",
            summary = LocalText("ಜಾತಿ ಪದ್ಧತಿಯ ವಿರುದ್ಧ ಹೋರಾಡಿದ ಶರಣ ಮತ್ತು ಕವಿ.", "A saint and poet who fought against the caste system."),
            interestingFacts = listOf(
                LocalText("ಅವರು 'ಅನುಭವ ಮಂಟಪ' ಎಂಬ ಮೊದಲ ಸಂಸತ್ತನ್ನು ಸ್ಥಾಪಿಸಿದರು.", "He established 'Anubhava Mantapa', often called the first parliament."),
                LocalText("ಅವರ 'ಕಾಯಕವೇ ಕೈಲಾಸ' ಎಂಬ ತತ್ವ ಬಹಳ ಪ್ರಸಿದ್ಧವಾಗಿದೆ.", "His philosophy 'Work is Worship' is very famous.")
            ),
            pages = listOf(
                StoryPage(LocalText("ಸಮಾನತೆಯ ಸಂದೇಶ", "Message of Equality"), LocalText("ಬಸವಣ್ಣನವರು ಎಲ್ಲಾ ಮನುಷ್ಯರು ಸಮಾನರು ಎಂದು ಬೋಧಿಸಿದರು.", "Basavanna taught that all human beings are equal.")),
                StoryPage(LocalText("ವಚನ ಸಾಹಿತ್ಯ", "Vachana Literature"), LocalText("ಅವರು ಸರಳ ಕನ್ನಡದಲ್ಲಿ ವಚನಗಳನ್ನು ಬರೆದು ಜನರಿಗೆ ಅರಿವು ಮೂಡಿಸಿದರು.", "He wrote Vachanas in simple Kannada to create awareness among people."))
            ),
            quiz = listOf(
                Question(LocalText("ಬಸವಣ್ಣನವರ ಪ್ರಸಿದ್ಧ ಘೋಷಣೆ ಯಾವುದು?", "What is Basavanna's famous slogan?"), listOf(LocalText("ಕಾಯಕವೇ ಕೈಲಾಸ", "Work is Worship"), LocalText("ಜೈ ಹಿಂದ್", "Jai Hind"), LocalText("ಸತ್ಯಮೇವ ಜಯತೇ", "Truth alone triumphs")), 0, LocalText("'ಕಾಯಕವೇ ಕೈಲಾಸ' ಅವರ ಘೋಷಣೆ.", "'Work is Worship' was his slogan.")),
                Question(LocalText("ಅವರು ಸ್ಥಾಪಿಸಿದ ಮಂಟಪ ಯಾವುದು?", "Which Mantapa did he establish?"), listOf(LocalText("ಧ್ಯಾನ ಮಂಟಪ", "Meditation Hall"), LocalText("ಅನುಭವ ಮಂಟಪ", "Anubhava Mantapa"), LocalText("ಕಲಾ ಮಂಟಪ", "Art Hall")), 1, LocalText("ಅನುಭವ ಮಂಟಪ ಸರಿಯಾದ ಉತ್ತರ.", "Anubhava Mantapa is correct.")),
                Question(LocalText("ಅವರು ಯಾವ ಭಾಷೆಯಲ್ಲಿ ಸಾಹಿತ್ಯ ಬರೆದರು?", "In which language did he write literature?"), listOf(LocalText("ಸಂಸ್ಕೃತ", "Sanskrit"), LocalText("ಕನ್ನಡ", "Kannada"), LocalText("ಹಿಂದಿ", "Hindi")), 1, LocalText("ಅವರು ಕನ್ನಡದಲ್ಲಿ ವಚನಗಳನ್ನು ಬರೆದರು.", "He wrote Vachanas in Kannada.")),
            ),
            badge = Badge("badge-basavanna", LocalText("ಕ್ರಾಂತಿಕಾರಿ", "Revolutionary"), LocalText("ನೀವು ಸಮಾನತೆಯ ಪಾಠವನ್ನು ಕಲಿತಿದ್ದೀರಿ!", "You learned the lesson of equality!")),
            memorial = Memorial(LocalText("ಕೂಡಲಸಂಗಮ", "Kudalasangama"), "Bagalkote/Vijayapura Border")
        ),
        Hero(
            id = "da-ra-bendre",
            districtId = "dharwad",
            name = LocalText("ದ. ರಾ. ಬೇಂದ್ರೆ", "Da. Ra. Bendre"),
            title = LocalText("ವರಕವಿ", "The Gifted Poet"),
            years = "1896-1981",
            imageUrl = "android.resource://com.nammakathey.app/drawable/bendre",
            summary = LocalText("ಕನ್ನಡದ ಶ್ರೇಷ್ಠ ಕವಿಗಳಲ್ಲಿ ಒಬ್ಬರು ಮತ್ತು ಜ್ಞಾನಪೀಠ ಪ್ರಶಸ್ತಿ ವಿಜೇತರು.", "One of the greatest Kannada poets and a Jnanpith awardee."),
            interestingFacts = listOf(
                LocalText("ಅವರನ್ನು 'ಅಂಬಿಕಾತನಯದತ್ತ' ಎಂಬ ಕಾವ್ಯನಾಮದಿಂದ ಗುರುತಿಸಲಾಗುತ್ತದೆ.", "He is known by his pen name 'Ambikatanayadatta'."),
                LocalText("ಅವರ 'ನಾಕುತಂತಿ' ಕವನ ಸಂಕಲನಕ್ಕೆ ಜ್ಞಾನಪೀಠ ಪ್ರಶಸ್ತಿ ದೊರೆತಿದೆ.", "He received the Jnanpith award for his collection 'Naku Tanti'.")
            ),
            pages = listOf(
                StoryPage(LocalText("ಕಾವ್ಯದ ಅಲೆಗಳು", "Waves of Poetry"), LocalText("ಬೇಂದ್ರೆಯವರ ಕವನಗಳು ಮಧುರವಾದ ಲಯ ಮತ್ತು ಭಾವನೆಗಳಿಂದ ಕೂಡಿವೆ.", "Bendre's poems are full of sweet rhythm and emotions.")),
                StoryPage(LocalText("ಧಾರವಾಡದ ನಂಟು", "Connection with Dharwad"), LocalText("ಅವರು ಧಾರವಾಡದ ಪರಿಸರ ಮತ್ತು ಸಂಸ್ಕೃತಿಯನ್ನು ಪ್ರೀತಿಸುತ್ತಿದ್ದರು.", "He loved the environment and culture of Dharwad."))
            ),
            quiz = listOf(
                Question(LocalText("ಬೇಂದ್ರೆಯವರ ಕಾವ್ಯನಾಮ ಯಾವುದು?", "What was Bendre's pen name?"), listOf(LocalText("ಕುವೆಂಪು", "Kuvempu"), LocalText("ಅಂಬಿಕಾತನಯದತ್ತ", "Ambikatanayadatta"), LocalText("ವಿನಾಯಕ", "Vinayaka")), 1, LocalText("ಅಂಬಿಕಾತನಯದತ್ತ ಅವರ ಕಾವ್ಯನಾಮ.", "Ambikatanayadatta was his pen name.")),
                Question(LocalText("ಅವರಿಗೆ ಯಾವ ಪ್ರಶಸ್ತಿ ದೊರೆತಿದೆ?", "Which award did he receive?"), listOf(LocalText("ಜ್ಞಾನಪೀಠ", "Jnanpith"), LocalText("ಪದ್ಮಶ್ರೀ", "Padma Shri"), LocalText("ನೋಬೆಲ್", "Nobel")), 0, LocalText("ಅವರಿಗೆ ಜ್ಞಾನಪೀಠ ಪ್ರಶಸ್ತಿ ದೊರೆತಿದೆ.", "He received the Jnanpith award.")),
                Question(LocalText("ಅವರ ಪ್ರಸಿದ್ಧ ಕವನ ಸಂಕಲನ ಯಾವುದು?", "Which is his famous collection?"), listOf(LocalText("ನಾಕುತಂತಿ", "Naku Tanti"), LocalText("ರಾಮಾಯಣ", "Ramayana"), LocalText("ಗೀತಾಂಜಲಿ", "Gitanjali")), 0, LocalText("ನಾಕುತಂತಿ ಅವರ ಪ್ರಸಿದ್ಧ ಕೃತಿ.", "Naku Tanti is his famous work."))
            ),
            badge = Badge("badge-bendre", LocalText("ಕನ್ನಡ ಕೋಗಿಲೆ", "Nightingale of Kannada"), LocalText("ನೀವು ಕವಿತೆಯ ಮಧುರತೆಯನ್ನು ಕಲಿತಿದ್ದೀರಿ!", "You learned the sweetness of poetry!")),
            memorial = Memorial(LocalText("ಬೇಂದ್ರೆ ಭವನ", "Bendre Bhavana"), "Dharwad District")
        ),
        Hero(
            id = "gorur-ramaswamy",
            districtId = "hassan",
            name = LocalText("ಗೊರೂರು ರಾಮಸ್ವಾಮಿ ಅಯ್ಯಂಗಾರ್", "Gorur Ramaswamy Iyengar"),
            title = LocalText("ಹಾಸ್ಯ ಚಕ್ರವರ್ತಿ", "Master of Humor"),
            years = "1904-1991",
            imageUrl = "android.resource://com.nammakathey.app/drawable/gorur",
            summary = LocalText("ಹಳ್ಳಿಯ ಬದುಕನ್ನು ಹಾಸ್ಯಭರಿತವಾಗಿ ಚಿತ್ರಿಸಿದ ಲೇಖಕ.", "Writer who humorously portrayed village life."),
            interestingFacts = listOf(
                LocalText("ಅವರು ಗಾಂಧೀಜಿಯವರ ಅನುಯಾಯಿಯಾಗಿದ್ದರು ಮತ್ತು ಸ್ವಾತಂತ್ರ್ಯ ಹೋರಾಟದಲ್ಲಿ ಭಾಗವಹಿಸಿದ್ದರು.", "He was a follower of Gandhi and participated in the freedom struggle."),
                LocalText("ಅವರ 'ಗೊರೂರು ಪ್ರಬಂಧಗಳು' ಬಹಳ ಪ್ರಸಿದ್ಧವಾಗಿವೆ.", "His 'Goruru Essays' are very famous for their wit.")
            ),
            pages = listOf(
                StoryPage(LocalText("ಹಳ್ಳಿಯ ಚಿತ್ರಣ", "Portrayal of Village Life"), LocalText("ಗೊರೂರರು ತಮ್ಮ ಬರಹಗಳಲ್ಲಿ ಹಳ್ಳಿಯ ಜನರ ಮುಗ್ಧತೆ ಮತ್ತು ಹಾಸ್ಯವನ್ನು ತಂದಿದ್ದಾರೆ.", "Iyengar brought out the innocence and humor of village people in his writings.")),
                StoryPage(LocalText("ಗಾಂಧಿ ಪ್ರಭಾವ", "Influence of Gandhi"), LocalText("ಗಾಂಧೀಜಿಯವರ ಸರಳ ಬದುಕು ಮತ್ತು ಸತ್ಯದ ಹಾದಿ ಅವರನ್ನು ಪ್ರೇರೇಪಿಸಿತು.", "Gandhi's simple life and path of truth inspired him."))
            ),
            quiz = listOf(
                Question(LocalText("ಗೊರೂರರು ಯಾವ ಬದುಕನ್ನು ಚಿತ್ರಿಸಿದ್ದಾರೆ?", "Whose life did Goruru portray?"), listOf(LocalText("ನಗರ", "City"), LocalText("ಹಳ್ಳಿ", "Village"), LocalText("ಸಮುದ್ರ", "Sea")), 1, LocalText("ಅವರು ಹಳ್ಳಿಯ ಬದುಕನ್ನು ಚಿತ್ರಿಸಿದ್ದಾರೆ.", "He portrayed village life.")),
                Question(LocalText("ಅವರು ಯಾರ ಅನುಯಾಯಿಯಾಗಿದ್ದರು?", "Whose follower was he?"), listOf(LocalText("ಗಾಂಧೀಜಿ", "Gandhi"), LocalText("ನೆಹರು", "Nehru"), LocalText("ಪಟೇಲ್", "Patel")), 0, LocalText("ಅವರು ಗಾಂಧೀಜಿಯವರ ಅನುಯಾಯಿ.", "He was a follower of Gandhi.")),
                Question(LocalText("ಗೊರೂರು ಯಾವ ಜಿಲ್ಲೆಯಲ್ಲಿದೆ?", "Gorur is in which district?"), listOf(LocalText("ಮೈಸೂರು", "Mysore"), LocalText("ಹಾಸನ", "Hassan"), LocalText("ಮಂಡ್ಯ", "Mandya")), 1, LocalText("ಗೊರೂರು ಹಾಸನ ಜಿಲ್ಲೆಯಲ್ಲಿದೆ.", "Gorur is in Hassan district."))
            ),
            badge = Badge("badge-gorur", LocalText("ಹಾಸ್ಯ ಪ್ರೇಮಿ", "Lover of Humor"), LocalText("ನೀವು ಹಳ್ಳಿಯ ಸೊಬಗನ್ನು ಕಲಿತಿದ್ದೀರಿ!", "You learned about the beauty of village life!")),
            memorial = Memorial(LocalText("ಗೊರೂರು ಹೇಮಾವತಿ ಅಣೆಕಟ್ಟು", "Gorur Hemavathi Dam"), "Hassan District")
        )
    )

    fun heroesForDistrict(districtId: String): List<Hero> = heroes.filter { it.districtId == districtId }
    fun hero(heroId: String): Hero? = heroes.firstOrNull { it.id == heroId }
    fun district(districtId: String): District? = districts.firstOrNull { it.id == districtId }
}
