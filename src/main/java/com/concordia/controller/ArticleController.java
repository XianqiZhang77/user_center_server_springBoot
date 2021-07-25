package com.concordia.controller;

import com.concordia.common.util.JwtTokenUtil;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.request.ArticleRequest;
import com.concordia.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/publish", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult publish(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
                              @RequestBody ArticleRequest articleRequest) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return articleService.publish(userId, articleRequest);
    }

    @PostMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult update(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
                              @RequestBody ArticleRequest articleRequest,
                              @RequestParam(name = "article_id") String articleId) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return articleService.update(userId, articleId, articleRequest);
    }

    @PostMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult delete(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
                             @RequestParam(name = "article_id") String articleId) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return articleService.deleteArticleById(userId, articleId);
    }

    @PostMapping(value = "/show/detail", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult showDetail(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
                             @RequestParam(name = "article_id") String articleId) {
        return articleService.showSingleArticleById(articleId);
    }


}
