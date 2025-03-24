package domain.wiseSaying.repository

import domain.wiseSaying.entity.WiseSaying

interface WiseSayingRepository {

    fun save(wiseSaying: WiseSaying): WiseSaying
    fun findAll(): List<WiseSaying>
    fun findById(id: Int): WiseSaying?
    fun delete(wiseSaying: WiseSaying)
    fun clear()
}