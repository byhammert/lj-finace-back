package com.lj.infrastructure.configuration.usecases;

import com.lj.application.account.create.CreateAccountUseCase;
import com.lj.application.account.create.DefaultCreateAccountUseCase;
import com.lj.application.account.delete.DefaultDeleteAccountUseCase;
import com.lj.application.account.delete.DeleteAccountUseCase;
import com.lj.application.account.retrieve.get.DefaultGetAccountByIdUseCase;
import com.lj.application.account.retrieve.get.GetAccountByIdUseCase;
import com.lj.application.account.retrieve.list.DefaultListAccountsUseCase;
import com.lj.application.account.retrieve.list.ListAccountsUseCase;
import com.lj.application.account.update.DefaultUpdateAccountUseCase;
import com.lj.application.account.update.UpdateAccountUseCase;
import com.lj.application.account.update.addamount.UpdateAccountAddAmountUseCase;
import com.lj.application.post.category.retrieve.get.GetPostCategoryByIdUseCase;
import com.lj.application.post.create.expense.CreateExpensePostUseCase;
import com.lj.application.post.create.expense.DefaultCreateExpensePostUseCase;
import com.lj.application.post.create.revenue.CreateRevenuePostUseCase;
import com.lj.application.post.create.revenue.DefaultCreateRevenuePostUseCase;
import com.lj.application.post.item.create.CreateListPostItemUseCase;
import com.lj.application.post.retrieve.list.DefaultListPostsUseCase;
import com.lj.application.post.retrieve.list.ListPostsUseCase;
import com.lj.application.post.subcategory.retrive.get.GetPostSubcategoryByIdUseCase;
import com.lj.domain.account.AccountGateway;
import com.lj.domain.post.PostGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostUseCaseConfig {

    private final PostGateway postGateway;

    public PostUseCaseConfig(final PostGateway postGateway) {
        this.postGateway = postGateway;
    }

    @Bean
    public CreateRevenuePostUseCase createRevenuePostUseCase(GetPostCategoryByIdUseCase getPostCategoryByIdUseCase,
                                                             GetPostSubcategoryByIdUseCase getPostSubcategoryByIdUseCase,
                                                             GetAccountByIdUseCase getAccountByIdUseCase,
                                                             UpdateAccountAddAmountUseCase updateAccountAddAmountUseCase,
                                                             CreateListPostItemUseCase createListPostItemUseCase) {
        return new DefaultCreateRevenuePostUseCase(postGateway,
                                                    getPostCategoryByIdUseCase,
                                                    getPostSubcategoryByIdUseCase,
                                                    getAccountByIdUseCase,
                                                    createListPostItemUseCase,
                                                    updateAccountAddAmountUseCase);
    }

    @Bean
    public CreateExpensePostUseCase createExpensePostUseCase(final PostGateway postGateway,
                                                             final GetPostCategoryByIdUseCase getPostCategoryByIdUseCase,
                                                             final GetPostSubcategoryByIdUseCase getPostSubcategoryByIdUseCase,
                                                             final GetAccountByIdUseCase getAccountByIdUseCase,
                                                             final CreateListPostItemUseCase createListPostItemUseCase,
                                                             final UpdateAccountAddAmountUseCase updateAccountBalanceUseCase) {
        return new DefaultCreateExpensePostUseCase(postGateway,
                                                    getPostCategoryByIdUseCase,
                                                    getPostSubcategoryByIdUseCase,
                                                    getAccountByIdUseCase,
                                                    createListPostItemUseCase,
                                                    updateAccountBalanceUseCase);
    }

    @Bean
    public ListPostsUseCase listPostsUseCase() {
        return new DefaultListPostsUseCase(postGateway);
    }
}
