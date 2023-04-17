package com.lj.infrastructure.post.item;


import com.lj.domain.post.item.PostItem;
import com.lj.domain.post.item.PostItemGateway;
import com.lj.domain.post.item.PostItemID;
import com.lj.infrastructure.post.item.persistence.PostItemJpaEntity;
import com.lj.infrastructure.post.item.persistence.PostItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostItemPostgreSQLGateway implements PostItemGateway {

    private final PostItemRepository repository;

    public PostItemPostgreSQLGateway(PostItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PostItem> create(List<PostItem> anPostItems) {
        final var result = this.repository.saveAll(anPostItems.stream().map(PostItemJpaEntity::from).toList());
        return result.stream().map(PostItemJpaEntity::toAggregate).toList();
    }

    @Override
    public void deleteById(PostItemID anId) {
        final var anIdValue = anId.getValue();

        if(this.repository.existsById(anIdValue)) {
            repository.deleteById(anId.getValue());
        }
    }

    @Override
    public Optional<PostItem> findById(PostItemID anId) {
        return this.repository.findById(anId.getValue())
                .map(PostItemJpaEntity::toAggregate);
    }

    @Override
    public PostItem update(PostItem anPostItem) {
        return save(anPostItem);
    }

    @Override
    public List<PostItem> findAllByPostOrderByNumberAsc(String anUser) {
        List<PostItem> result = new ArrayList<>();
        List<PostItemJpaEntity> accountJpaEntities = this.repository.findAllByPostOrderByNumberAsc(anUser);
        if (accountJpaEntities != null && !accountJpaEntities.isEmpty())
            result = accountJpaEntities.stream().map(PostItemJpaEntity::toAggregate).toList();
        return result;
    }

    private PostItem save(final PostItem anPostItem) {
        return this.repository.save(PostItemJpaEntity.from(anPostItem)).toAggregate();
    }
}
