package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.ArticleDto;
import com.shashashark.amugeona.model.entity.Article;
import com.shashashark.amugeona.model.param.ArticleUpdateParam;
import com.shashashark.amugeona.model.repository.ArticleRepository;
import com.shashashark.amugeona.model.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Override
    public Optional<ArticleDto> selectOne(Long articleSeq) {
        articleRepository.findById(articleSeq).orElseThrow().updateViewCnt();
        return Optional.ofNullable(toDto(articleRepository.findById(articleSeq).orElseThrow()));
    }

    @Override
    public List<ArticleDto> selectAll(Long boardSeq, String orderBy, int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, orderBy);
        PageRequest pageRequest = PageRequest.of(page, 10, sort);
        return articleRepository.findAllByBoardSeq(boardSeq, pageRequest).stream().map(this::toDto).collect(Collectors.toList());
    }


    @Override
    public List<ArticleDto> searchTitle(Long boardSeq, String title, String orderBy, int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, orderBy);
        PageRequest pageRequest = PageRequest.of(page, 10, sort);
        return articleRepository.findAllByBoardSeqAndTitleContaining(boardSeq, title, pageRequest).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> searchContent(Long boardSeq, String content, String orderBy, int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, orderBy);
        PageRequest pageRequest = PageRequest.of(page, 10, sort);
        return articleRepository.findAllByBoardSeqAndContentContaining(boardSeq, content, pageRequest).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void writeArticle(ArticleDto articleDto) {
        articleRepository.save(toEntity(articleDto));
    }

    @Override
    public void updateArticle(ArticleUpdateParam param) {
        Article article = articleRepository.findById(param.getArticleSeq()).orElseThrow();
        article.modify(param.getTitle(), param.getContent(), param.getInfo());
    }

    @Override
    public void deleteArticle(Long articleSeq) {
        articleRepository.deleteById(articleSeq);
    }

    @Override
    public void updateLike(Long articleSeq) {
        Article article = articleRepository.findById(articleSeq).orElseThrow();
        article.updateLike(article.getArticleLike() + 1);
    }
}
