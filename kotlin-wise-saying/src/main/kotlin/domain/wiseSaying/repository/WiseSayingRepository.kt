package domain.wiseSaying.repository

import domain.wiseSaying.entity.WiseSaying
import standard.Page

interface WiseSayingRepository {

    fun save(wiseSaying: WiseSaying): WiseSaying
    fun findAll(): List<WiseSaying>
    fun findById(id: Int): WiseSaying?
    fun delete(wiseSaying: WiseSaying)
    fun clear()
    fun findByAuthorLike(keyword: String): List<WiseSaying>
    fun findBySayingLike(keyword: String): List<WiseSaying>
    fun findByAuthorLikePaged(keyword: String, page: Int, pageSize: Int): Page
    fun findBySayingLikePaged(keyword: String, page: Int, pageSize: Int): Page
}