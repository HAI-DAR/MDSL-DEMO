package com.mdsl.institution.domain.token;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QToken is a Querydsl query type for Token
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QToken extends EntityPathBase<Token> {

    private static final long serialVersionUID = 459018497L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QToken token1 = new QToken("token1");

    public final com.mdsl.institution.domain.common.base.QBaseEntity _super = new com.mdsl.institution.domain.common.base.QBaseEntity(this);

    //inherited
    public final StringPath authorizedBy = _super.authorizedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> authorizedDate = _super.authorizedDate;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final BooleanPath expired = createBoolean("expired");

    public final NumberPath<java.math.BigDecimal> id = createNumber("id", java.math.BigDecimal.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    //inherited
    public final EnumPath<com.mdsl.institution.domain.common.RecordStatus> recordStatus = _super.recordStatus;

    public final BooleanPath revoked = createBoolean("revoked");

    public final StringPath token = createString("token");

    public final EnumPath<TokenType> tokenType = createEnum("tokenType", TokenType.class);

    public final com.mdsl.institution.domain.user.QUser user;

    public QToken(String variable) {
        this(Token.class, forVariable(variable), INITS);
    }

    public QToken(Path<? extends Token> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QToken(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QToken(PathMetadata metadata, PathInits inits) {
        this(Token.class, metadata, inits);
    }

    public QToken(Class<? extends Token> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.mdsl.institution.domain.user.QUser(forProperty("user")) : null;
    }

}

