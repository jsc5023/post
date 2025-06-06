package com.example.upload.global.security

import com.example.upload.domain.member.member.service.MemberService
import lombok.RequiredArgsConstructor
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class CustomOauth2UserService(
    private val memberService: MemberService
) : DefaultOAuth2UserService() {

    @Transactional
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = super.loadUser(userRequest)

        val oauthId = oAuth2User.name //식별자
        val providerType = userRequest
            .clientRegistration
            .registrationId

        val attributes = oAuth2User.attributes
        val propeties = attributes["properties"] as Map<String, Any>

        val nickname = propeties["nickname"] as String
        val profileImage = propeties["profile_image"] as String
        val username = "${providerType}__$oauthId"

        val opMember = memberService.findByUsername(username)

        if (opMember.isPresent) {
            val member = opMember.get()
            member.update(nickname)

            return SecurityUser(member)
        }

        val member = memberService.join(username, "", nickname, profileImage)

        return SecurityUser(member)
    }
}
