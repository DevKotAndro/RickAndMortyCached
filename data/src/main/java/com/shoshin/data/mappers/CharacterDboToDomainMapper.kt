package com.shoshin.data.mappers

import com.shoshin.data.db.entities.CharacterDbo
import com.shoshin.domain.common.Mapper
import com.shoshin.domain.entities.CharacterDomain
import com.shoshin.domain.entities.Location
import com.shoshin.domain.entities.Origin

class CharacterDboToDomainMapper: Mapper<CharacterDbo, CharacterDomain>() {
    override fun map(from: CharacterDbo): CharacterDomain =
        CharacterDomain(
            id = from.id,
            image = from.image,
            gender = from.gender,
            species = from.species,
            created = from.created,
            name = from.name,
            episode = from.episode,
            type = from.type,
            url = from.url,
            status = from.status,
            location = Location(
                name = from.location?.name,
                url = from.location?.url
            ),
            origin = Origin(
                name = from.origin?.name,
                url = from.origin?.url
            )
        )
}
