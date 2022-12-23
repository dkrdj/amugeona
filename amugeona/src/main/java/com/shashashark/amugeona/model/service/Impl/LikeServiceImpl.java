package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.LikeDto;
import com.shashashark.amugeona.model.repository.LikeRepository;
import com.shashashark.amugeona.model.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;


    @Override
    public Optional<LikeDto> selectOne(Long userSeq, Long articleSeq) {
        return Optional.ofNullable(toDto(likeRepository.findByUserSeqAndArticleSeq(userSeq, articleSeq).orElseThrow()));
    }

    @Override
    public void writeLike(LikeDto likeDto) {
        likeRepository.save(toEntity(likeDto));
    }

    @Override
    public void deleteLike(Long userSeq, Long articleSeq) {
        likeRepository.deleteByUserSeqAndArticleSeq(userSeq, articleSeq);
    }
}
