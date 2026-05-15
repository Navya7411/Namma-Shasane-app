package com.namma.shasane.model

data class Inscription(
    val id: Int,
    val title: String,
    val location: String,
    val district: String,
    val distanceKm: Double,
    val century: String,
    val type: InscriptionType,
    val status: InscriptionStatus,
    val dynasty: String,
    val description: String,
    val thumbnailType: ThumbnailType,
    val markerType: MarkerType,
    // Approximate map position (0f-1f relative to map bounds)
    val mapX: Float,
    val mapY: Float
)

enum class InscriptionType(val displayName: String) {
    LAND_GRANT("Land Grant"),
    ROYAL_DECREE("Royal Decree"),
    BOUNDARY("Boundary"),
    GENEALOGY("Genealogy"),
    PRASHASTI("Prashasti"),
    VOTIVE("Votive"),
    MEMORIAL("Memorial")
}

enum class InscriptionStatus(val displayName: String) {
    PRESERVED("Preserved"),
    AT_RISK("At Risk"),
    MUSEUM("Museum"),
    RESTORED("Restored")
}

enum class ThumbnailType {
    STONE,
    TEMPLE,
    GATE,
    WARNING
}

enum class MarkerType {
    FOUND,
    VERIFIED,
    AT_RISK
}

object InscriptionRepository {

    fun getSampleInscriptions(): List<Inscription> = listOf(
        Inscription(
            id = 1,
            title = "Belur Vishnu Grant Stone",
            location = "Belur",
            district = "Hassan District",
            distanceKm = 2.3,
            century = "12th Century",
            type = InscriptionType.LAND_GRANT,
            status = InscriptionStatus.PRESERVED,
            dynasty = "Hoysala",
            description = "A beautifully carved stone inscription recording the grant of land to the Chennakeshava temple by King Vishnuvardhana of the Hoysala dynasty. The inscription is written in old Kannada script and records details of the land boundaries, witnesses, and the date of the grant.",
            thumbnailType = ThumbnailType.STONE,
            markerType = MarkerType.VERIFIED,
            mapX = 0.30f,
            mapY = 0.60f
        ),
        Inscription(
            id = 2,
            title = "Rashtrakuta Victory Slab",
            location = "Hampi",
            district = "Ballari District",
            distanceKm = 4.1,
            century = "9th Century",
            type = InscriptionType.ROYAL_DECREE,
            status = InscriptionStatus.PRESERVED,
            dynasty = "Rashtrakuta",
            description = "A large victory pillar inscription celebrating King Amoghavarsha I's military campaigns. The slab depicts scenes of battle and contains verses in Sanskrit and Kannada praising the king's prowess and generosity to Jain institutions.",
            thumbnailType = ThumbnailType.TEMPLE,
            markerType = MarkerType.FOUND,
            mapX = 0.55f,
            mapY = 0.35f
        ),
        Inscription(
            id = 3,
            title = "Chalukya Boundary Marker",
            location = "Aihole",
            district = "Bagalkot District",
            distanceKm = 6.7,
            century = "7th Century",
            type = InscriptionType.BOUNDARY,
            status = InscriptionStatus.AT_RISK,
            dynasty = "Badami Chalukya",
            description = "One of the earliest known inscriptions from the Chalukya period, marking the eastern boundary of the royal capital at Vatapi. The stone shows signs of significant weathering. Immediate conservation efforts are recommended by the Archaeological Survey.",
            thumbnailType = ThumbnailType.WARNING,
            markerType = MarkerType.AT_RISK,
            mapX = 0.40f,
            mapY = 0.28f
        ),
        Inscription(
            id = 4,
            title = "Ganga Dynasty Copper Plate",
            location = "Shravanabelagola",
            district = "Hassan District",
            distanceKm = 8.2,
            century = "4th Century",
            type = InscriptionType.GENEALOGY,
            status = InscriptionStatus.MUSEUM,
            dynasty = "Western Ganga",
            description = "A rare copper plate inscription from the Western Ganga dynasty recording the royal genealogy and grants to Jain institutions. The plate is now housed in the Shravanabelagola museum for preservation and is one of the oldest records of Jain patronage in Karnataka.",
            thumbnailType = ThumbnailType.GATE,
            markerType = MarkerType.VERIFIED,
            mapX = 0.28f,
            mapY = 0.55f
        ),
        Inscription(
            id = 5,
            title = "Sringeri Temple Prashasti",
            location = "Sringeri",
            district = "Chikkamagaluru District",
            distanceKm = 11.0,
            century = "14th Century",
            type = InscriptionType.PRASHASTI,
            status = InscriptionStatus.PRESERVED,
            dynasty = "Vijayanagara",
            description = "An elaborate prashasti (eulogy) inscription on the outer wall of the Sharada temple, commissioned by the Vijayanagara king Bukka Raya I. The inscription records the founding of the Sringeri Math by Adi Shankaracharya and subsequent patronage by the Vijayanagara emperors.",
            thumbnailType = ThumbnailType.TEMPLE,
            markerType = MarkerType.FOUND,
            mapX = 0.18f,
            mapY = 0.70f
        )
    )

