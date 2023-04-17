package com.lj.infrastructure.post.category;

import com.lj.domain.post.category.PostCategory;
import com.lj.domain.post.category.PostCategoryGateway;
import com.lj.domain.post.category.PostCategoryID;
import com.lj.infrastructure.post.category.persistence.PostCategoryJpaEntity;
import com.lj.infrastructure.post.category.persistence.PostCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostCategoryPostgreSQLGateway implements PostCategoryGateway {

    private final PostCategoryRepository repository;

    public PostCategoryPostgreSQLGateway(PostCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public PostCategory create(PostCategory anPostCategory) {
        return save(anPostCategory);
    }

    @Override
    public void deleteById(PostCategoryID anId) {
        final var anIdValue = anId.getValue();

        if(this.repository.existsById(anIdValue)) {
            repository.deleteById(anId.getValue());
        }
    }

    @Override
    public Optional<PostCategory> findById(PostCategoryID anId) {
        return this.repository.findById(anId.getValue())
                .map(PostCategoryJpaEntity::toAggregate);
    }

    @Override
    public PostCategory update(PostCategory anPostCategory) {
        return save(anPostCategory);
    }

    @Override
    public List<PostCategory> findAllByUserOrderByNameAsc(String anUser) {
        List<PostCategory> result = new ArrayList<>();
        List<PostCategoryJpaEntity> accountJpaEntities = this.repository.findAllByUserOrderByNameAsc(anUser);
        if (accountJpaEntities != null && !accountJpaEntities.isEmpty())
            result = accountJpaEntities.stream().map(PostCategoryJpaEntity::toAggregate).toList();
        return result;
    }

    private PostCategory save(final PostCategory anPostCategory) {
        return this.repository.save(PostCategoryJpaEntity.from(anPostCategory)).toAggregate();
    }
}
