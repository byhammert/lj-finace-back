package com.lj.infrastructure.post.subcategory;

import com.lj.domain.post.subcategory.PostSubcategory;
import com.lj.domain.post.subcategory.PostSubcategoryGateway;
import com.lj.domain.post.subcategory.PostSubcategoryID;
import com.lj.infrastructure.post.subcategory.persistence.PostSubcategoryJpaEntity;
import com.lj.infrastructure.post.subcategory.persistence.PostSubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostSubcategoryPostgreSQLGateway implements PostSubcategoryGateway {

    private final PostSubcategoryRepository repository;

    public PostSubcategoryPostgreSQLGateway(PostSubcategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public PostSubcategory create(PostSubcategory aPostSubcategory) {
        return save(aPostSubcategory);
    }

    @Override
    public void deleteById(PostSubcategoryID anId) {
        final var anIdValue = anId.getValue();

        if(this.repository.existsById(anIdValue)) {
            repository.deleteById(anId.getValue());
        }
    }

    @Override
    public Optional<PostSubcategory> findById(PostSubcategoryID anId) {
        return this.repository.findById(anId.getValue())
                .map(PostSubcategoryJpaEntity::toAggregate);
    }

    @Override
    public PostSubcategory update(PostSubcategory aPostSubcategory) {
        return save(aPostSubcategory);
    }

    @Override
    public List<PostSubcategory> findAllByPostCategoryOrderByNameAsc(String aPostCategory) {
        List<PostSubcategory> result = new ArrayList<>();
        List<PostSubcategoryJpaEntity> accountJpaEntities = this.repository.findAllByPostCategoryOrderByNameAsc(aPostCategory);
        if (accountJpaEntities != null && !accountJpaEntities.isEmpty())
            result = accountJpaEntities.stream().map(PostSubcategoryJpaEntity::toAggregate).toList();
        return result;
    }

    private PostSubcategory save(final PostSubcategory anPostSubcategory) {
        return this.repository.save(PostSubcategoryJpaEntity.from(anPostSubcategory)).toAggregate();
    }
}
