package com.shoshin.data.mappers

import com.shoshin.data.db.entities.CharacterDbo
import com.shoshin.data.db.entities.LocationDbo
import com.shoshin.data.db.entities.OriginDbo
import com.shoshin.data.remote.entities.CharacterRemote
import com.shoshin.domain.common.Mapper

class CharacterRemoteToDboMapper: Mapper<CharacterRemote, CharacterDbo>() {
    override fun map(from: CharacterRemote): CharacterDbo =
        CharacterDbo(
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
            location = LocationDbo(
                name = from.location?.name,
                url = from.location?.url
            ),
            origin = OriginDbo(
                name = from.origin?.name,
                url = from.origin?.url
            )
        )
}
