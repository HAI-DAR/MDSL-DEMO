package com.mdsl.institution.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -807385397L;

    public static final QUser user = new QUser("user");

    public final com.mdsl.institution.domain.common.base.QBaseEntity _super = new com.mdsl.institution.domain.common.base.QBaseEntity(this);

    //inherited
    public final StringPath authorizedBy = _super.authorizedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> authorizedDate = _super.authorizedDate;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<java.math.BigDecimal> id = createNumber("id", java.math.BigDecimal.class);

    public final StringPath lastName = createString("lastName");

    public final EnumPath<com.mdsl.institution.domain.common.LoginLanguage> loginLang = createEnum("loginLang", com.mdsl.institution.domain.common.LoginLanguage.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath password = createString("password");

    //inherited
    public final EnumPath<com.mdsl.institution.domain.common.RecordStatus> recordStatus = _super.recordStatus;

    public final EnumPath<UserRole> role = createEnum("role", UserRole.class);

    public final ListPath<com.mdsl.institution.domain.token.Token, com.mdsl.institution.domain.token.QToken> tokens = this.<com.mdsl.institution.domain.token.Token, com.mdsl.institution.domain.token.QToken>createList("tokens", com.mdsl.institution.domain.token.Token.class, com.mdsl.institution.domain.token.QToken.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

