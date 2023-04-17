package com.lj.application.post.create.revenue;

import com.lj.application.UseCase;
import com.lj.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateRevenuePostUseCase extends UseCase<CreateRevenuePostCommand, Either<Notification, CreateRevenuePostOutput>> {
}
