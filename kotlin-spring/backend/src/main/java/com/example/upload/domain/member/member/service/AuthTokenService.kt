package com.example.upload.domain.member.member.service

import com.example.upload.domain.member.member.entity.Member
import com.example.upload.standard.util.Ut.jwt.createToken
import com.example.upload.standard.util.Ut.jwt.getPayload
import com.example.upload.standard.util.Ut.jwt.isValidToken
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AuthTokenService {
    @Value("\${custom.jwt.secret-key}")
    private lateinit var keyString: String

    @Value("\${custom.jwt.expire-seconds}")
    private var expireSeconds: Int = 0
    fun genAccessToken(member: Member): String {
        return createToken(
            keyString,
            expireSeconds,
            java.util.Map.of("id", member.id, "username", member.username, "nickname", member.nickname)
        )
    }

    fun getPayload(token: String?): Map<String, Any?>? {
        if (!isValidToken(keyString, token)) return null

        val payload = getPayload(keyString, token)
        val idNo = payload["id"] as Number?
        val id = idNo!!.toLong()
        val username = payload["username"] as String
        val nickname = payload["nickname"] as String

        return java.util.Map.of<String, Any?>("id", id, "username", username, "nickname", nickname)
    }
}
