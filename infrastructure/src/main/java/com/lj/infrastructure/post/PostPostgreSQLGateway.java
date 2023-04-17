package com.lj.infrastructure.post;


import com.lj.domain.PaginationSearchQuery;
import com.lj.domain.pagination.Pagination;
import com.lj.domain.post.Post;
import com.lj.domain.post.PostGateway;
import com.lj.domain.post.PostID;
import com.lj.infrastructure.post.persistence.PostJpaEntity;
import com.lj.infrastructure.post.persistence.PostRepository;
import com.lj.infrastructure.utils.SpecificationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.lj.infrastructure.utils.SpecificationUtils.like;

@Service
public class PostPostgreSQLGateway implements PostGateway {

    private final PostRepository repository;

    public PostPostgreSQLGateway(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public Post create(Post aPost) {
        return save(aPost);
    }

    @Override
    public void deleteById(PostID anId) {
        final var anIdValue = anId.getValue();

        if(this.repository.existsById(anIdValue)) {
            repository.deleteById(anId.getValue());
        }
    }

    @Override
    public Optional<Post> findById(PostID anId) {
        return this.repository.findById(anId.getValue())
                .map(PostJpaEntity::toAggregate);
    }

    @Override
    public Post update(Post aPost) {
        return save(aPost);
    }

    @Override
    public Pagination<Post> findAll(PaginationSearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(str -> {
                    final Specification<PostJpaEntity> userEquals = SpecificationUtils.equals("user", str);
                    final Specification<PostJpaEntity> recurrenceLike = like("recurrence", str);
                    final Specification<PostJpaEntity> descriptionLike = like("description", str);
                    return userEquals.and(recurrenceLike.or(descriptionLike));
                })
                .orElse(null);

        final var pageResult = this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(PostJpaEntity::toAggregate).toList()
        );
    }

    private Post save(final Post aPost) {
        return this.repository.save(PostJpaEntity.from(aPost)).toAggregate();
    }
}
