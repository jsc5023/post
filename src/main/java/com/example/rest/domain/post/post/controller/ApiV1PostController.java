package com.example.rest.domain.post.post.controller;

import com.example.rest.domain.post.post.dto.PostDto;
import com.example.rest.domain.post.post.entity.Post;
import com.example.rest.domain.post.post.service.PostService;
import com.example.rest.global.dto.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ApiV1PostController {

    private final PostService postService;


    @GetMapping
    public RsData<List<PostDto>> getItems() {

        List<Post> posts = postService.getItems();
        List<PostDto> postDtos = posts.stream()
                .map(PostDto::new)
                .toList();

        return new RsData<>(
                "200-1",
                "글 조회가 완료되었습니다.",
                postDtos
        );
    }

    @GetMapping("{id}")
    public RsData<PostDto> getItem(@PathVariable long id) {

        Post post = postService.getItem(id).get();

        return new RsData<> (
                "200-1",
                "글 조회가 완료되었습니다.",
                new PostDto(post)
        );
    }


    @DeleteMapping("/{id}")
    public RsData delete(@PathVariable long id) {
        Post post = postService.getItem(id).get();
        postService.delete(post);

        Map<String, Object> rsData = new HashMap<>();
        rsData.put("code", "200-1");
        rsData.put("msg", "%d번 글 삭제가 완료되었습니다.".formatted(id));

        return new RsData(
                "200-1",
                "%d번 글 삭제가 완료되었습니다.".formatted(id)
        );
    }


    record ModifyReqBody(@NotBlank @Length(min=3) String title, @NotBlank @Length(min=3) String content) {}

    @PutMapping("{id}")
    public RsData<Void> modify(@PathVariable long id, @RequestBody @Valid ModifyReqBody body) {

        Post post = postService.getItem(id).get();
        postService.modify(post, body.title(), body.content());

        return new RsData<> (
                "200-1",
                "%d번 글 수정이 완료되었습니다.".formatted(id),
                null
        );
    }

    record WriteReqBody (@NotBlank @Length(min=3) String title, @NotBlank @Length(min=3) String content) { }

    record WriteResBody(long id, long totalCount) { }

    @PostMapping
    public ResponseEntity<RsData<WriteResBody>> write(@RequestBody @Valid WriteReqBody body) {

        Post post = postService.write(body.title(), body.content());

        // 바디
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    new RsData<> (
                        "200-1",
                        "글 작성이 완료되었습니다.",
                        new WriteResBody(
                                post.getId(),
                                postService.count()
                    )
                )

        );
    }
}