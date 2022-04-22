package com.shoshin.data.db.sources

//
//class CharactersLocalSource @Inject constructor(
//    private val dao: CharactersDao,
//    private val dispatchers: DispatchersWrapper
//): ICharactersLocalSource {
//    override suspend fun insertAll(characters: List<CharacterDbo>) = withContext(dispatchers.io) {
//        dao.insertAll(characters)
//    }
//    override fun getAll(): PagingSource<Int, CharacterDbo> = dao.getAll()
//    override suspend fun deleteAll() = withContext(dispatchers.io) { dao.deleteAll() }
//}