    fun getSampleStories(): List<Story> = listOf(
        Story(
            id = 1,
            title = "The Warrior King of Hampi",
            dynasty = "Vijayanagara Empire · 14th Century",
            location = "Hampi, Ballari District",
            emoji = "⚔️",
            excerpt = "An inscription found on the walls of the Hazara Rama temple tells the tale of King Harihara I, who unified the warring chiefs of the Deccan under a single banner. The stone carving describes seventeen battles fought in a single monsoon season, and the magnanimous terms of peace offered to defeated rulers who pledged loyalty to Hampi."
        ),
        Story(
            id = 2,
            title = "The Merchant's Generous Gift",
            dynasty = "Hoysala Empire · 12th Century",
            location = "Belur, Hassan District",
            emoji = "💎",
            excerpt = "A modest stone near the Chennakeshava temple records the remarkable gift of a wealthy spice merchant named Keshavadasa. Having made his fortune trading pepper and cardamom with Arab merchants, he donated an entire village — with its wells, cattle, and agricultural implements — to sustain the temple priests and feed pilgrims."
        ),
        Story(
            id = 3,
            title = "The Jain Saint's Last Words",
            dynasty = "Western Ganga · 4th Century",
            location = "Shravanabelagola, Hassan District",
            emoji = "🧘",
            excerpt = "Among the oldest inscriptions at Shravanabelagola, a small granite slab records the final discourse of the Jain monk Acharya Kundakunda before he performed Sallekhana — the sacred Jain practice of fasting unto death. His words, recorded by devoted disciples, contain philosophical insights about the nature of the soul that are still studied in Jain seminaries today."
        ),
        Story(
            id = 4,
            title = "The Boundary Dispute of 630 CE",
            dynasty = "Badami Chalukya · 7th Century",
            location = "Aihole, Bagalkot District",
            emoji = "🪨",
            excerpt = "A legal document etched in stone records one of the earliest known land disputes in Karnataka. Two neighboring villages — Aihole and Mahakuta — contested the ownership of a fertile river bank. The judgment, delivered by King Pulakesi II's minister, established boundaries that can still be traced on modern maps, making it one of the most remarkably accurate ancient surveys in the Deccan."
        ),
        Story(
            id = 5,
            title = "The Scholar Who Defied a Dynasty",
            dynasty = "Rashtrakuta · 9th Century",
            location = "Hampi, Ballari District",
            emoji = "📚",
            excerpt = "A little-known inscription tucked behind a larger victory pillar tells of a Brahmin scholar named Vijayasimha who publicly debated a Rashtrakuta minister and won. Rather than punishing him, King Amoghavarsha I — himself a poet and philosopher — rewarded the scholar with a land grant, an act that the inscription proudly calls 'the king's greatest victory: the triumph of wisdom over pride.'"
        )
    )
}

data class Story(
    val id: Int,
    val title: String,
    val dynasty: String,
    val location: String,
    val emoji: String,
    val excerpt: String
)
